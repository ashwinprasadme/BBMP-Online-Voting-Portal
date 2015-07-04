
package com.aklc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aklc.pojo.User;
import com.aklc.util.DBHelper;

public class UserDAOImpl implements UserDAO {

	@Override
	public String getStatus(String email) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select status from USER where email=? ");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (Exception e) {
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);

		}

	}

	public InputStream getIdProof(String email) throws Exception {
		Connection con = null;
		List<User> result = new ArrayList<User>();
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select idproofdoc from USER where email=? ");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getBinaryStream(1);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);
		}

	}

	public User getDetails(String email) throws Exception {
		Connection con = null;
		List<User> result = new ArrayList<User>();
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select * from USER where email=? ");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setGender(rs.getString("gender"));
				u.setDob(rs.getString("dob"));
				u.setMobile(rs.getString("mobile"));
				u.setAssembly(rs.getString("assembly"));
				result.add(u);
			}
			return result.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);
		}

	}

	@Override
	public List<User> getPendingRequest() throws Exception {
		Connection con = null;
		List<User> result = new ArrayList<User>();
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select * from USER where status='NEW' ");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setGender(rs.getString("gender"));
				u.setDob(rs.getString("dob"));
				u.setMobile(rs.getString("mobile"));
				u.setAssembly(rs.getString("assembly"));
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);
		}
	}

	@Override
	public void approve(String email) throws Exception {

		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("update USER set status='APPROVED' where email=? ");
			ps.setString(1, email);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);

		}
	}

	@Override
	public void reject(String email) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("update USER set status='REJECTED' where email=? ");
			ps.setString(1, email);

			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);

		}

	}

}
