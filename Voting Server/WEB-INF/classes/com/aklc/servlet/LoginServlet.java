
package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.OTPDAO;
import com.aklc.dao.OTPDAOImpl;
import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;
import com.aklc.util.OTP;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		try {

			if (email != null && email.equals("ecadmin") && pw != null
					&& pw.equals("admin009")) {
				req.getSession().setAttribute("loggedIn", "yes");
				resp.sendRedirect("welcome.jsp");
			} else {
				resp.sendRedirect("login.jsp?res=fail");
			}

		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}

	}

}
