package com.aklc.test;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptDecrypt {

	public static void main(String[] args) throws Exception {

		ED ed = new ED();
		String cipherBytes = ed.encrypt("Ashwin");

		System.out.println(cipherBytes);
		System.out.println(ed.decrypt(cipherBytes));
	}
}

class ED {
	private static Cipher cipher = null;
	SecretKey secretKey;

	ED() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		keyGenerator.init(168);
		keyGenerator.generateKey();
		cipher = Cipher.getInstance("DESede");
		secretKey = keyGenerator.generateKey();

	}

	public String encrypt(String data) throws Exception {

		// Store the string as an array of bytes. You should
		// specify the encoding method for consistent encoding
		// and decoding across different platforms.

		String clearText = "Ashwin";
		byte[] clearTextBytes = clearText.getBytes("UTF8");

		// Initialize the cipher and encrypt this byte array

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] cipherBytes = cipher.doFinal(clearTextBytes);
		String bi = new BigInteger(cipherBytes).toString();
		return bi;

	}

	public String decrypt(String cipherBytes) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(new BigInteger(cipherBytes)
				.toByteArray());
		String decryptedText = new String(decryptedBytes, "UTF8");

		return decryptedText;

	}
}
