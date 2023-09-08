package com.example.java_.encryption.SHA256.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util
{
	
    public static String getEncrypt(String source)
    {
        String result = "";
        try
        {
            byte[] a = source.getBytes();
           
            /*/
            byte[] salt = source.getBytes();
            byte[] bytes = new byte[a.length + salt.length];
            System.arraycopy(a, 0, bytes, 0, a.length);
            System.arraycopy(salt, 0, bytes, a.length, salt.length);
            /*/
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(a);

            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; ++i)
            {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
        	e.printStackTrace();
        }

        return result;
    }
    
    // 임의 salt를 생성
    public static byte[] createSalt(String password) {
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = password.getBytes();
		random.nextBytes(saltBytes);
        return saltBytes;
    }
}
