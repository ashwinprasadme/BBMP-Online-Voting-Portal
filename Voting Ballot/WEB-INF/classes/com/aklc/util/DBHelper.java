
package com.aklc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

	public static Connection getConnectionToECDB() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://---", "-", "-");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void closeConnectionFromECDB(Connection con) throws Exception {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static Connection getConnectionToVoterDB() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://---", "-", "-");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void closeConnectionFromVoterDB(Connection con)
			throws Exception {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
