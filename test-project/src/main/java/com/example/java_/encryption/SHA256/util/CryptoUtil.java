package com.example.java_.encryption.SHA256.util;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;


public class CryptoUtil {
	/**
	 * 파일암호화에 쓰이는 버퍼 크기 지정
	 */
	public static final int kBufferSize = 8192;

	/**
	 * Key 파일
	 */
	private static String keyFileURL = "crypto.key";

	/**
	 * 키파일 지정
	 *
	 * @param file Key 파일명
	 */
	public static void setKeyFile(String file) {
		keyFileURL = file;
	}

	/**
	 * 지정된 비밀키를 가지고 오는 메서드
	 *
	 * @return Key 비밀키 클래스
	 * @exception Exception
	 */
	private static Key getKey() throws Exception {
		return getKey(keyFileURL);
	}

	/**
	 * 암호화키를 가지고 오는 메서드
	 *
	 * @param keyFile 암혹화 키 파일
	 * @return 암화화 키
	 * @throws Exception
	 */
	public static Key getKey(String keyFile) {
		Key key = null;
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(keyFile));
			key = (Key) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return key;
	}

	/**
	 * 문자열 대칭 암호화
	 *
	 * @param ID 비밀키 암호화를 희망하는 문자열
	 * @return String 암호화된 ID
	 * @exception Exception
	 */
	public static String encrypt(String ID) {
		if (ID == null || ID.length() == 0) return "";
		
		Cipher cipher;
		String outputStr = "";
		
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			
			cipher.init(Cipher.ENCRYPT_MODE, getKey());
			String amalgam = ID;
			byte[] inputBytes1 = amalgam.getBytes("UTF8");
			byte[] outputBytes1 = cipher.doFinal(inputBytes1);
			
			Encoder encoder = Base64.getEncoder();
			outputStr = encoder.encodeToString(outputBytes1);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		};
		
		return outputStr;
	}

	/**
	 * 문자열 대칭 암호화
	 *
	 * @param ID 암호화를 원하는 문자열
	 * @param key 암호화 키
	 * @return String 암호화된 문자열
	 * @throws Exception
	 */
	public static String encrypt(String ID, Key key) {
		if (ID == null || ID.length() == 0) return "";
		if (key==null) {
			System.out.println("key is null");
		}
		
		String outputStr = "";
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			
			cipher.init(Cipher.ENCRYPT_MODE, key);
			String amalgam = ID;

			byte[] inputBytes1 = amalgam.getBytes("UTF8");
			byte[] outputBytes1 = cipher.doFinal(inputBytes1);
			
			Encoder encoder = Base64.getEncoder();
			outputStr = encoder.encodeToString(outputBytes1);
			
			
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		};
		
		
		return outputStr;
	}

	/**
	 * 문자열 대칭 복호화
	 *
	 * @param codedID 비밀키 복호화를 희망하는 문자열
	 * @return String 복호화된 ID
	 * @exception Exception
	 */
	public static String decrypt(String codedID) {
		if (codedID == null || codedID.length() == 0) return "";
		
		Cipher cipher;
		String strResult = "";
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			
			cipher.init(Cipher.DECRYPT_MODE, getKey());
			Decoder decoder = Base64.getDecoder();
			
			byte[] inputBytes1 = decoder.decode(codedID);
			byte[] outputBytes2 = cipher.doFinal(inputBytes1);

			strResult = new String(outputBytes2, "UTF8");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		};
		
		return strResult;
	}

	/**
	 * 무자열 대칭 복호화
	 *
	 * @param codedID 복호화를 희망하는 문자열
	 * @param key 암/복호화 키
	 * @return String 복호화된 문자열
	 * @throws Exception
	 */
	public static String decrypt(String codedID, Key key) {
		if (codedID == null || codedID.length() == 0) return "";
		
		Cipher cipher;
		String strResult = codedID;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			cipher.init(Cipher.DECRYPT_MODE, key);
			Decoder decoder = Base64.getDecoder();
			byte[] inputBytes1 = decoder.decode(codedID);
			byte[] outputBytes2 = cipher.doFinal(inputBytes1);
			strResult = new String(outputBytes2, "UTF8");
			
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
		} catch (InvalidKeyException e) {
//			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
//			e.printStackTrace();
		} catch (BadPaddingException e) {
//			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
		} catch (IllegalArgumentException e){
//			e.printStackTrace();
		};
		
		return strResult;
	}

	/**
	 * 파일 대칭 암호화
	 *
	 * @param infile 암호화을 희망하는 파일명
	 * @param outfile 암호화된 파일명
	 * @exception Exception
	 */
	public static void encryptFile(String infile, String outfile) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			
			cipher.init(Cipher.ENCRYPT_MODE, getKey());

			FileInputStream in = new FileInputStream(infile);
			FileOutputStream fileOut = new FileOutputStream(outfile);

			CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
			byte[] buffer = new byte[kBufferSize];
			int length;
			while ((length = in.read(buffer)) != -1)
				out.write(buffer, 0, length);
			in.close();
			out.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		};
		
	}

	/**
	 * 파일 대칭 복호화
	 *
	 * @param infile 복호화을 희망하는 파일명
	 * @param outfile 복호화된 파일명
	 * @exception Exception
	 */
	public static void decryptFile(String infile, String outfile) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding", "SunJCE");
			cipher.init(Cipher.DECRYPT_MODE, getKey());

			FileInputStream in = new FileInputStream(infile);
			FileOutputStream fileOut = new FileOutputStream(outfile);

			CipherOutputStream out = new CipherOutputStream(fileOut, cipher);
			byte[] buffer = new byte[kBufferSize];
			int length;
			while ((length = in.read(buffer)) != -1)
				out.write(buffer, 0, length);
			in.close();
			out.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		};
		
	}
}
