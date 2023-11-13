package com.flipkart.dao;

import com.flipkart.business.ProfessorOperation;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoOperation implements ProfessorDaoInterface{
    Connection connection = DBUtil.getConnection();
    private static Logger logger = Logger.getLogger(ProfessorOperation.class);

    /**
     * Method for Professor to grade the student on course
     * @param studentId
     * @param courseId
     * @param grade
     */
    @Override
    public void addGrade(int studentId, int courseId, double grade) {
        //logger.info("addGrade inside ProfessorDAOOperation");
        //logger.debug("Stud id = " + studentId+" courseCode = "+courseCode+" grade = "+grade);
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);
            statement.setDouble(1, grade);
            statement.setInt(2,courseId);
            statement.setInt(3,studentId);
            statement.executeUpdate();

            logger.info("Successfully added Grade with studentId : "+studentId+" and courseId : "+courseId);
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }

    }

    /**
     * Method for viewing enrolled students in a course
     * @param courseId
     * @return
     */
    @Override
    public List<String> viewEnrolledStudents(int courseId) {
        //logger.info("viewEnrolledStudents inside ProfessorDAOOperation");
        //logger.debug("Stud id = " + studentId+" courseCode = "+courseCode+" grade = "+grade);
        List<String> students = new ArrayList<String>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
            statement.setInt(1,courseId);
            ResultSet results=statement.executeQuery();

            while(results.next())
            {
                students.add(results.getInt("studentId") +": "+results.getString("name"));

            }

            //logger.info("Successfully retrieved list of students");
            return students;
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }
        return students;
    }

    /**
     * Method to view all courses taught by the professor
     * @param profId
     * @return
     */
    @Override
    public List<Pair<Integer, String>> getCourses(int profId) {
        //logger.info("addGrade inside ProfessorDAOOperation");
        //logger.debug("Prof id = " + profId);
        List<Pair<Integer, String>> courses = new ArrayList<Pair<Integer, String>>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);
            statement.setInt(1,profId);
            ResultSet results=statement.executeQuery();
            while(results.next())
            {
                courses.add(new Pair<Integer,String>(results.getInt("courseId"),results.getString("courseName")));
            }
            //logger.info("Successfully retrieved list of courses");
            return courses;
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Method to get ProfessorId given the User ID
     * @param userId
     * @return
     */
    @Override
    public int getProfId(int userId) {
        logger.info("getProfID inside ProfessorDAOOperation");
        logger.debug("User id = " + userId);
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROFID);
            statement.setInt(1,userId);
            ResultSet results=statement.executeQuery();

            while(results.next())
            {

                //logger.info("Successfully retrieved ProfId");
                return results.getInt("profId");
            }
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }
        return 0;
    }


}
