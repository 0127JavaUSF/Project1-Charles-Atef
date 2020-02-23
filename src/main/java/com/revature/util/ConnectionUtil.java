package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() {
		
		String url =System.getenv("JDBC_URL1");
		System.out.println(url);
        String password = System.getenv("JDBC_PASSWORD1");
        System.out.println(password);
        String user = System.getenv("JDBC_ROLE1");
        System.out.println(user);
        try {
           return DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
		return null;
	}

}
