
package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;
import com.aklc.pojo.User;

public class ReqDetailsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			UserDAO dao = new UserDAOImpl();

			User details = dao.getDetails(req.getParameter("email"));
			req.setAttribute("details", details);
			req.getRequestDispatcher("details.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect("details.jsp?res=fail");
		}

	}

}
