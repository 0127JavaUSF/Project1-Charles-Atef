package com.revature.service;

import com.revature.dao.ReimbursementImp;
import com.revature.model.ReimbStatus;
import com.revature.model.User;

public class ManagerRetrievalService {//checks if manager is trying to access the service, then performs retrieval
	static ReimbursementImp reimbDao = new ReimbursementImp();
	public static void retrieveByStatus(User manager, ReimbStatus typeRequested){
		
		manager.setManagerViewableReimbs(reimbDao.extractReimbursementsByStatus(manager, typeRequested));
		
	}

}
