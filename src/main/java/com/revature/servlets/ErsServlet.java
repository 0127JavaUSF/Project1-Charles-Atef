package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
