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
		if (manager.getUserRoleId() == 2) {//must be a manager

		switch(typeRequested) {
			case PENDING: reimbStatusParam = "PENDING";
				
			case APPROVED:reimbStatusParam = "APPROVED";
				
			case REJECTED:reimbStatusParam = "REJECTED";
			
		}
		try(Connection connection = com.revature.util.ConnectionUtil.getConnection()){
			String sql= "SELECT ers_username, ers_user_roles.user_role, ers_reimbursement_type.reimb_type, ers_reimbursement.reimb_description,  ers_reimbursement_status.reimb_status FROM ers_users " + 
					" JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id " + 
					" JOIN ers_reimbursement ON ers_users.ers_user_id = ers_reimbursement.reimb_author " + 
					" JOIN ers_reimbursement_type ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id " + 
					" join ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id " + 
					" WHERE ers_reimbursement_status.reimb_status = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, reimbStatusParam);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		}
		else {
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
