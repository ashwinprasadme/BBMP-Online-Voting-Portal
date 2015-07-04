package com.aklc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.aklc.pojo.Election;
import com.aklc.pojo.Result;
import com.aklc.util.DBHelper;

public class ResultDAOImpl implements ResultDAO {

	@Override
	public List<Election> getElectioNList() throws Exception {
		Connection con = null;
		try {
			List<Election> result = new ArrayList<Election>();
			con = DBHelper.getConnectionToECDB();
			con.createStatement()
					.executeQuery(
							"select eid, descp, assembly from ELECTION where eid in (select eid from RESULT) and state='END' ");

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromECDB(con);
		}
	}

	public List<Result> getResults(int eid) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select * from RESULT where eid='" + eid + "' order by votecount desc");

			List<Result> resultList = new ArrayList<Result>();
			while (rs.next()) {
				Result r = new Result();
				r.setEid(eid);
				r.setName(rs.getString("name"));
				r.setPname(rs.getString("pname"));
				r.setAssembly(rs.getString("assembly"));
				r.setVotecount(rs.getDouble("votecount"));
				resultList.add(r);
			}
			return resultList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromECDB(con);
		}

	}

}
