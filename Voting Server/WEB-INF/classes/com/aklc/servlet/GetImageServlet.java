
package com.aklc.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.PartyDAO;
import com.aklc.dao.PartyDAOImpl;
import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;

public class GetImageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String logo = req.getParameter("partylogo");
		if (logo != null && logo.equals("true")) {
			PartyDAO dao = new PartyDAOImpl();
			InputStream imgBytes = null;
			try {
				imgBytes = dao.getPartyLogo(req.getParameter("name"));
			} catch (Exception e1) {
			}
			resp.setContentType("image/jpeg");
			try {
				resp.getOutputStream().write(getBytes(imgBytes));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else {
			UserDAO dao = new UserDAOImpl();
			InputStream imgBytes = null;
			try {
				imgBytes = dao.getIdProof(req.getParameter("email"));
			} catch (Exception e1) {
			}
			resp.setContentType("image/jpeg");
			try {
				resp.getOutputStream().write(getBytes(imgBytes));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}

	public byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

}
