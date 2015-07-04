
package com.aklc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aklc.pojo.User;
import com.aklc.util.DBHelper;

public class UserDAOImpl implements UserDAO {

	@Override
	public void register(User user) throws Exception {

		try {
			Connection con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("insert into USER (email, password,status, fname, lname, gender, dob, mobile, assembly) values (?,?,?,?,?,?,?,?,?) ");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getStatus());
			ps.setString(4, user.getFname());
			ps.setString(5, user.getLname());
			ps.setString(6, user.getGender());
			ps.setString(7, user.getDob());
			ps.setString(8, user.getMobile());
			ps.setString(9, user.getAssembly());

			ps.execute();
			DBHelper.closeConnectionFromVoterDB(con);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void register2(String email, File file) throws Exception {
		try {
			FileInputStream fin = new FileInputStream(file);
			Connection con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("update USER set idproofdoc=? where email=? ");
			ps.setBinaryStream(1, (InputStream) fin, (int) file.length());
			ps.setString(2, email);

			ps.execute();
			DBHelper.getConnectionToECDB();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

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

	public String login(String email, String password) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			System.out.println(email);
			System.out.println(password);
			PreparedStatement ps = con
					.prepareStatement("select status from USER where email=? and password=? ");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);

		}
	}

	@Override
	public String getAssembly(String user) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select assembly from USER where email=? ");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (Exception e) {
			return null;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);

		}

	}

}
