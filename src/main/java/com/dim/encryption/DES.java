/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author yanming_dai
 */
public class DES {
    
	static String src = "F:/X项目支持/G工具/脚本加密/test/org.txt";
	static String dst = "F:/X项目支持/G工具/脚本加密/test/dst.txt";

	static String chk = "F:/X项目支持/G工具/脚本加密/test/chk.txt";
	// 密码，长度要是8的倍数
	public final static String pass = "dsserver";

	// 测试
	public static void main(String args[]) throws IOException {

//		DES.encryptFile(src, dst, pass);

		try {
//			byte[] decryResult = DES.decryptFile(dst, chk, pass);
//			System.out.println(new String(decryResult));
			printHome();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void printHome() {
		System.out.println(System.getProperty("user.home"));
	}


	/**
	 * 
	 * @param srcPath
	 * @param dstPath
	 * @return
	 * @throws IOException
	 */
	public static void encryptFile(String srcPath, String dstPath, String pass) throws IOException {
		FileInputStream in = new FileInputStream(srcPath);
		byte[] filedata = null;
		if (in != null) {
			int len = in.available();
			filedata = new byte[len];
			in.read(filedata);
			in.close();
		}
		byte[] crypt = encrypt(filedata, pass);
		FileOutputStream out = new FileOutputStream(dstPath);
		out.write(crypt);
		out.flush();
		if (out != null) {
			out.close();
		}
	}

	/**
	 * 解密文件
	 * @param srcPath
	 * @param dstPath
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptFile(String srcPath, String dstPath, String pass) throws Exception {
		FileInputStream in = new FileInputStream(srcPath);
		byte[] filedata = null;
		if (in != null) {
			int len = in.available();
			filedata = new byte[len];
			in.read(filedata);
			in.close();
		}
		byte[] crypt = decrypt(filedata, pass);
		FileOutputStream out = new FileOutputStream(dstPath);
		out.write(crypt);
		out.flush();
		if (out != null) {
			out.close();
		}
		return crypt;
	}

	/**
	 * 加密
	 * 
	 * @param datasource byte[]
	 * @param password String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src byte[]
	 * @param password String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

}
