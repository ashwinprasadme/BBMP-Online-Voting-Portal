package com.aklc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aklc.pojo.Election;
import com.aklc.pojo.Participants;
import com.aklc.util.DBHelper;
import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class ElectionDAOImpl implements ElectionDAO {

	@Override
	public List<Election> read() throws Exception {
		try {
			List<Election> result = new ArrayList<Election>();

			Connection con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select * from ELECTION");
			while (rs.next()) {
				Election e = new Election();
				e.setId(rs.getInt("eid"));
				e.setDescp(rs.getString("descp"));
				e.setAssembly(rs.getString("assembly"));
				e.setCommencedate(rs.getDate("commencedate"));
				e.setBegintime(rs.getTime("begintime"));
				e.setEndtime(rs.getTime("endtime"));
				e.setState(rs.getString("state"));
				result.add(e);
			}
			DBHelper.closeConnectionFromECDB(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Participants> getparticipants(int eid) throws Exception {
		try {
			List<Participants> result = new ArrayList<Participants>();

			Connection con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select * from PARTICIPANTS where eid='" + eid + "' ");
			while (rs.next()) {
				Participants p = new Participants();
				p.setEid(eid);
				p.setSlno(rs.getInt("slno"));
				p.setName(rs.getString("name"));
				p.setPartyName(rs.getString("pname"));
				result.add(p);
			}
			DBHelper.closeConnectionFromECDB(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<String> getPartyNames() throws Exception {
		try {
			List<String> result = new ArrayList<String>();

			Connection con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select partyName from PARTY");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			DBHelper.closeConnectionFromECDB(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateElectionStatus() throws Exception {
		try {

			Connection con = DBHelper.getConnectionToECDB();

			con.createStatement()
					.execute(
							"update ELECTION set state='LIVE' where commencedate=CURRENT_DATE and begintime<CURRENT_TIME and endtime>CURRENT_TIME ");
			con.createStatement()
					.execute(
							"update ELECTION set state='END' where commencedate<CURRENT_DATE OR (commencedate=CURRENT_DATE and begintime<CURRENT_TIME and endtime<CURRENT_TIME) ");

			DBHelper.closeConnectionFromECDB(con);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<Integer> getEIDsForUser(String email) throws Exception {
		try {
			List<Integer> result = new ArrayList<Integer>();

			Connection con = DBHelper.getConnectionToVoterDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select eid from HISTORY where email='" + email + "' ");
			while (rs.next()) {
				result.add(rs.getInt(1));
			}
			DBHelper.closeConnectionFromVoterDB(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
