package com.flipkart.business;


import com.flipkart.constant.ConsoleColors;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import javafx.util.Pair;
import org.apache.log4j.Logger;

/**
 * @author JEDI-07
 * Implementations of Admin Operations
 */


public class AdminOperation implements AdminInterface {
    private static Logger logger = Logger.getLogger(AdminOperation.class);
    /**
     *
     * Method to add a new professor
     * @param name
     * @param gender
     * @param password
     * @param address
     * @param designation
     * @param department
     * @return
     */
    @Override
    public Pair<Integer, Integer> addProfessor(String name, String gender, String password, String address, String designation, String department) {
//        logger.info("addProfessor()");
//        logger.debug(name+" "+gender+" "+address);
        AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
        return adminDaoInterface.addProfessor(name,gender,password,address,designation,department);
    }

    /**+
     * Method to approve registration of a Student
     * @param studentId
     */
    @Override
    public void approveStudent(int studentId) {
        AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
        try{
            adminDaoInterface.approveStudent(studentId);
        }catch (StudentNotFoundForApprovalException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);
        }

    }

    /**
     * Method to add courses in the catalog
     * @param courseName
     * @param instructorID
     * @param semester
     * @return
     */
    @Override
    public int addCourse(String courseName, int instructorID, Integer semester) {
        AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
        return adminDaoInterface.addCourse(courseName,instructorID,semester);
    }

    /**
     * Method to delete courses in the catalog
     *
     * @param courseID
     */
    @Override
    public void deleteCourse(int courseID) {
        AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
        try{
            adminDaoInterface.deleteCourse(courseID);
        }catch (CourseNotFoundException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);
        }

    }

    /**
     * Method to approve student's selected course
     *
     * @param studentId
     * @param courseId
     */
    @Override
    public void approveCourse(int studentId, int courseId) {
        AdminDaoInterface adminDaoInterface = new AdminDaoOperation();
        adminDaoInterface.approveCourse(studentId,courseId);
    }
}