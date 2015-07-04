package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.PartyDAO;
import com.aklc.dao.PartyDAOImpl;

public class PartiesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			PartyDAO dao = new PartyDAOImpl();

			req.setAttribute("parties", dao.getpartyNames());
			req.getRequestDispatcher("parties.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
