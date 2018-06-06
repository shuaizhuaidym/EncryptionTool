/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yanming_dai
 */
public class MD5 {

    public static final String ALGORITHM_MD5 = "MD5";
    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
        'd', 'e', 'f'};

    /**
     * 计算输入流MD5
     *
     * @param stream
     * @return
     */
    public static String streamToMD5(InputStream stream) {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM_MD5);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = stream.read(buffer)) > 0) {
                digester.update(buffer, 0, numRead);
            }
            byte[] dg = digester.digest();
            return bufferToHex(dg, 0, dg.length);
        } catch (IOException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    // byte[] to hex
    private static String bufferToHex(byte[] bf, int offset, int len) {
        StringBuffer buf = new StringBuffer(2 * len);
        for (int i = offset, k = offset + len; i < k; ++i) {
            appendHexPair(bf[i], buf);
        }
        return buf.toString();
    }

    // byte to hex
    private static void appendHexPair(byte bt, StringBuffer buf) {

        char c0 = hexDigits[((bt & 0xF0) >> 4)];
        char c1 = hexDigits[(bt & 0xF)];
        buf.append(c0);
        buf.append(c1);
    }
}
