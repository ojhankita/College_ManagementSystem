package com.flipkart.dao;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import javafx.util.Pair;

public interface AdminDaoInterface {

    /**
     * Method to add a new professor
     *
     * @return
     */
    public Pair<Integer, Integer> addProfessor(String name, String gender, String password, String address, String designation, String department);

    /**
     * Method to approve a student
     */
    public void approveStudent(int studentId) throws StudentNotFoundForApprovalException;

    /**
     * Method to add courses in the catalog
     * @return
     */
    public int addCourse(String courseName, int instructorID, int semester);

    /**
     * Method to delete courses in the catalog
     */
    public void deleteCourse(int courseID) throws CourseNotFoundException;

    /**
     * Method to approve student's course
     * @param studentId
     * @param courseId
     */
    void approveCourse(int studentId, int courseId);
}
