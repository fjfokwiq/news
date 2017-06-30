package com.okw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String USERNAME="root";
	private static final String PASSWORD="1923311619";
	private static final String URL="jdbc:mysql://localhost:3306/user_message";
    private static Connection conn;
    
    static{
    	try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    public static Connection getConnection(){
    	return conn;
    }
}
