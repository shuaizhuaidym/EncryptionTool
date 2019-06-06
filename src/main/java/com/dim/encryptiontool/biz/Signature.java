/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.biz;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

/**
 *
 * @author yanming_dai
 */
public class Signature {
    	private String ksType = "PKCS12";
        	/**
	 * 生成数字签名
	 * @param srcMsg 源信息
	 * @param charSet 字符编码
	 * @param certPath 证书路径
	 * @param certPwd 证书密码
	 * @return
	 */
	public byte[] signMessage(String srcMsg, String charSet, String certPath, String certPwd) {
		String priKeyName = null;
		char passphrase[] = certPwd.toCharArray();
 
		try {
			Provider provider = new BouncyCastleProvider();
			// 添加BouncyCastle作为安全提供
			Security.addProvider(provider);
 
			// 加载证书
			KeyStore ks = KeyStore.getInstance(ksType);
			ks.load(new FileInputStream(certPath), passphrase);
 
			if (ks.aliases().hasMoreElements()) {
				priKeyName = ks.aliases().nextElement();
			}
			
			Certificate cert = (Certificate) ks.getCertificate(priKeyName);
 
			// 获取私钥
			PrivateKey prikey = (PrivateKey) ks.getKey(priKeyName, passphrase);
 
			X509Certificate cerx509 = (X509Certificate) cert;
 
			List<Certificate> certList = new ArrayList<Certificate>();
			certList.add(cerx509);
 
			CMSTypedData msg = (CMSTypedData) new CMSProcessableByteArray(
					srcMsg.getBytes(charSet));
 
			Store certs = new JcaCertStore(certList);
 
			CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
			ContentSigner sha1Signer = new JcaContentSignerBuilder(
					"SHA1withRSA").setProvider("BC").build(prikey);
 
			gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
					new JcaDigestCalculatorProviderBuilder().setProvider("BC")
							.build()).build(sha1Signer, cerx509));
 
			gen.addCertificates(certs);
 
			CMSSignedData sigData = gen.generate(msg, true);
 
			return Base64.encode(sigData.getEncoded());
 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
        
        	/**
	 * 验证数字签名
	 * @param signedData
	 * @return
	 */
	public boolean signedDataVerify(byte[] signedData) {
		boolean verifyRet = true;
		try {
			// 新建PKCS#7签名数据处理对象
			CMSSignedData sign = new CMSSignedData(signedData);
 
			// 添加BouncyCastle作为安全提供
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
 
			// 获得证书信息
			Store certs = sign.getCertificates();
 
			// 获得签名者信息
			SignerInformationStore signers = sign.getSignerInfos();
			Collection c = signers.getSigners();
			Iterator it = c.iterator();
 
			// 当有多个签名者信息时需要全部验证
			while (it.hasNext()) {
				SignerInformation signer = (SignerInformation) it.next();
 
				// 证书链
				Collection certCollection = certs.getMatches(signer.getSID());
				Iterator certIt = certCollection.iterator();
				X509CertificateHolder cert = (X509CertificateHolder) certIt
						.next();
 
				// 验证数字签名
				if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder()
						.setProvider("BC").build(cert))) {
					verifyRet = true;
				} else {
					verifyRet = false;
				}
			}
 
		} catch (Exception e) {
			verifyRet = false;
			e.printStackTrace();
			System.out.println("验证数字签名失败");
		}
		return verifyRet;
	}
}
