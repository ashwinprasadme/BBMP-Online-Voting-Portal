package com.aklc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.dao.ResultDAO;
import com.aklc.dao.ResultDAOImpl;
import com.aklc.pojo.Result;

public class ResultsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			ResultDAO dao = new ResultDAOImpl();
			List<Result> res = dao.getResults(Integer.parseInt(req
					.getParameter("eid")));
			req.setAttribute("res", res);
			req.setAttribute("desc", req.getParameter("desc"));
			req.setAttribute("assembly", req.getParameter("assembly"));
			req.setAttribute("cdate", req.getParameter("cdate"));
			
			req.getRequestDispatcher("results.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
