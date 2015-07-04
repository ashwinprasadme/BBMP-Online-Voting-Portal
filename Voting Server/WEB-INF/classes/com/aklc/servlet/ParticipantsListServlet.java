package com.aklc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;
import com.aklc.pojo.Participants;

public class ParticipantsListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			int eid = Integer.parseInt(req.getParameter("eid"));
			ElectionDAO dao = new ElectionDAOImpl();
			List<Participants> res = dao.getparticipants(eid);
			req.setAttribute("res", res);
			req.setAttribute("eid", String.valueOf(eid));
			List<String> pnames = dao.getPartyNames();
			pnames.add("Independent");
			req.setAttribute("pnames", pnames);
			req.getRequestDispatcher("participants.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("participants.jsp?res=fail");
		}
	}

}
