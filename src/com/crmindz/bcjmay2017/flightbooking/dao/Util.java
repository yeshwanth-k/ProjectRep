package com.crmindz.bcjmay2017.flightbooking.dao;

import java.sql.DriverManager;

import java.sql.Connection;

public class Util {
	static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mydb";
			String name = "root";
			String password = "root";

			con = DriverManager.getConnection(url, name, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
