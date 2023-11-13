/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception to check if user exists 
 * @author JEDI-07
 *
 */
public class UserNotFoundException extends Exception {

	private int userId;

	/***
	 * Getter function for UserId
	 * @param userId
	 */
	public UserNotFoundException(int userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "User with userId: " + userId + " not found.";
	}

}
