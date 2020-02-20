package com.revature.dao;

import com.revature.model.User;

import java.sql.SQLException;

public interface Iuser {
	
	User logIn(String username, String password);
	String hashPAssword(String password) throws SQLException;

}
