package com.flipkart.exception;


/**
 * Exception to check if course is available in catalog
 * @author JEDI-07
 *
 */
public class CourseNotFoundException extends Exception{
	private int courseId;
	
	public CourseNotFoundException(int courseId)
	{	
		this.courseId = courseId;
	}

	/**
	 * Getter function for course code
	 * @return
	 */
	public int getCourseId()
	{
		return courseId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return "Course with courseCode: " + courseId + " not found.";
	}
}
