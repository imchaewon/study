package com.example.java_.encryption.SHA256.util;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.SecureRandom;

public class CryptoKeyBuilder {
	
	/*
	 *  알고리즘은 DES, DESede, PBEWithMD5AndDES 을 사용
	 */ 
	static String method = "DES";
		 
	/**
	 * Main 메서드
	 * 
	 * @param args 생성파일명
	 * @exception Exception
	 */
	public static void main(String[] args) throws Exception {

		String genKeyFile = "crypto.key";
		
		KeyGenerator generator = KeyGenerator.getInstance(method);
		generator.init(new SecureRandom());
		Key key = generator.generateKey();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(".", genKeyFile)));
		out.writeObject(key);
		out.close();
	}
}