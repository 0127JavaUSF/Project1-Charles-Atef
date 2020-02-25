package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementImp;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import javafx.scene.control.Alert;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;


public class EmployeeServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DisplayMode displayMode = new DisplayMode();
		HttpSession session = request.getSession(false);
		User user = null;
		ReimbursementImp reimbursementImp = new ReimbursementImp(); //access the dao method to create a request
		Reimbursement reimbursement = new Reimbursement();
		try {
			user = (User) session.getAttribute("User");
			if (user.getUserRoleId()!= 2) {
				response.setStatus(401);
				return;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		//Employee should be able to create a reimbuiersment
		//get the request information 
		//reimbursement.setReimId(user.getUserID());
		System.out.println("In the employee dopost");
		reimbursement.setReimbAmount(Double.parseDouble(request.getParameter("amount"))); //get the value from the user
		reimbursement.setReimbDescription(request.getParameter("description"));
		reimbursement.setReimbReceipt(request.getParameter("receipt_url")); //S3 buckets 
		reimbursement.setReimbSubmitted(new Timestamp(System.currentTimeMillis()));
		reimbursement.setReimbAuthor(user.getUserID()); //logged user ID.
		boolean addReimbursement = reimbursementImp.addReimbursement(user,reimbursement); //to check if the request got through the database
		/*if (addReimbursement){
			response.getWriter().write("The request has been updated");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("You request has been added");


		}
		else {

		}*/



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);

    }
}
