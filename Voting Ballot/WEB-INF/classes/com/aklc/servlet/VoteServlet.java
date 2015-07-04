package com.aklc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class VoteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String eid = req.getParameter("eid");
			String name = req.getParameter("name");
			String pname = req.getParameter("pname");
			String assembly = req.getParameter("assembly");
			String priv = req.getParameter("priv");
			System.out.println("private key .. " + priv);
			BigInteger bi = new BigInteger(priv.trim());
			byte[] privBytes = bi.toByteArray();

			PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privBytes);

			KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

			PrivateKey privKey = keyFactory.generatePrivate(privKeySpec);
			Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
			dsa.initSign(privKey);
			String data = (name + "<>" + pname);
			dsa.update(data.getBytes());
			byte[] sig = dsa.sign();

			BigInteger bi2 = new BigInteger(sig);

			String url = "http://ashvtu-ashvtu.rhcloud.com/vS/vote";
			HttpPost post = new HttpPost(url);
			HttpClient client = HttpClientBuilder.create().build();
			System.out.println("1 .. sig .. " + String.valueOf(bi2));
			System.out.println("1.. data .. " + data);
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("assembly", assembly));
			urlParameters.add(new BasicNameValuePair("data", data));
			urlParameters
					.add(new BasicNameValuePair("sig", String.valueOf(bi2)));
			urlParameters.add(new BasicNameValuePair("eid", eid));
			urlParameters.add(new BasicNameValuePair("email", (String) req
					.getSession().getAttribute("email")));

			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result);
			if (result.toString().contains("SUCCESS")) {
				System.out.println(1);
				req.setAttribute("res", "done");
			} else {
				System.out.println(2);
				req.setAttribute("res", "fail");
			}
			req.getRequestDispatcher("votenow.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("res", "fail");
			req.getRequestDispatcher("votenow.jsp").forward(req, resp);
		}
	}
}
