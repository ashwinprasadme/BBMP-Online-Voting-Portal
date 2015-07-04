package com.aklc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aklc.util.DBHelper;

public class PartyDAOImpl implements PartyDAO {

	@Override
	public void uploadParty(String name, File logo) throws Exception {

		try {
			Connection con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("insert into PARTY  values (?,?) ");

			ps.setString(1, name);
			ps.setBinaryStream(2, (InputStream) new FileInputStream(logo),
					(int) logo.length());

			ps.execute();

			DBHelper.closeConnectionFromECDB(con);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<String> getpartyNames() throws Exception {
		List<String> result = new ArrayList<String>();
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select partyname from PARTY");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromECDB(con);
		}
	}

	@Override
	public InputStream getPartyLogo(String name) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();
			PreparedStatement ps = con
					.prepareStatement("select logo from PARTY where partyname=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getBinaryStream(1);

		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeConnectionFromECDB(con);
		}
	}

	public void deleteParty(String name) throws Exception {
		try {
			Connection con = DBHelper.getConnectionToECDB();
			con.createStatement().execute(
					"delete from PARTY where partyname='" + name + "' ");
			DBHelper.closeConnectionFromECDB(con);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
