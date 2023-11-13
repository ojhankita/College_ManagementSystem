package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.utils.DBUtil;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoOperation implements AdminDaoInterface {

    private static Logger logger = Logger.getLogger(AdminDaoOperation.class);

    @Override
    public Pair<Integer, Integer> addProfessor(String name, String gender, String password, String address, String designation, String department) {


        Connection connection = DBUtil.getConnection();
        int userId=0, profId=0;
        try {
            String generatedColumns[] = {"userId"};

            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY, generatedColumns);
            statement.setString(1, password);
            statement.setString(2, "Professor");
            statement.setString(3, name);
            statement.setString(4, address);
            statement.setString(5, gender);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
                String generatedColumns2[] = {"profId"};
                statement = connection.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR,generatedColumns2);
                statement.setString(2, department);
                statement.setString(3, designation);
                statement.setInt(1, userId);
                statement.executeUpdate();
                ResultSet rs2 = statement.getGeneratedKeys();
                if(rs2.next()) {
                    profId = rs2.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Professor successfully added by ADMIN");

        return new Pair<>(profId,userId);
    }

    @Override
    public void approveStudent(int studentId) throws StudentNotFoundForApprovalException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_STUDENT);
            statement.setInt(1, studentId);
            int rows = statement.executeUpdate();
            if (rows == 0)
                throw new StudentNotFoundForApprovalException(studentId);

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Student successfully approved by ADMIN");

    }

    @Override
    public int addCourse(String courseName, int instructorID, int semester) {
        Connection connection = DBUtil.getConnection();
        String generatedColumns[] = {"courseId"};
        int courseId = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_COURSE,generatedColumns);
            statement.setString(1, courseName);
            statement.setInt(2, instructorID);
            statement.setInt(3, semester);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                courseId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        logger.info("Course successfully added by ADMIN");
        return courseId;
    }

    @Override
    public void deleteCourse(int courseID) throws CourseNotFoundException {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.DELETE_COURSE);
            statement.setInt(1, courseID);
            int rows = statement.executeUpdate();
            if (rows == 0) {
                throw new CourseNotFoundException(courseID);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        logger.info("Course successfully deleted by ADMIN");
    }

    /**
     * Method to approve student's course
     *
     * @param studentId
     * @param courseId
     */
    @Override
    public void approveCourse(int studentId, int courseId) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.APPROVE_COURSE);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        logger.info("Course successfully approved by ADMIN");
    }
}
