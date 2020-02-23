package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.ReimbStatus;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementImp implements Ireimbursement {

	@Override
	public ArrayList<Reimbursement> extractReimbursementsByStatus(User manager, ReimbStatus typeRequested) {
		//extract whats needed from the objects to accomplish the task
		ArrayList<Reimbursement> managerList = new ArrayList<Reimbursement>();
		String reimbStatusParam = "";

		if (manager.getUserRoleId() == 1) {//must be a manager

		switch(typeRequested) {
			case PENDING: reimbStatusParam = "PENDING";
				
			case APPROVED:reimbStatusParam = "APPROVED";
				
			case REJECTED:reimbStatusParam = "REJECTED";
			
		}
		try(Connection connection = com.revature.util.ConnectionUtil.getConnection()){
			String sql= "SELECT ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount, ers_reimbursement.reimb_resolved, ers_reimbursement.reimb_submitted, " + 
					"ers_reimbursement.reimb_description, ers_reimbursement.reimb_receipt, ers_reimbursement.reimb_author, ers_reimbursement.reimb_resolver, ers_reimbursement.reimb_status_id FROM ers_users " + 
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
				managerList.add(new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimbResolved"),rs.getString("reimbDescription"), rs.getString("reimb_receipt"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimbTypeId")));
			}
		}
		catch(SQLException e) {
			System.out.println("Sql exception happened yo");
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
		return false;
	}

	@Override
	public boolean approveOrDeny(User adminErs, Reimbursement reimbursement, boolean isApproved) {
		// TODO Auto-generated method stub
		return false;
	}

}
