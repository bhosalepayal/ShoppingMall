package com.app.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDbConnection {
	private static Connection connection;
	private MySqlDbConnection() {
		
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String password = "root";
		String user= "root";
		String url ="jdbc:mysql://localhost:3306/shopping";
		connection=DriverManager.getConnection(url,user,password);
		return connection;
		
		
		
	}

}
