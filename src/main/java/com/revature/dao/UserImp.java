package com.revature.dao;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp implements Iuser {

	protected static final String USER_TABLE = "ers_users";
	public User logIn(String username, String password) {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hashPAssword(String password1) throws SQLException {
		//String hashedPassword = BCrypt.hashpw(password1,BCrypt.gensalt());
		String password = null;
		try (Connection connection = ConnectionUtil.getConnection()){

			String sql = "SELECT * FROM " + USER_TABLE;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet =statement.executeQuery();
			if (resultSet.next()){
				password = resultSet.getString("ers_password");
			}
		} catch (SQLException e) {
			throw new SQLException();
		}
		String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
		return hashedPassword;
	}


}
