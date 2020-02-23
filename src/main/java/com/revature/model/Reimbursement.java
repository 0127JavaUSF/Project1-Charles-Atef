package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
    private int reimId;
    private double reimbAmount;
    private Timestamp reimbSubmitted;
    private Timestamp reimbResolved;
    private String reimbDescription;

	private String reimbReceipt;
    private int reimbAuthor;
    private int reimbResolver;
    private int reimbStatusId;
    public int getReimId() {
		return reimId;
	}
    
    
    public Reimbursement(int reimId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, String reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusId,
			int reimbTypeId) {
		super();
		this.reimId = reimId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}



	public void setReimId(int reimId) {
		this.reimId = reimId;
	}
	public double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}
	public Timestamp getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public String getReimbReceipt() {
		return reimbReceipt;
	}
	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public int getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public int getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public int getReimbStatusId() {
		return reimbStatusId;
	}
	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}
	public int getReimbTypeId() {
		return reimbTypeId;
	}
	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}
	private int reimbTypeId;
	
    

}
