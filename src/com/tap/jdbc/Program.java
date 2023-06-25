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

public class Program {
	static	Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		FileInputStream fis = null;
		Properties p = null;
		Connection myCon = null;
		Statement myStmt = null;
		ResultSet res = null;

		String sql = "";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			fis = new FileInputStream("C:\\Users\\moham\\eclipse-workspace\\Jdbc\\src\\com\\tap\\utility\\mySqlInfo.properties");
			p = new Properties();
			p.load(fis);

			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");

			myCon = DriverManager.getConnection(url, username, password);

			myStmt = transaction(myCon);

			//myStmt.executeUpdate(sql);

			

			Employee.display(myStmt, res);

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

	public static Statement transaction(Connection myCon) throws SQLException{
		Statement myStmt;
		
		myCon.setAutoCommit(false);
		
		System.out.println("Enter the senders name");
		String sender = scan.next();

		System.out.println("Enter the receiver name");
		String receiver = scan.next();

		System.out.println("Enter the amount");
		int amount = scan.nextInt();


		String balanceQuery="SELECT salery FROM employees WHERE name = ?";
		PreparedStatement pstmt = myCon.prepareStatement(balanceQuery);
		pstmt.setString(1, sender);
		ResultSet res = pstmt.executeQuery();
		int balance = 0;
		if(res.next()) {
			balance = res.getInt("salery");
		}

		int i = updateBalance(myCon, sender, -amount);
		int j = updateBalance(myCon, receiver, amount);

		boolean flag = checkTransaction(i,j,balance,amount);
		if(flag) {
			myCon.commit();
			System.out.println("Transaction Successful");
		}else {
			myCon.rollback();
			System.out.println("Transaction Unuccessful");
		}

		myStmt = myCon.createStatement();
		return myStmt;
	}

	public static boolean checkTransaction(int i, int j, int balance, int amount) {

		System.out.println("Do you want the transaction (yes/no)");
		String choice = scan.next();
		
		return choice.equalsIgnoreCase("yes") && balance >= amount && i==1 && j==1;  
				

	}

	private static int updateBalance(Connection myCon, String sender, int amount) throws SQLException {

		String update = "UPDATE employees SET salery = salery + ? where name = ? ";
		PreparedStatement pstmt = myCon.prepareStatement(update);

		pstmt.setInt(1, amount);
		pstmt.setString(2, sender);

		int i = pstmt.executeUpdate();

		return i;

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
