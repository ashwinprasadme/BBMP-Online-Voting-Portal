
package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;

public class ApproveServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			UserDAO dao = new UserDAOImpl();
			dao.approve(req.getParameter("email"));
			resp.sendRedirect("requests");
		} catch (Exception e) {
			resp.sendRedirect("details.jsp?res=fail");
		}
	}

}
