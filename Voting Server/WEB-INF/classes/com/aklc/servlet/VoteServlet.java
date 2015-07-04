package com.aklc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.KeyDAO;
import com.aklc.dao.ResultDAO;
import com.aklc.dao.ResultDAOImpl;

public class VoteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			String assembly = req.getParameter("assembly");
			String email = req.getParameter("email");
			String data = req.getParameter("data");
			String sigToVerify = req.getParameter("sig");
			String eid = req.getParameter("eid");
			System.out.println("2. Sig .. " + sigToVerify);
			System.out.println("2. Data .. " + data);
			KeyDAO dao = new KeyDAO();
			String pubK = dao.getKey(email);
			BigInteger bi = new BigInteger(pubK);
			byte[] pubArray = bi.toByteArray();
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubArray);
			KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
			PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

			Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
			sig.initVerify(pubKey);

			sig.update(data.getBytes());
			BigInteger bi2 = new BigInteger(sigToVerify);
//			byte[] array = bi2.toByteArray();
//			if (array[0] == 0) {
//				byte[] tmp = new byte[array.length - 1];
//				System.arraycopy(array, 1, tmp, 0, tmp.length);
//				array = tmp;
//			}
			boolean chk = sig.verify(bi2.toByteArray());
			if (chk) {
				System.out.println("Signature verified successfully .. ");
				System.out.println(eid);
				ResultDAO dao2 = new ResultDAOImpl();
				try {
					String[] arr = data.split("<>");
					dao2.vote(assembly, arr[1], arr[0], eid, email);
					pw.print("SUCCESS");
				} catch (Exception e) {
					e.printStackTrace();
					pw.print("FAILURE");
				}

			} else {
				System.out.println("Signature is not valid");
			}

			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
