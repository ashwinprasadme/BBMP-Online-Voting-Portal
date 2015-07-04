
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
import com.aklc.mail.SendMail;
import com.aklc.util.OTP;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		try {
			System.setProperty("user.timezone","IST");

			UserDAO dao = new UserDAOImpl();
			String status = dao.login(email, pw);
			if (status == null) {
				resp.sendRedirect("login.jsp?res=fail");
			} else if (status.equals("NEW")) {
				resp.sendRedirect("login.jsp?res=pending");
			} else if (status.equals("REJECTED")) {
				resp.sendRedirect("login.jsp?res=rejected");
			} else if (status.equals("APPROVED")) {
				req.getSession().setAttribute("email", email);
				req.getSession().setAttribute("assembly",
						dao.getAssembly(email));
				String otp = OTP.get();
				SendMail mail = new SendMail();
				mail.send(
						email,
						"One Time Password (OTP) from Voting Ballet",
						"<b>Dear Voter</b>, <br>Here is the One Time Password to access the voting ballet.<br/><br/>Make sure not to copy the white spaces<br/><br/> <div style='background-color: lightgray; padding: 10px;'> "
								+ otp + "</div>");
				OTPDAO dao2 = new OTPDAOImpl();
				dao2.insertOTP(email, otp);
				System.out.println(otp);
				resp.sendRedirect("login2.jsp");
			}
		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}

	}

}
