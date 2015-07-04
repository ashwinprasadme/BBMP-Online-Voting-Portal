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

public class VoteNowServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int eid = Integer.parseInt(req.getParameter("eid"));
		ElectionDAO dao = new ElectionDAOImpl();
		try {
			List<Participants> participants = dao.getparticipants(eid);
			req.setAttribute("participants", participants);
			req.setAttribute("desc", req.getParameter("desc"));
			req.setAttribute("assembly", req.getParameter("assembly"));
			req.getRequestDispatcher("votenow.jsp").forward(req, resp);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
