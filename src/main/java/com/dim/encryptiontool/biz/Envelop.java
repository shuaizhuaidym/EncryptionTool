/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.biz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author yanming_dai
 */
public class Envelop {

    private String ksType = "PKCS12";
    static String src = "<?xml version='1.0' encoding='UTF-8'?><AuthorizationRequest><head><requestType>qr_authorization</requestType></head><token>b040e82e-5878-4836-85a1-fe49bb760191402889f0</token><qrcode>sxyF1LRdyjrPPbbxOcUPK0kd8O18mfYK402889f0</qrcode><appFlag>VUlBUw==</appFlag><customAttr></customAttr></AuthorizationRequest>";
    public static void main(String[] args) throws Exception{
        String envelopeMessage = new Envelop().envelopeMessage(src, "C:/Users/yanming_dai/Desktop/0.1.cer", "UTF-8");
    }
            

    /**
     * 加密数据
     *
     * @param srcMsg 源信息
     * @param certPath 证书路径
     * @param charSet 字符编码
     * @return
     * @throws Exception
     */
    public String envelopeMessage(String srcMsg, String certPath, String charSet) throws Exception {
        CertificateFactory certificatefactory;
        X509Certificate cert;
        // 使用公钥对对称密钥进行加密 //若此处不加参数 "BC" 会报异常：CertificateException -
        certificatefactory = CertificateFactory.getInstance("X.509", "BC");
        // 读取.crt文件；你可以读取绝对路径文件下的crt，返回一个InputStream（或其子类）即可。
        InputStream bais = new FileInputStream(certPath);

        cert = (X509Certificate) certificatefactory.generateCertificate(bais);

        //添加数字信封
        CMSTypedData msg = new CMSProcessableByteArray(srcMsg.getBytes(charSet));

        CMSEnvelopedDataGenerator edGen = new CMSEnvelopedDataGenerator();

        edGen.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(
                cert).setProvider("BC"));

        CMSEnvelopedData edata = edGen.generate(msg,
                new JceCMSContentEncryptorBuilder(PKCSObjectIdentifiers.rc4).setProvider("BC").build());

        String rslt = new String(Base64.encode(edata.getEncoded()));

        System.out.println(rslt);
        return rslt;
    }

    /**
     * 解密数据
     *
     * @param encode 加密后的密文
     * @param certPath 证书路径
     * @param certPwd 证书密码
     * @param charSet 字符编码
     * @return
     * @throws Exception
     */
    public String openEnvelope(String encode, String certPath, String certPwd, String charSet) throws Exception {
        //获取密文
        CMSEnvelopedData ed = new CMSEnvelopedData(Base64.decode(encode.getBytes()));

        RecipientInformationStore recipients = ed.getRecipientInfos();

        Collection c = recipients.getRecipients();
        Iterator it = c.iterator();

        // 加载证书
        KeyStore ks = KeyStore.getInstance(ksType);
        ks.load(new FileInputStream(certPath), certPwd.toCharArray());

        String priKeyName = null;
        if (ks.aliases().hasMoreElements()) {
            priKeyName = ks.aliases().nextElement();
        }

        // 获取私钥
        PrivateKey prikey = (PrivateKey) ks.getKey(priKeyName, certPwd.toCharArray());

        byte[] recData = null;
        //解密
        if (it.hasNext()) {
            RecipientInformation recipient = (RecipientInformation) it.next();

            recData = recipient.getContent(new JceKeyTransEnvelopedRecipient(
                    prikey).setProvider("BC"));
        }

        return new String(recData, charSet);
    }

    public Envelop() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
}
