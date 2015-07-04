package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;
import com.aklc.pojo.Participants;

public class AddParticipantServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Participants p = new Participants();
			p.setSlno(Integer.parseInt(req.getParameter("slno")));
			p.setName(req.getParameter("name"));
			p.setPartyName(req.getParameter("pname"));
			p.setEid(Integer.parseInt(req.getParameter("eid")));

			ElectionDAO dao = new ElectionDAOImpl();
			dao.addParticipant(p);
			resp.sendRedirect("participants?eid=" + req.getParameter("eid"));
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("participants.jsp?res=fail");
		}

	}

}
