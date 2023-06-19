package com.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Employee {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/jdbcclass";
		String username = "root";
		String password = "jabi@2001";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Sucess");
			DriverManager.getConnection(url,username,password);
			System.out.println("Sucess");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}


