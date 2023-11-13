package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Abhinav
 */
public class ProfessorOperation implements ProfessorInterface {
    ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);
    /**
     * Method for Professor to grade the student on course
     * @param studentId
     * @param courseCode
     * @param grade
     */
    @Override
    public void addGrade(int studentId, int courseCode, double grade) {
        //logger.info("addGrade inside ProfessorOperation");
        //logger.debug("Stud id = " + studentId+" courseCode = "+courseCode+" grade = "+grade);
        professorDaoInterface.addGrade(studentId,courseCode,grade);
    }

    /**
     * Method for viewing enrolled students in a course
     * @param courseId
     *
     */
    @Override
    public List<String> viewEnrolledStudents(int courseId) {
        //logger.info("viewEnrolledStudents inside ProfessorOperation");
        //logger.debug("Course Id = "+courseId);
        return professorDaoInterface.viewEnrolledStudents(courseId);
    }

    /**
     * Method to view all courses taught by the professor
     * @param profId
     * @return
     */
    @Override
    public List<Pair<Integer,String>>  getCourses(int profId) {
        //logger.info("getCourses inside ProfessorOperation");
        //logger.debug("Professor Id = "+profId);
        return professorDaoInterface.getCourses(profId);
    }
}
