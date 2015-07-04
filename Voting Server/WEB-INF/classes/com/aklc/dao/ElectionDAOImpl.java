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
	public void write(Election e) throws Exception {
		try {
			Connection con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("insert into ELECTION(descp,assembly,commencedate,begintime,endtime,state) values(?,?,?,?,?,?) ");
			ps.setString(1, e.getDescp());
			ps.setString(2, e.getAssembly());
			ps.setDate(3, e.getCommencedate());
			ps.setTime(4, e.getBegintime());
			ps.setTime(5, e.getEndtime());
			ps.setString(6, e.getState());
			ps.execute();
			DBHelper.closeConnectionFromECDB(con);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void delete(int eid) throws Exception {
		try {
			Connection con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("delete from ELECTION where eid=?");
			ps.setInt(1, eid);
			ps.execute();
			DBHelper.closeConnectionFromECDB(con);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
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
	public void removeParticipant(Participants p) throws Exception {

		try {
			Connection con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("delete from PARTICIPANTS where eid=? and name=? and pname=? ");
		
			ps.setInt(1, p.getEid());
			ps.setString(2, p.getName());
			ps.setString(3, p.getPartyName());
			ps.execute();
			DBHelper.closeConnectionFromECDB(con);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	@Override
	public void addParticipant(Participants p) throws Exception {
		try {
			Connection con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("insert into PARTICIPANTS values(?,?,?,?) ");

			ps.setInt(1, p.getEid());
			ps.setString(2, p.getName());
			ps.setString(3, p.getPartyName());
			ps.setInt(4, p.getSlno());

			ps.execute();
			DBHelper.closeConnectionFromECDB(con);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
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

}
