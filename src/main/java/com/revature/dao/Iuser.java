package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.User;

public interface Iuser {
	
	boolean logIn(String username, String password,User User) throws SQLException;
	String hashPassword() throws SQLException;

}
