
package com.aklc.security;

import java.io.*;
import java.security.*;

public class GenSig {

	public static void main(String[] args) {

		/* Generate a DSA signature */

		try {

			KeyPairGenerator keyGen = KeyPairGenerator
					.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);
			KeyPair pair = keyGen.generateKeyPair();
			PrivateKey priv = pair.getPrivate();
			PublicKey pub = pair.getPublic();

			Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
			dsa.initSign(priv);

			FileInputStream fis = new FileInputStream("hello.txt");
			BufferedInputStream bufin = new BufferedInputStream(fis);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufin.read(buffer)) >= 0) {
				dsa.update(buffer, 0, len);
			}

			bufin.close();

			byte[] realSig = dsa.sign();

			/* save the signature in a file */
			FileOutputStream sigfos = new FileOutputStream("sig");
			sigfos.write(realSig);
			sigfos.close();

			/* save the public key in a file */
			byte[] key = pub.getEncoded();
			FileOutputStream keyfos = new FileOutputStream("suepk");
			keyfos.write(key);
			keyfos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Caught exception " + e.toString());
		}
	}
}
