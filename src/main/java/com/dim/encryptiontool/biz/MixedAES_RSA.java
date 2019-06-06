/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.biz;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * @author yanming_dai
 * Usage
 * To use the code, you need corresponding public and private RSA keys. RSA keys can be generated using the open source tool OpenSSL. However, you have to be careful to generate them in the format required by the Java encryption libraries. To generate a private key of length 2048 bits:
 * openssl genrsa -out private.pem 2048 
 * To get it into the required (PKCS#8, DER) format:
 * openssl pkcs8 -topk8 -in private.pem -outform DER -out private.der -nocrypt
 * To generate a public key from the private key:
 * openssl rsa -in private.pem -pubout -outform DER -out public.der
 * An example of how to use the code:
 * FileEncryption secure = new FileEncryption();

 * to encrypt a file
 * secure.makeKey();
 * secure.saveKey(encryptedKeyFile, publicKeyFile);
 * secure.encrypt(fileToEncrypt, encryptedFile);

 * to decrypt it again
 * secure.loadKey(encryptedKeyFile, privateKeyFile);
 * secure.decrypt(encryptedFile, unencryptedFile);
 */
public class MixedAES_RSA {

    Cipher pkiCipher;
    Cipher aesCipher;
    int AES_Key_Size = 256;
    SecretKeySpec aeskeySpec;
        
    byte[] aesKey;
    
//    The encryption algorithms are specified in the constructor
    public MixedAES_RSA() throws GeneralSecurityException {
        // create RSA public key cipher
        pkiCipher = Cipher.getInstance("RSA");
        // create AES shared key cipher
        aesCipher = Cipher.getInstance("AES");
    }

//A random AES key is generated to encrypt files. A key size (AES_Key_Size) of 256 bits is standard for AES
    public void makeKey() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AES_Key_Size);
        SecretKey key = kgen.generateKey();
        aesKey = key.getEncoded();
        aeskeySpec = new SecretKeySpec(aesKey, "AES");
    }

//    The file encryption and decryption routines then use the AES cipher
    public void encrypt(File in, File out) throws IOException, InvalidKeyException {
        aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec);

        FileInputStream is = new FileInputStream(in);
        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), aesCipher);

        copy(is, os);

        os.close();
    }

    public void decrypt(File in, File out) throws IOException, InvalidKeyException {
        aesCipher.init(Cipher.DECRYPT_MODE, aeskeySpec);

        CipherInputStream is = new CipherInputStream(new FileInputStream(in), aesCipher);
        FileOutputStream os = new FileOutputStream(out);

        copy(is, os);

        is.close();
        os.close();
    }

    private void copy(InputStream is, OutputStream os) throws IOException {
        int i;
        byte[] b = new byte[1024];
        while ((i = is.read(b)) != -1) {
            os.write(b, 0, i);
        }
    }

    /**
     * So that the files can be decrypted later, the AES key is encrypted to a file using the RSA
     * cipher.The RSA public key is assumed to be stored in a file.
     */
    public void saveKey(File out, File publicKeyFile) throws IOException, GeneralSecurityException {
        // read public key to be used to encrypt the AES key
        byte[] encodedKey = new byte[(int) publicKeyFile.length()];
        new FileInputStream(publicKeyFile).read(encodedKey);

        // create public key
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(publicKeySpec);

        // write AES key
        pkiCipher.init(Cipher.ENCRYPT_MODE, pk);
        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), pkiCipher);
        os.write(aesKey);
        os.close();
    }
//    Before decryption can take place , the encrypted AES key must be decrypted using the RSA private key

    public void loadKey(File in, File privateKeyFile) throws GeneralSecurityException, IOException {
        // read private key to be used to decrypt the AES key
        byte[] encodedKey = new byte[(int) privateKeyFile.length()];
        new FileInputStream(privateKeyFile).read(encodedKey);

        // create private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pk = kf.generatePrivate(privateKeySpec);

        // read AES key
        pkiCipher.init(Cipher.DECRYPT_MODE, pk);
        aesKey = new byte[AES_Key_Size / 8];
        CipherInputStream is = new CipherInputStream(new FileInputStream(in), pkiCipher);
        is.read(aesKey);
        aeskeySpec = new SecretKeySpec(aesKey, "AES");
    }
}
