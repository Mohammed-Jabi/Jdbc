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

public class Employee {

	public static void main(String[] args) {

		//String path = "C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\mySqlInfo.properties";
		FileInputStream fis = null;
		Properties p = null;
		
//		String url = "jdbc:mysql://localhost:3306/jdbcclass";
//		String username = "root";
//		String password = "jabi@2001";

		Statement myStmt=null;
		ResultSet res =null;
		Connection mycon =null;

		try {
			//
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			fis = new FileInputStream("C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\mySqlInfo.properties");
			p = new Properties();
			p.load(fis);
			
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			//
			//To hide the credntials
			mycon = DriverManager.getConnection(url,username,password);
			//
			myStmt= mycon.createStatement();
			//
			display(myStmt,res);


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			close(myStmt, res, mycon);
		}


	}
	//Display method
	public static void display(Statement myStmt,ResultSet res) throws SQLException {
		//ResultSet res; take this as a parameter
		res = myStmt.executeQuery("Select * from employees");

		while(res.next()) {
			//System.out.println(res.getInt("id")+" "+res.getString("name")+" "+res.getString("email")+" "+res.getInt("salery")+" "+res.getString("department")); 
		
		System.out.printf("%-2d %-15s %-27s %-7d %-1s \n",res.getInt("id"),res.getString("name"),res.getString("email"),res.getInt("salery"),res.getString("department"));
		}
	}
	
	//finally bloc method
	public static void close(Statement myStmt, ResultSet res, Connection mycon) {
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
				mycon.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
//alt+shift+m

