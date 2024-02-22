package com.example.java_.encryption.SHA256.util;

import java.io.File;
import java.net.URL;
import java.security.Key;

public class CryptoHelper {
	// Single instance
	private static final CryptoHelper instance = new CryptoHelper();
	
	// 암/복호화 키
	private Key key = null;
	
	private CryptoHelper() {
		try {
			this.key  = loadKey();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 인스턴스 획득
	 *
	 * @return CryptoHelper 인스턴스
	 */
	public static final CryptoHelper getInstance() {
		return instance;
	}

	/**
	 * 암복호화 키 로딩
	 *
	 * @return 암복화키
	 * @throws Exception
	 */
	private Key loadKey()  {
		
		String KEY_FILE = "abcdefg";
	
		URL url = null;
		ClassLoader loader =  Thread.currentThread().getContextClassLoader();
		if (loader != null)	 url = loader.getResource(KEY_FILE);
		if (url == null) url = ClassLoader.getSystemResource(KEY_FILE);
		File resourceFile = new File(url.getFile());
		
		//File resourceFile = new File(keyfile);
		String absolutePath = resourceFile.getAbsolutePath();
		
		return CryptoUtil.getKey(absolutePath);
	}

	/**
	 * 문자열을 암호화
	 *
	 * @param value 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws Exception
	 */
	public String encrypt(String value) {
		return CryptoUtil.encrypt(value, this.key);
	}

	/**
	 * 문자열 복호화
	 *
	 * @param value 복호화할 문자열
	 * @return 복화화된 문자열
	 * @throws Exception
	 */
	public String decrypt(String value) {
		return CryptoUtil.decrypt(value, this.key);
	}
	
	/**
	 * 단방향 암호화
	 * @param value
	 * @return
	 */
	public String encryptOneway(String value) {
		String result = "";
		
		String encryptPassword = SHA256Util.getEncrypt(value);
		result = encryptPassword ;
		
		return result;
		
	}
	
}
