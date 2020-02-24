package com.revature.dao;



import com.revature.Exceptions.InvalidLogInException;
import com.revature.Exceptions.UserNameException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImp implements Iuser {

	protected static final String USER_TABLE = "ers_users";
	public User logIn(String username, String password) throws SQLException {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "Select * from " + USER_TABLE + " Where ers_username = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				
//				
//			    private int userID;
//			    private String userName;
//			    private String firstName;
//			    private String lastName;
//			    private String email;
//				private int userRoleId;
				int userID = resultSet.getInt("ers_user_id");
				String user_name = resultSet.getString("ers_username");
				String firstName = resultSet.getString("user_first_name");
				String lastName = resultSet.getString("user_last_name");
				String email = resultSet.getString("user_email");
				int userRoleId = resultSet.getInt("user_role_id");
				
				String table_password = resultSet.getString("ers_password"); //password from the table

				if (username.equals(user_name) ){
					if ( table_password.equals(password)){
						return new User(userID,user_name, firstName, lastName, email, userRoleId);
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
