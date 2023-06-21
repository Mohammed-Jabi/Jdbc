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

public class Program {

	public static void main(String[] args) {

		FileInputStream fis = null;
		Properties p = null;
		Connection myCon = null;
		Statement myStmt=null;
		ResultSet res =null;


		String sql = "INSERT into `employees`(`id`,`name`,`email`,`salery`,`department`) values(1004,'Taniya', 'taniya.com', 70000, 'IT')  ";
		String sql1 = "INSERT into `employees`(`id`,`name`,`email`,`salery`,`department`) values(1005,'Arun', 'arun.com', 70000, 'IT')  ";
		String sql2 = "INSERT into `employees`(`id`,`name`,`email`,`salery`,`department`) values(1006,'Kavya', 'kavya.com', 70000, 'IT')  ";


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

			myStmt.addBatch(sql);
			myStmt.addBatch(sql1);
			myStmt.addBatch(sql2);
			
			myStmt.executeBatch();


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
			close(res, myStmt, myCon, p, fis);
		}


	}
	

	private static void close(ResultSet res, Statement myStmt, Connection myCon, Properties p, FileInputStream fis) {
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




