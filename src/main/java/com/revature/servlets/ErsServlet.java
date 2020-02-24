package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
		response.setStatus(200);
		response.setContentType("application/json");
		
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve if they're a manager and they're requesting record
		//, approve if they're a manager that sent an update and a reimbursement form
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
		
		ObjectMapper om = new ObjectMapper();
		// TODO will be removed once an actual user is passed with the get method to determine intentions, using one that exists in db
//		User theUser = new User(0, "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", 1);//used previously for testing
	   
		
		User theUser = om.readValue(request.getReader(), User.class);//aquired through postman/front-end supplied json data- { "userID" : "3", "userName" : "manager1", "firstName" : "Bob" , "lastName" : "Bobbys" , "email" : "bob@bobbys.com" , "userRoleId" : "1"  }
		om.read
		System.out.println("The user's toString method results in...:" + theUser.toString());
		int userRole = theUser.getUserRoleId();
		
		//if their role is an employee, let them create only
		if (userRole == 2) {//2 represents employee
			System.out.println("role is employee");
			response.setStatus(200);
			return;
		}
		
		else if (userRole ==1){
			System.out.println("role is manager");

			//what is their intention? retrieval or approval?
			Reimbursement reimbSentByJson = null;
			System.out.println("reimbSentbyJson default value(no args) equals null is: "+ reimbSentByJson.equals(null));
			try {
				reimbSentByJson = om.readValue(request.getReader(), Reimbursement.class);
				System.out.println("Reimbursment was sent (ideally)");
				//route them to update dao if this doesn't throw exception
				
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception was thrown when attempting to  create reimbursement from json");
			}
			
			if (reimbSentByJson == null){
				ObjectNode node = new ObjectMapper().readValue(request.getReader(), ObjectNode.class);
				if (node.has("reimbTypeFilter")) {
				    System.out.println("reimbTypeFilter: " + node.get("reimbTypeFilter"));
				}   
				//they're intention is retrieving reimbursements by type
			}
			
			
		}
		
		
		
		
		else {
			response.setStatus(404);
			return;
		}
//	    
//		if (!theUser.equals(null)) {
//			if (theUser.getUserRoleId()== 1) {	//if the roleId is 1, they are a manager and wanna see reimbursements...
//				ArrayList<Reimbursement> reimbsRequested = reimDao.extractReimbursementsByStatus(theUser, ReimbStatus.PENDING);
//				System.out.println("jank debugging after getUserRoleId");
//				om.writeValue(response.getWriter(), reimbsRequested);//return as JSON using marshalling
//				
//			}
//			else {//TODO add logic for a get request of a user
//				System.out.println("jank debugging User is not a manager...");
//				response.getWriter().append("User is not a manager").append(request.getContextPath());
//			}
//			
//		}
	

	}

}
