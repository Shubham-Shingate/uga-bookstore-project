package com.uga.forwords.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SHAFactory {

	public static String getSHAEncryptedString(String stringToBeEncrypted, byte[] salt, String encryptionAlgorithm) {
		String encryptedString = null;
		try {
			MessageDigest md = MessageDigest.getInstance(encryptionAlgorithm);
			md.update(salt);
			byte[] bytes = md.digest(stringToBeEncrypted.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			encryptedString = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
	private SHAFactory() {
		
	}
}
