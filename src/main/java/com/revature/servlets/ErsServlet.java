package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
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
//		User theUser = new User(0, "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", "doesn'tmatter", 1);//used previously for testing
	    int intUserId;
	    int intUserRoleId;
	    
		String userID = request.getParameter("userId");
	    System.out.println("The parameter userId is of value: "+ userID);
	    String userName = request.getParameter("userName");
	    System.out.println("The parameter userName is of value: "+ userName);
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String email = request.getParameter("email");
	    String userRoleId = request.getParameter("userRoleId");
	    System.out.println("The parameter userRoleId is of value" + userRoleId);
	    
	    intUserId = Integer.valueOf(userID);
	    intUserRoleId = Integer.valueOf(userRoleId);
	    
		User theUser = new User(intUserId, userName, firstName, lastName, email, intUserRoleId);
//		try{
////			 theUser = om.readValue(request.getReader(), User.class); 	//unmarshall
//		}
//		catch(MismatchedInputException e) {
//			System.out.println("No user is currently logged in...");
//			e.printStackTrace();
//		}
		System.out.println("The role id of the user is..." + theUser.getUserRoleId());
		if (!theUser.equals(null)) {
			if (theUser.getUserRoleId()== 1) {	//if the roleId is 1, they are a manager and wanna see reimbursements...
				ArrayList<Reimbursement> reimbsRequested = reimDao.extractReimbursementsByStatus(theUser, ReimbStatus.PENDING);
				System.out.println("jank debugging after getUserRoleId");
				om.writeValue(response.getWriter(), reimbsRequested);//return as JSON using marshalling
				
			}
			else {//TODO add logic for a get request of a user
				System.out.println("jank debugging User is not a manager...");
				response.getWriter().append("User is not a manager").append(request.getContextPath());
			}
			
		}
		
		
		
	}

	private int parseInt(String parameter) {
		// TODO Auto-generated method stub
		return 0;
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
