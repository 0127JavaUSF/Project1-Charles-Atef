package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.ReimbStatus;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementImp implements Ireimbursement {

	@Override
	public ArrayList<Reimbursement> extractReimbursementsByStatus(User employeeErs, ReimbStatus reimbStatusType) {
		//extract whats needed from the objects to accomplish the task
		String reimbStatusParam = "";
		
		
		switch(reimbStatusType) {
			case PENDING: reimbStatusParam = "PENDING";
				
			case APPROVED:reimbStatusParam = "APPROVED";
				
			case REJECTED:reimbStatusParam = "REJECTED";
			
		}
		try(Connection connection = com.revature.util.ConnectionUtil.getConnection()){
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
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
