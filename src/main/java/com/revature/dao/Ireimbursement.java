package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.ReimbStatus;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface Ireimbursement {
	public ArrayList<Reimbursement> extractReimbursementsByStatus(User manager, ReimbStatus typeRequested);
	public Reimbursement addReimbursement(User employeeErs, Reimbursement ersReimbursement);
	boolean approveOrDeny(User adminErs, int reimbursementId, String isApproved);
}
