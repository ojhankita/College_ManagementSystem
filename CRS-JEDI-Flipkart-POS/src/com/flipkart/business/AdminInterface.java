package com.flipkart.business;

import javafx.util.Pair;

/**
 *
 * @author JEDI-07
 * Interface for Admin Operations
 *
 */

public interface AdminInterface {

	/**
	 * Method to add professor
	 * @param name
	 * @param gender
	 * @param password
	 * @param address
	 * @param designation
	 * @param department
	 * @return
	 */
	public Pair<Integer, Integer> addProfessor(String name, String gender, String password, String address, String designation, String department);

	/**
	 * Method to approve a student
	 * @param studentId
	 */
	public void approveStudent(int studentId);

	/**
	 * Method to add courses in the catalog
	 * @return
	 */
	public int addCourse(String courseName, int instructorID, Integer semester);

	/**
	 * Method to delete courses in the catalog
	 * @param courseID
	 */
	public void deleteCourse(int courseID);

	/**
	 * Method to approve student's selected course
	 * @param studentId
	 * @param courseId
	 */
    void approveCourse(int studentId, int courseId);
}
