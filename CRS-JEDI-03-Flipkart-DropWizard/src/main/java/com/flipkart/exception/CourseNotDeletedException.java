/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception course is deleted from catalog
 * @author JEDI-07
 *
 */
public class CourseNotDeletedException extends Exception{
private int courseId;
	
	public CourseNotDeletedException(int courseId)
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
	 * Message thrown by exception
	 */
	@Override
	public String getMessage()
	{
		return "Course with courseCode: " + courseId + " can not be deleted.";
	}
}
