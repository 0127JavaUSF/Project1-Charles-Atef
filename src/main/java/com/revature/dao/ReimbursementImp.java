package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.ReimbStatus;
import com.revature.model.ReimbType;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementImp implements Ireimbursement {
	
	
	private static final String REIMB = "ers_reimbursement";
	private static final String USER = "ers_user";
	//private static final Object ReimbType.OTHER = null;

	@Override
	public ArrayList<Reimbursement> extractReimbursementsByStatus(User manager, ReimbStatus typeRequested) {
		//extract whats needed from the objects to accomplish the task
		ArrayList<Reimbursement> managerList = new ArrayList<Reimbursement>();
		String reimbStatusParam = "";

		if (manager.getUserRoleId() == 1) {//must be a manager

		switch(typeRequested) {
			case PENDING: reimbStatusParam = "PENDING";
			break;
				
			case APPROVED:reimbStatusParam = "APPROVED";
			break;
				
			case REJECTED:reimbStatusParam = "REJECTED";
			break;
			
		}
		try(Connection connection = com.revature.util.ConnectionUtil.getConnection()){
			String sql= "SELECT ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_resolved, ers_reimbursement.reimb_submitted, " + 
					"ers_reimbursement.reimb_description, ers_reimbursement.reimb_receipt, ers_reimbursement.reimb_author, ers_reimbursement.reimb_resolver, "
					+ " ers_reimbursement.reimb_status_id, ers_reimbursement.reimb_type_id FROM ers_users " + 
					"JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id " + 
					"JOIN ers_reimbursement ON ers_users.ers_user_id = ers_reimbursement.reimb_author " + 
					"JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id " + 
					"join ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id  " + 
					"WHERE ers_reimbursement_status.reimb_status = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println("string being set is..." + reimbStatusParam);
			ps.setString(1, reimbStatusParam);
			ResultSet rs = ps.executeQuery();
			System.out.println("hasn't failed yet after executing query...");
			while(rs.next()) {
				
//				int reimId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
//				String reimbDescription, String reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusId,
//				int reimbTypeId
//				
//				
//				ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_resolved, 
//				ers_reimbursement.reimb_description, ers_reimbursement.reimb_receipt, ers_reimbursement.reimb_author, ers_reimbursement.reimb_resolver, ers_reimbursement.reimb_status_id
//				
				managerList.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),rs.getString("reimb_description"), rs.getString("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
		}
		catch(SQLException e) {
			System.out.println("Something went wrong during sql execution");
			e.printStackTrace();
		}
		
		}
		
		
		else {
			System.out.println("Well shit...");
			return null;
		}
		
		
		return managerList;
	}

	@Override
	public boolean addReimbursement(User employeeErs, Reimbursement ersReimbursement) {
		// TODO Auto-generated method stub
		try(Connection connection = ConnectionUtil.getConnection()){
			
			String Sql ="INSERT INTO " + REIMB+" (reimb_amount, reimb_submitted,reimb_description,reimb_receipt,reimb_author, reimb_type_id)"
					+"VALUES(?,?,?,?,?,?) RETURNING *";
			PreparedStatement statement = connection.prepareStatement(Sql);
			statement.setDouble(1, ersReimbursement.getReimbAmount());
			statement.setTimestamp(2, ersReimbursement.getReimbSubmitted());
			statement.setString(3, ersReimbursement.getReimbDescription());
			statement.setString(4, ersReimbursement.getReimbReceipt());
			statement.setInt(5, 3);
			//statement.setObject(6, ReimbType.OTHER);
			statement.setInt(6, 1);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean approveOrDeny(User adminErs, int reimbursementId, String approveOrDeny) {
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql = "update ers_reimbursement  " + 
					" SET reimb_resolved = Now(), reimb_resolver = ?, reimb_status_id = (SELECT reimb_status_id FROM ers_reimbursement_status WHERE reimb_status = ?) " + 
					" WHERE ers_reimbursement.reimb_id = ? RETURNING *";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, adminErs.getUserID());
			ps.setString(2, approveOrDeny);
			ps.setInt(3, reimbursementId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
			}
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();

		}
		return false;
	}
	




}
