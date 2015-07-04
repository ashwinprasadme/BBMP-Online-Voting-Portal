
package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;
import com.aklc.pojo.User;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			User user = new User();

			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("pw"));
			user.setFname(req.getParameter("fn"));
			user.setLname(req.getParameter("ln"));
			user.setGender(req.getParameter("gen"));
			user.setDob(req.getParameter("dob"));
			user.setMobile(req.getParameter("mobile"));
			user.setAssembly(req.getParameter("assembly"));
			user.setStatus("NEW");

			UserDAO dao = new UserDAOImpl();
			String status = dao.getStatus(user.getEmail());
			System.out.println(status);
			if (status != null && status.equals("NEW")) {
				resp.sendRedirect("register.jsp?res=new");
			} else if (status != null && status.equals("REJECTED")) {
				resp.sendRedirect("register.jsp?res=rejected");
			} else if (status != null && status.equals("APPROVED")) {
				resp.sendRedirect("register.jsp?res=approved");
			} else {

				dao.register(user);

				req.getSession().setAttribute("email", user.getEmail());
				resp.sendRedirect("register2.jsp?res=prev");
			}

		} catch (Exception e) {
			resp.sendRedirect("register.jsp?res=fail");
		}

	}

}
