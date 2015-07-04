package com.aklc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;
import com.aklc.pojo.Election;

public class ElectionListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			ElectionDAO dao = new ElectionDAOImpl();
			List<Election> list = dao.read();
			req.setAttribute("res", list);
			req.getRequestDispatcher("election.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("election.jsp?res=fail");
		}
	}

}
