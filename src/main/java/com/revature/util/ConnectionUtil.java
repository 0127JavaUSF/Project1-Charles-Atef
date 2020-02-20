package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public Connection getConnection() {
		
		String url =System.getenv("JDBC_URL");
        String password = System.getenv("JDBC_PASSWORD");
        String user = System.getenv("JDBC_ROLE");
        try {
           return DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
		return null;
	}

}
