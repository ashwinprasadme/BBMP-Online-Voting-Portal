
package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.OTPDAO;
import com.aklc.dao.OTPDAOImpl;
import com.aklc.dao.UserDAOImpl;
import com.aklc.dao.UserDAO;

public class Login2Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = (String) req.getSession().getAttribute("email");
		String pw = req.getParameter("otp");
		try {

			OTPDAO dao = new OTPDAOImpl();
			if (dao.verifyOTP(email, pw)) {
				resp.sendRedirect("welcome.jsp");
			} else {
				req.getSession().invalidate();
				resp.sendRedirect("login.jsp?res=otpfail");
			}

		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}

	}

}
