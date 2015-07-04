package com.aklc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.aklc.util.DBHelper;

public class KeyDAO {
	public void storeKey(String email, String pubKey) throws Exception {

		try {
			Connection con = DBHelper.getConnectionToECDB();
			Timestamp expTime = new Timestamp(
					System.currentTimeMillis() + 300000);
			con.createStatement().execute(
					"insert into USERKEYS (email, pubKey, validtill) values('"
							+ email + "','" + pubKey + "', '" + expTime + "')");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public String getKey(String email) throws Exception {
		Connection con = null;
		try {
			con = DBHelper.getConnectionToECDB();
			ResultSet rs = con.createStatement().executeQuery(
					"select pubKey from USERKEYS where email='" + email
							+ "' and validtill > CURRENT_TIMESTAMP ORDER BY validtill desc ");
			if (rs.next()) {
				return rs.getString(1);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			con.close();
		}
	}
}
