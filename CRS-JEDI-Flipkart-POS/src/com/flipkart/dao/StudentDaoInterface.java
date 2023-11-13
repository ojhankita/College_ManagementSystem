package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;

import java.util.List;

public interface StudentDaoInterface {
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
     * @param semester
     * @return Grade Card
     */
    Grade viewGradeCard(int studentId, int semester) throws GradeNotAddedException;

    /**+
     * Method to check Approval status of Student
     * @return Approval Status
     */
    boolean isApproved(int studentId);

    /**
     * Method to make Payment
     * @param studentId
     * @param semester
     */
    Notification makePayment(int studentId, int semester);

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
     */
    void addCourse(int studentId, int courseId) throws CourseNotFoundException;

    /**
     * Method to drop course
     * @param courseId
     */
    void dropCourse(int studentId, int courseId) throws CourseNotFoundException;

    int getStudentId(int userId);

    List<Course> getSelectedCourses(int studentId);
}
