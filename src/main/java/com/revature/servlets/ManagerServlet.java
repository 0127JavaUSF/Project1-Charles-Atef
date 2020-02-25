package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImp;
import com.revature.model.ReimbStatus;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ManagerServlet extends HttpServlet {

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		User user =  returnManager(request, response);//validates the session
		if (user == null) {
			return;
		}
		//request should indicate their intention for which status of reimbursement 
		String reimbStatusRequested = request.getParameter("statusType");//this assumes a button on the ui for submitting a form
		ReimbursementImp reimDao = new ReimbursementImp();
		ReimbStatus statusEnum = null;
		System.out.println("The actual string of the parameter sent is: " + reimbStatusRequested);
		if(reimbStatusRequested.contentEquals("PENDING")){
			statusEnum = ReimbStatus.PENDING;
		}
		else if(reimbStatusRequested.contentEquals("APPROVED")) {
			statusEnum = ReimbStatus.APPROVED;
		}
		else {
			statusEnum = ReimbStatus.REJECTED;
		}
		
		
		ArrayList<Reimbursement> reimbArr = reimDao.extractReimbursementsByStatus(user, statusEnum);
		//get reimbursements by type request
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), reimbArr);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//approve or deny a 'PENDING' reimbursement
		User user =  returnManager(request, response);//validates the session
		if (user == null) {
			return;
		}
		ReimbursementImp reimbDao = new ReimbursementImp();
		String reimbursementIdString = request.getParameter("reimbursementId");//get the associated id of a particular reimbursement div/card
		String managerChoice = request.getParameter("managerChoice");//get the action the manager chose to perform, expecting "APPROVED" OR "DENIED" STRING LITERAL
		int reimbursementId = Integer.parseInt(reimbursementIdString);
		
		if (reimbDao.approveOrDeny(user, reimbursementId, managerChoice)) {
			response.setStatus(201);//updated successfully
			return;
		}
		else {
			response.setStatus(400);
			return;
		}
		
		
		
		
	}
	private User returnManager(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		boolean isNull = !(session == null);
		System.out.println("Session is valid ?" + isNull);
		//check if they're a manager with the session variable
		User user = null;
		try {
			 user = (User) session.getAttribute("User");
			 if (!(user.getUserRoleId() == 1)) {  //they are not a manager
				 response.setStatus(401);
	
			 }
		}
		catch(Exception e){//in case the session doesn't have the values for whatever reason(unknown unknowns)
			response.setStatus(401);
			e.printStackTrace();

		}
		return user;
		
	}
}
