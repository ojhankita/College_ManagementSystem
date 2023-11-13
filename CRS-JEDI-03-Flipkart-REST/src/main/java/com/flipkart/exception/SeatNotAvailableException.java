package com.flipkart.exception;

/**
 * Exception to check if seats are available for course registration
 * @author JEDI-07
 *
 */
public class SeatNotAvailableException extends Exception{
	
	private int courseId;

	/**
	 * Constructor
	 * @param courseId
	 */
	public SeatNotAvailableException(int courseId)
	{	
		this.courseId = courseId;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseId;
	}


}
