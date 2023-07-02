package com.tap.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Clob {

	public static void main(String[] args) throws SQLException {

		FileInputStream fis = null;
		Properties p = null;
		Connection myCon = null;
		Statement myStmt = null;
		ResultSet res = null;

		String sql = "UPDATE employees SET profile=? WHERE id=1002";
		String myFilePath = "C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\About.txt";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			fis = new FileInputStream(
					"C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\mySqlInfo.properties");
			p = new Properties();
			p.load(fis);

			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");

			myCon = DriverManager.getConnection(url, username, password);

			PreparedStatement pstmt = myCon.prepareStatement(sql);
			
			FileInputStream reader = new FileInputStream(myFilePath);
			
			pstmt.setBinaryStream(1, reader);
			
			int i = pstmt.executeUpdate();
			System.out.println(i);
			
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(res, myStmt, myCon, p, fis);
		}

	}

	private static void close(ResultSet res, Statement myStmt, Connection myCon, Properties p, FileInputStream fis) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (res != null) {
				myStmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (res != null) {
				myCon.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
