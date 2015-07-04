package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.PartyDAO;
import com.aklc.dao.PartyDAOImpl;

public class DeletePartyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("name");
		PartyDAO dao = new PartyDAOImpl();
		try {
			dao.deleteParty(name);
			resp.sendRedirect("parties");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("parties.jsp?res=fail");
		}
	}

}
