package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import javafx.util.Pair;

import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Professor Operations
 *
 */
public interface ProfessorInterface {
    /**
     * Method for Professor to grade the student on course
     * @param studentId
     * @param courseId
     * @param grade
     */
    public void addGrade(int studentId, int courseId, double grade);

    /**
     * Method for viewing enrolled students in a course
     * @param courseId
     * @return
     */
    public List<String> viewEnrolledStudents(int courseId);

    /**
     * Method to view all courses taught by the professor
     * @param profId
     * @return
     */
    public List<Pair<Integer,String>> getCourses(int profId);
}
