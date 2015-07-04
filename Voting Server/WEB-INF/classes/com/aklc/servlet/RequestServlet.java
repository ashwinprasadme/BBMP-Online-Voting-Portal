
package com.aklc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;
import com.aklc.pojo.User;

public class RequestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			UserDAO dao = new UserDAOImpl();
			List<User> result = dao.getPendingRequest();

			req.setAttribute("result", result);
			req.getRequestDispatcher("requests.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("requests.jsp");
		}

	}

}
