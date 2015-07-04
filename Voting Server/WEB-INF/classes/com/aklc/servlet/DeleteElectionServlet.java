package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;

public class DeleteElectionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			ElectionDAO dao = new ElectionDAOImpl();
			dao.delete(Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("electionList");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("election.jsp");
		}
	}

}
