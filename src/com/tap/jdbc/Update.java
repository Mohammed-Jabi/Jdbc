package com.tap.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Update {

	public static void main(String[] args) {
		
		FileInputStream fis = null;
		Properties p = null;
		Connection myCon = null;
		Statement myStmt=null;
		ResultSet res =null;

		
		String sql = "UPDATE `employees` SET `salery` = `salery` + 10000 WHERE `department` = 'IT' ";

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			fis= new FileInputStream("C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\mySqlInfo.properties");
			p=new Properties();
			p.load(fis);
			
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			
			myCon = DriverManager.getConnection(url,username,password);
			
			myStmt = myCon.createStatement();
			
			int i = myStmt.executeUpdate(sql);
			
			
			Employee.display(myStmt, res);
			
			
			
		} 
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				close(res, myStmt, myCon, p, fis);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public static void close(ResultSet res, Statement myStmt, Connection myCon, Properties p, FileInputStream fis) throws SQLException {
		
		try {
			if(res != null) {
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(res != null) {
				myStmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(res != null) {
				myCon.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
