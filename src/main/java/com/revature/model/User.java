package com.revature.model;

import java.util.ArrayList;

public class User {
	


	private ArrayList<Reimbursement> managerViewableReimbs;
    private int userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
	private int userRoleId;
    public User(int userID, String userName, String firstName, String lastName, String email, int userRoleId) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRoleId = userRoleId;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public ArrayList<Reimbursement> getManagerViewableReimbs() {//will return null for regular users
		return managerViewableReimbs;
	}
	public void setManagerViewableReimbs(ArrayList<Reimbursement> managerViewableReimbs) {//will be used to set the current Reimbs viewable by manager
		this.managerViewableReimbs.clear();//clears out previous reimbs
		this.managerViewableReimbs = managerViewableReimbs;
	}
	@Override
	public String toString() {
		return "User [managerViewableReimbs=" + managerViewableReimbs + ", userID=" + userID + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", userRoleId="
				+ userRoleId + "]";
	}
	
    
}
