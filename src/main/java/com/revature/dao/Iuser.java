package com.revature.dao;

import java.sql.SQLException;

public interface Iuser {
	
	boolean logIn(String username, String password) throws SQLException;
	String hashPAssword() throws SQLException;

}
