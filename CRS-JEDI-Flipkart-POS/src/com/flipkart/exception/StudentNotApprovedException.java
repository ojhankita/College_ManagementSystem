package com.flipkart.exception;

/**
 * Exception to check if user is approved by administration
 * @author JEDI-07
 *
 */
public class StudentNotApprovedException extends Exception{
	private int studentId;
	
	/**
	 * Constructor
	 * @param studentId
	 */
	public StudentNotApprovedException(int studentId) {
		this.studentId = studentId;
	}


	@Override
	public String getMessage() {
		return  "Student with "+studentId+" not approved!";
	}

}
