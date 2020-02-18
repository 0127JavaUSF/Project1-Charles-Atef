package com.project1.atefcharles.project1_atef_charles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project1.atefcharles.util.ConnectionUtil;



/**
 * Hello world!

 *another subtle change
=======
 *subtle change

 */
public class App 
{
    public static void main( String[] args )
    {//sublte change
       //test connectivity
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT id, build_name, apartment_number FROM houses " +
					"WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setInt(1, 1);
			
			ResultSet result = statement.executeQuery();
			System.out.println("great we have db connection per usual...");
			if(result.next()) {
				String buildName = result.getString("build_name");
				String apartmentNumber = result.getString("apartment_number");

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
	
    }
}
