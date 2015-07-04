package com.aklc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;
import com.aklc.pojo.Participants;

public class RemoveParticipantServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String eid = req.getParameter("eid");
			String name = req.getParameter("name");
			String pname = req.getParameter("pname");
			ElectionDAO dao = new ElectionDAOImpl();
			Participants p = new Participants();
			p.setEid(Integer.parseInt(eid));
			p.setName(name);
			p.setPartyName(pname);
			dao.removeParticipant(p);
			resp.sendRedirect("participants?eid="+eid);
		} catch (Exception e) {
			resp.sendRedirect("participants.jsp?res=fail");
		}
	}

}
