package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.exception.CourseLimitExceedException;
import javafx.util.Pair;

import java.util.List;

/**
 * @author Pulkit
 */

public interface StudentInterface {

    /**+
     * Method to register Student for courses
     * @param name
     * @param password
     * @param gender
     * @param branch
     * @param semester
     * @param address
     * @return
     */
    Pair<Integer, Integer> register(String name, String password, String gender, String branch, int semester, String address);

    /**+
     * Method to view Student's grade card
     * @param studentId
     * @param semester
     * @return Grade Card
     */
    Grade viewGradeCard(int studentId, int semester);

    /**+
     * Method to check Approval status of Student
     * @param userId
     * @return Approval Status
     */
    boolean isApproved(int userId);

    /**
     * Method to make Payment
     * @param studentId
     * @param semester
     */
    void makePayment(int studentId, int semester);

    /**
     * Method to get Registered Courses
     * @param studentId
     * @return
     */
    List<Course> getRegisteredCourses(int studentId);

    /**
     * Method to get available courses
     * @param semester
     * @return
     */
    List<Course> getCourses(int semester);

    /**
     * Method to add course
     * @param courseId
     * @param studentId
     */
    void addCourse(int courseId, int studentId) throws CourseLimitExceedException;

    /**
     * Method to drop course
     * @param courseId
     * @param studentId
     */
    void dropCourse(int courseId, int studentId);

    void registerForCourses(int studentId);
}
