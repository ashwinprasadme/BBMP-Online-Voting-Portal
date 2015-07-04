package com.aklc.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;
import com.aklc.pojo.Election;

public class AddElectionServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Election e = new Election();
			e.setDescp(req.getParameter("descp"));
			e.setAssembly(req.getParameter("assembly"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			e.setCommencedate(new Date(sdf.parse(
					req.getParameter("commencedate")).getTime()));
			SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
			e.setBegintime(new Time(sdf2.parse(req.getParameter("begintime"))
					.getTime()));
			e.setEndtime(new Time(sdf2.parse(req.getParameter("endtime"))
					.getTime()));
			e.setState("NEW");
			
			ElectionDAO dao = new ElectionDAOImpl();
			dao.write(e);
			resp.sendRedirect("electionList");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("election.jsp?res=fail");
		}
	}

}
