



import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

class VerSig {

	public static void main(String[] args) {

		/* Verify a DSA signature */

		try {

			FileInputStream keyfis = new FileInputStream("suepk");
			byte[] encKey = new byte[keyfis.available()];
			keyfis.read(encKey);

			keyfis.close();

			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

			KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

			FileInputStream sigfis = new FileInputStream("sig");
			byte[] sigToVerify = new byte[sigfis.available()];
			sigfis.read(sigToVerify);
			sigfis.close();

			BigInteger bi = new BigInteger(sigToVerify);
			byte[] versig = bi.toByteArray();
			Signature sig = Signature.getInstance("SHA1withDSA", "SUN");

			sig.initVerify(pubKey);
			FileInputStream datafis = new FileInputStream("hello.txt");
			BufferedInputStream bufin = new BufferedInputStream(datafis);

			byte[] buffer = new byte[1024];
			int len;
			while (bufin.available() != 0) {
				len = bufin.read(buffer);
				sig.update(buffer, 0, len);
			}
			System.out.println(new String(buffer));
			bufin.close();

			boolean verifies = sig.verify(versig);

			System.out.println("signature verifies: " + verifies);

		} catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
	}

}
