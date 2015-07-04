package com.aklc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aklc.pojo.Result;
import com.aklc.util.DBHelper;

public class ResultDAOImpl implements ResultDAO {

	@Override
	public void vote(String assembly, String pname, String name, String eid,
			String email) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();

			PreparedStatement ps = con
					.prepareStatement("select count(*) from RESULT where eid=? and name=? and pname=? and assembly=?");
			ps.setInt(1, Integer.parseInt(eid.trim()));
			ps.setString(2, name);
			ps.setString(3, pname);
			ps.setString(4, assembly);

			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				PreparedStatement ps2 = con
						.prepareStatement("insert into RESULT values (?,?,?,?, 1) ");
				ps2.setInt(1, Integer.parseInt(eid.trim()));
				ps2.setString(2, name);
				ps2.setString(3, pname);
				ps2.setString(4, assembly);
				ps2.execute();
			} else {
				PreparedStatement ps2 = con
						.prepareStatement("update RESULT set votecount = votecount+1 where eid=? and name=? and pname=? and  assembly=?");
				ps2.setInt(1, Integer.parseInt(eid.trim()));
				ps2.setString(2, name);
				ps2.setString(3, pname);
				ps2.setString(4, assembly);
				ps2.execute();
			}

			Connection con2 = DBHelper.getConnectionToVoterDB();
			con2.createStatement().execute(
					"insert into HISTORY values ('" + email + "','" + eid
							+ "') ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnectionFromECDB(con);
		}
	}

	public List<Result> getResults(int eid) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select * from RESULT where eid='" + eid
							+ "' order by votecount desc");

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
