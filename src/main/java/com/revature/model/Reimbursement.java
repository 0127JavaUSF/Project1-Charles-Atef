package com.revature.model;

import java.security.Timestamp;
import java.sql.Blob;

public class Reimbursement {
	
	public static final int LOGING = 1;
    public static final int TRAVEL = 2;
    public static final int FOOD = 3;
    public static final int OTHER = 4;

    public static final int STATUS_PENDDING = 1;
    public static final int STATUS_APPROVED = 2;
    public static final int STATUS_REJECTED = 3;

    private int id;
    private int amount;
    private Timestamp submitted;
    private Timestamp resolved;
    private String description;
    private Blob receipt;
    private int authorID;
    private int resolveID;
    private int statusID;
    private int typeID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getReceipt() {
		return receipt;
	}
	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public int getResolveID() {
		return resolveID;
	}
	public void setResolveID(int resolveID) {
		this.resolveID = resolveID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", authorID=" + authorID + ", resolveID="
				+ resolveID + ", statusID=" + statusID + ", typeID=" + typeID + "]";
	}
	public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description, Blob receipt,
			int authorID, int resolveID, int statusID, int typeID) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.authorID = authorID;
		this.resolveID = resolveID;
		this.statusID = statusID;
		this.typeID = typeID;
	}
    
    

}
