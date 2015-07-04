package com.aklc.security;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import com.aklc.dao.KeyDAO;
import com.aklc.mail.SendMail;

public class GenerateKeys {

	public void generateKeys(String email) throws Exception {

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		keyGen.initialize(1024, random);
		KeyPair pair = keyGen.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();

		BigInteger pubKey = new BigInteger(pub.getEncoded());
		BigInteger privKey = new BigInteger(priv.getEncoded());
		KeyDAO dao = new KeyDAO();
		dao.storeKey(email, pubKey.toString());

		SendMail mail = new SendMail();
		mail.send(
				email,
				"BBMP | Voting Server | Private Key for Signing Process",
				"<b>Dear Voter</b>, <br>Here is the private key you can chose for signing your vote. <br/>We take care of securely transmitting your vote from Ballot to Server.<br/><br/><div style='background-color: lightgray; padding: 10px;'> "
						+ privKey.toString() + "</div>");

	}
}
