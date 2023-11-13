package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.constant.ConsoleColors;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author Pulkit
 */

public class StudentOperation implements StudentInterface{
    private static Logger logger = Logger.getLogger(StudentOperation.class);

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
    @Override
    public Pair<Integer, Integer> register(String name, String password, String gender, String branch, int semester, String address) {
        logger.info("register()");
        logger.debug(name+" "+branch+" "+address);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        return studentDao.register(name, password, gender, branch, semester, address);
    }

    /**+
     * Method to check Approval status of Student
     * @param studentId
     * @return Approval Status
     */
    @Override
    public boolean isApproved(int studentId) {
        logger.info("isApproved()");
        logger.debug(studentId);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        return studentDao.isApproved(studentId);
    }

    /**
     * Make Payment
     *
     * @param studentID
     * @param semester
     */
    @Override
    public void makePayment(int studentID, int semester) {
        logger.info("makePayment()");
        logger.debug(studentID+" "+semester);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        Notification notification = studentDao.makePayment(studentID, semester);

        logger.info(notification.getNotificationMessage());

        NotificationDaoInterface notificationDao = new NotificationDaoOperation();
        notificationDao.saveNotification(notification);

    }

    /**
     * Method to get Registered Courses
     *
     * @param studentID
     * @return
     */
    @Override
    public List<Course> getRegisteredCourses(int studentID) {
        logger.info("getRegisteredCourses()");
        logger.debug(studentID);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        return studentDao.getRegisteredCourses(studentID);
    }

    /**
     * Method to get available courses
     *
     * @param semester
     * @return
     */
    @Override
    public List<Course> getCourses(int semester) {
        logger.info("getCourses()");
        logger.debug(semester);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        return studentDao.getCourses(semester);
    }

    /**
     * Method to add course
     *
     * @param courseId
     * @param studentId
     */
    @Override
    public void addCourse(int courseId, int studentId) throws CourseLimitExceedException {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        List<Course> selectedCourses = studentDao.getSelectedCourses(studentId);
        logger.info("addCourse()");
        logger.debug(studentId+" "+courseId);
        if(selectedCourses.size() >= 6){
            throw new CourseLimitExceedException(selectedCourses.size());
        }
        try{
            studentDao.addCourse(studentId, courseId);
        }catch (CourseNotFoundException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);

        }

    }

    /**
     * Method to drop course
     *
     * @param courseId
     * @param studentId
     */
    @Override
    public void dropCourse(int courseId, int studentId) {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        logger.info("dropCourse()");
        logger.debug(studentId+" "+ courseId);
        try{
            studentDao.dropCourse(studentId, courseId);
            System.out.println(ConsoleColors.GREEN+"Course Deleted Successfully "+ConsoleColors.RESET);
        }catch (CourseNotFoundException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);
        }

    }



    /**+
     * Method to view Student's grade card
     * @param studentID
     * @param semester
     * @return grade card
     */
    @Override
    public Grade viewGradeCard(int studentID, int semester) {
        StudentDaoInterface studentDao = new StudentDaoOperation();
        try{
            return studentDao.viewGradeCard(studentID, semester);
        }catch (GradeNotAddedException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);
            return null;
        }

    }

    /**+
     *
     * @param studentId
     */
    @Override
    public void registerForCourses(int studentId) {
        logger.info("registerForCourses()");
        logger.debug(studentId);
        StudentDaoInterface studentDao = new StudentDaoOperation();
        List<Course> selectedCourses = studentDao.getSelectedCourses(studentId);

        System.out.println("These are the selected Courses:");
        System.out.println("-------------------------------");
        System.out.printf("%-6s%20s\n","COURSE ID", "COURSE NAME");
        for(Course course: selectedCourses){
            System.out.printf("%-6d%15s\n",course.getCourseID(),course.getCourseName());
            //System.out.println(course.getCourseID() + " " + course.getCourseName());
        }

        if(selectedCourses.size()<6){
            System.out.println("Select at least 6 courses to register");
        }else{
            List<Course> registeredCourses = studentDao.getRegisteredCourses(studentId);
            if(registeredCourses.size()>=4){
                System.out.println("Registration Completed");
            }else{
                System.out.println("Administrator approval pending.");
            }
        }
    }
}
