package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementImp;
import com.revature.model.ReimbStatus;
import com.revature.model.User;
import com.revature.model.*;


/**
 * Servlet implementation class ErsServlet
 */
public class ErsServlet extends HttpServlet {

     
	
	@Override
	public void init() throws ServletException{//driver will be found for postgres, or exception will occur
		try{
			System.out.println("Attempting to locate postgres driver...");
			Class.forName("org.postgresql.Driver");
			System.out.println("postgres driver found! =O");
		}
	

		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public ErsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementImp reimDao = new ReimbursementImp();
		ObjectMapper om = new ObjectMapper();
		// TODO will be removed once an actual user is passed with the get method to determine intentions, using one that exists in db
		User theUser = new User(0, "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", 1);
			
		if (theUser.getUserRoleId()== 1) {	//if the roleId is 1, they are a manager and wanna see reimbursements...
			ArrayList<Reimbursement> reimbsRequested = reimDao.extractReimbursementsByStatus(theUser, ReimbStatus.APPROVED);
			System.out.println("jank debugging after getUserRoleId");
			om.writeValue(response.getWriter(), reimbsRequested);
			
		}
		else {
			System.out.println("jank debugging User is not a manager...");
			response.getWriter().append("User is not a manager").append(request.getContextPath());
		}
		//return as JSON using marshalling
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create a reimbursement request
//		
//		try {
//		ObjectMapper om = new ObjectMapper();
//		
//		// Using Jackson to create object from JSON
//		Food food = om.readValue(request.getReader(), Food.class);
//		
//		// Stuff that should probably be happening in the service/DAO
//		food.setId(++counter);
//		foodMap.put(food.getId(), food);
//		
//		response.setStatus(201);
//		response.setContentType("application/json");
//		
//		// Using Jackson to create JSON from object
//		om.writeValue(response.getWriter(), food);
//		}
//		catch(Exception e) {
//			System.out.println("Well... post failed");
//			e.printStackTrace();
//		}
		doGet(request, response);

	}

}
