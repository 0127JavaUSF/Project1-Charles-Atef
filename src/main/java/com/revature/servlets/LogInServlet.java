package com.revature.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserImp;
import com.revature.model.User;

/**
 * Servlet implementation class LogInServlet
 */
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		UserImp userImp = new UserImp();
		User user = null;
		HttpSession session = request.getSession();//changed 2/23 11:03 charles
		try {
			 user = userImp.logIn(name, password);
			//session.setAttribute("logged User",user.getUserID());//sets session attribute to integer
			//session.setAttribute("user role", user.getUserRoleId());//also integer
			//session.setAttribute("User name", user.getUserName());//is a string
			session.setAttribute("User",user); //User
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.writeValue(response.getWriter(),user);
		//response.getWriter().write();
		//if (session.getAttribute())
		/*if (session.getAttribute("user role").equals(1)) {
			System.out.println("User is manager, insert route here");
			RequestDispatcher rqstDispatcher = request.getRequestDispatcher("/ManagerServlet");
			rqstDispatcher.forward(request, response);
		}
		else {
			System.out.println("session attribute user role value is:" + (String)session.getAttribute("user role"));
		}*/
		
	}

}
