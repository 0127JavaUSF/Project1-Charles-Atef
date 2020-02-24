package com.revature.service;

import java.sql.SQLException;

import com.revature.util.PasswordUtils;

public class Hashing {
	protected static final String USER_TABLE = "ers_users";
	public String hash(String password) throws SQLException {
		//String password=null;
		String salt = "revature";
		String hashedPassword = PasswordUtils.generateSecurePassword(password,salt);

	//String password = "1234";
	//String salt = "revature";
	//String hashPassword = PasswordUtils.generateSecurePassword(password, salt);
	
	/*try (Connection connection = ConnectionUtil.getConnection()){

		String sql = "SELECT * FROM " + USER_TABLE;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet =statement.executeQuery();
		if (resultSet.next()){
			int userID = resultSet.getInt("ers_user_id");
			password = resultSet.getString("ers_password");
			hashedPassword = PasswordUtils.generateSecurePassword(password, salt);
			String sql2 = "UPDATE " + USER_TABLE + "SET ers_password = " + hashedPassword
					+ "Where ers_user_id = " + userID;
			PreparedStatement statement1 = connection.prepareStatement(sql2);
			statement1.executeUpdate();
			connection.commit();
		}
	} catch (SQLException e) {
		throw new SQLException();
	}*/
		return salt;
	}
	
	

}
