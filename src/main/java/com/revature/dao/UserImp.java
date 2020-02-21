package com.revature.dao;



import com.revature.Exceptions.InvalidLogInException;
import com.revature.Exceptions.UserNameException;
import com.revature.util.ConnectionUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp implements Iuser {

	protected static final String USER_TABLE = "ers_users";
	public boolean logIn(String username, String password) throws SQLException {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "Select * from " + USER_TABLE;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String table_password = resultSet.getString("ers_password"); //password from the table
				String user_name = resultSet.getString("ers_username");
				if (user_name == user_name){
					if (password == table_password){
						return true;
					} else {
						throw new InvalidLogInException("Wrong Password");
					}
				} else {
					throw new UserNameException("User name not correct");
				}

			}
			
		} catch (SQLException | InvalidLogInException | UserNameException e) {
			// TODO: handle exception
			throw new SQLException();
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String hashPAssword() throws SQLException {
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
