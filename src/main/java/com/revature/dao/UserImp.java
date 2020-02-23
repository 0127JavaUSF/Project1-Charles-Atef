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
	public String hashPassword() throws SQLException {
		//String hashedPassword = BCrypt.hashpw(password1,BCrypt.gensalt());
		String password = null;
		String hashedPassword = null;
		try (Connection connection = ConnectionUtil.getConnection()){

			String sql = "SELECT * FROM " + USER_TABLE;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet =statement.executeQuery();
			if (resultSet.next()){
				int userID = resultSet.getInt("ers_user_id");
				password = resultSet.getString("ers_password");
				hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
				String sql2 = "UPDATE " + USER_TABLE + "SET ers_password = " + hashedPassword
						+ "Where ers_user_id = " + userID;
				PreparedStatement statement1 = connection.prepareStatement(sql2);
				statement1.executeUpdate();
				connection.commit();
			}
		} catch (SQLException e) {
			throw new SQLException();
		}


		return hashedPassword;
	}


}
