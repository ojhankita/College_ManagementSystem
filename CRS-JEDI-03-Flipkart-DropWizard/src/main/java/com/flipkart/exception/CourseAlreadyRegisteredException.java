package com.flipkart.exception;

/**
 * Exception to check if course is already registered by student
 * @author JEDI-07
 *
 */
public class CourseAlreadyRegisteredException extends Exception{
	
	private int courseId;
	
	/**
	 * Constructor
	 * @param courseId
	 */
	public CourseAlreadyRegisteredException(int courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Getter method
	 * @return course code
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "You have already registered for " + courseId;
	}

}
