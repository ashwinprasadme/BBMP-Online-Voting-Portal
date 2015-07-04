
package com.aklc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.aklc.util.DBHelper;

public class OTPDAOImpl implements OTPDAO {

	@Override
	public void insertOTP(String email, String otp) throws Exception {
		try {
			Connection con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("insert into OTP values(?,?,?) ");
			ps.setString(1, email);
			ps.setString(2, otp);
			Timestamp expTime = new Timestamp(
					System.currentTimeMillis() + 300000);
			ps.setTimestamp(3, expTime);
			ps.execute();
			DBHelper.closeConnectionFromVoterDB(con);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public boolean verifyOTP(String email, String otp) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToVoterDB();
			PreparedStatement ps = con
					.prepareStatement("select count(*) from OTP where email=? and otp=? and validtill>CURRENT_TIMESTAMP");
			ps.setString(1, email);
			ps.setString(2, otp);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				return false;
			} else {
				con.createStatement().execute(
						"update OTP set validtill='0000-00-00 00:00:00' where email='"
								+ email + "' ");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBHelper.closeConnectionFromVoterDB(con);
		}
	}

}
