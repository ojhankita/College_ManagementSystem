package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.utils.DBUtil;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDaoOperation implements StudentDaoInterface{

    @Override
    public Pair<Integer, Integer> register(String name, String password, String gender, String branch, int semester, String address) {
        Connection connection = DBUtil.getConnection();
        int userId=0,studentId = 0;
        try {

            String generatedColumns[] = { "userId" };

            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY, generatedColumns);
            statement.setString(1,password);
            statement.setString(2, "Student");
            statement.setString(3, name);
            statement.setString(4, address);
            statement.setString(5, gender);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                userId = rs.getInt(1);
                String generatedColumns2[] = { "studentId" };
                statement = connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT,generatedColumns2);
                statement.setString(1, branch);
                statement.setInt(2, semester);
                statement.setInt(3, userId);
                statement.executeUpdate();
                ResultSet rs2 = statement.getGeneratedKeys();
                if(rs2.next()) {
                    studentId = rs2.getInt(1);
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return new Pair<>(studentId,userId);
    }

    @Override
    public Grade viewGradeCard(int studentId, int semester) throws GradeNotAddedException {
        Connection connection=DBUtil.getConnection();
        HashMap<String, Double> grades =new HashMap<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.VIEW_GRADE);

            statement.setInt(1, studentId);
            statement.setInt(2, semester);

            ResultSet results=statement.executeQuery();
            while(results.next())
            {
                if(results.getDouble("grade") == -1){
                    throw new GradeNotAddedException(studentId);
                }
                grades.put(results.getString("courseName"), results.getDouble("grade"));
            }
            return new Grade(studentId, grades);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isApproved(int studentId) {
        Connection connection=DBUtil.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);

            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                return result.getBoolean("isApproved");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean checkPayment(int studentId){
        Connection connection=DBUtil.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.CHECK_PAYMENT);
            statement.setInt(1, studentId);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                return result.getBoolean("feesPaid");
            }
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public Notification makePayment(int studentId, int semester) {
        Connection connection=DBUtil.getConnection();

        Notification notification;

        try{
            boolean feesPaid = checkPayment(studentId);

            if(feesPaid){
                notification = new Notification(studentId, "Payment Already Done");
            }else{

                PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.MAKE_PAYMENT);
                statement.setInt(1, studentId);

                statement.executeUpdate();

                long paymentId = Instant.now().toEpochMilli();
                notification = new Notification(studentId,"Payment Approved", paymentId);

            }
            return notification;

        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<Course> getRegisteredCourses(int studentId) {
        Connection connection=DBUtil.getConnection();
        List<Course> registered_courses = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);

            statement.setInt(1, studentId);

            ResultSet results=statement.executeQuery();

            while(results.next())
            {
                registered_courses.add(new Course(
                        results.getInt("courseId"),
                        results.getString("courseName"),
                        results.getInt("profId"),
                        results.getInt("semester")
                ));
            }
            return registered_courses;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            List<Course> empty_list = new ArrayList<>();
            return empty_list;

        }

    }

    @Override
    public List<Course> getCourses(int semester) {
        Connection connection=DBUtil.getConnection();
        List<Course> registered_courses = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.VIEW_AVAILABLE_COURSES);

            statement.setInt(1, semester);

            ResultSet results=statement.executeQuery();

            while(results.next())
            {
                registered_courses.add(new Course(
                        results.getInt("courseId"),
                        results.getString("courseName"),
                        results.getInt("profId"),
                        results.getInt("semester")
                ));
            }
            return registered_courses;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addCourse(int studentId, int courseId) throws CourseNotFoundException {
        Connection connection = DBUtil.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.STUDENT_ADD_COURSE);

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new CourseNotFoundException(courseId);
            }
            else{
                System.out.println("Course added successfully !");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropCourse(int studentId, int courseId) throws CourseNotFoundException {
        Connection connection=DBUtil.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.STUDENT_DROP_COURSE);

            statement.setInt(2, studentId);
            statement.setInt(1, courseId);

            int rows = statement.executeUpdate();
            if(rows == 0){
                throw new CourseNotFoundException(courseId);
            }

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getStudentId(int userId) {
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDID);
            statement.setInt(1,userId);
            ResultSet results=statement.executeQuery();

            while(results.next())
            {
                return results.getInt("studentId");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Course> getSelectedCourses(int studentId) {
        Connection connection=DBUtil.getConnection();
        List<Course> registered_courses = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.VIEW_SELECTED_COURSES);

            statement.setInt(1, studentId);

            ResultSet results=statement.executeQuery();

            while(results.next())
            {
                registered_courses.add(new Course(
                        results.getInt("courseId"),
                        results.getString("courseName"),
                        results.getInt("profId"),
                        results.getInt("semester")
                ));
            }
            return registered_courses;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
