package com.aklc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.security.GenerateKeys;

public class RequestPrivateKeyServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		GenerateKeys gk = new GenerateKeys();
		try {
			gk.generateKeys(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		pw.print("Successfully Generated the Keys for Signing Process and Sent Over the Keys in an Email. Please Check Your Mail");
		pw.close();

	}

}
