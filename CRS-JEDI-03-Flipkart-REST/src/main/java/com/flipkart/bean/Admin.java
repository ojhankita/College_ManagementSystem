package com.flipkart.bean;

/**
 *
 * @author JEDI-07
 * Admin Class
 *
 */

public class Admin extends User{
	private int adminID;

	public Admin(int userId, String name, String gender, String password, String address, int adminId, String role) {
		super(userId, name, gender, password, address,role);
		this.adminID = adminId;
	}

	/**
	 * Method to get Admin ID
	 * @return Admin ID
	 */

	public int getAdminID() {
		return adminID;
	}

	/**
	 * Method to set Admin ID
	 * @param adminID
	 */

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	 
	 
	 
	 
	 
}
