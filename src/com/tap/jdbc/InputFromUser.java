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
import java.util.Scanner;

public class InputFromUser {

	public static void main(String[] args) throws SQLException {

		FileInputStream fis = null;
		Properties p = null;
		Connection myCon = null;
		Statement myStmt=null;
		ResultSet res =null;
		Scanner scan = new Scanner(System.in);
		String choice;
		int count = 1010;


		String sql = "INSERT into `employees`(`id`,`name`,`email`,`salery`,`department`) values(?,?,?,?,? ) ";

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
			
			PreparedStatement myPrepStmt = myCon.prepareStatement(sql);
			
			do {
				
				myPrepStmt.setInt(1, count++);
				myPrepStmt.setString(2,scan.next());
				myPrepStmt.setString(3,scan.next());
				myPrepStmt.setInt(4,scan.nextInt());
				myPrepStmt.setString(5,scan.next());
				
				myPrepStmt.addBatch();
				
				
				
				//myPrepStmt.executeUpdate();
				
				System.out.println("Yes/No");
				choice = scan.next();
			}while(choice.equals("yes"));
			
			myPrepStmt.executeBatch();

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


