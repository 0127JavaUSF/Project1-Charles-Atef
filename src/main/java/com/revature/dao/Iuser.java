package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.User;

public interface Iuser {
	
	User logIn(String username, String password) throws SQLException;
	String hashPassword() throws SQLException;

}
