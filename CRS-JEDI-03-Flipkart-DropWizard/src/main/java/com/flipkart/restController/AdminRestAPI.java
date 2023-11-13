package com.flipkart.restController;

import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.utils.UserAuth;
import javafx.util.Pair;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author Sarthak
 */

@Path("/admin")
public class AdminRestAPI {

    private static Logger logger = Logger.getLogger(AdminRestAPI.class);

    AdminInterface adminOperation = new AdminOperation();

    /**
     * Method to approve student's course
     */

    @POST
    @Path("/approveCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String approveCourse(Map<String, String> params, @HeaderParam("authKey") String authKey) {
        logger.debug("Inside approveCourse");
        logger.debug("authKey is " + authKey);
        try{
            for (Map.Entry<String, String> entry : params.entrySet()) {
                logger.info(entry.getKey() + " " + entry.getValue());
            }
            if (UserAuth.isAdminLogin(authKey) == null) {
                logger.info("Invalid Auth Key");
                return "Access Denied";
            }
            adminOperation.approveCourse(Integer.parseInt(params.get("studentId")), Integer.parseInt(params.get("courseId")));
            logger.info("Course Approved");
            return "Course Approved Successfully!";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Method to Add a professor into the system
     */

    @POST
    @Path("/addProfessor")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addProfessor(Map<String, String> params, @HeaderParam("authKey") String authKey) {
        logger.debug("Inside addProfessor");
        logger.debug("authKey is " + authKey);
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                logger.info(entry.getKey() + " " + entry.getValue());
            }
            if (UserAuth.isAdminLogin(authKey) == null) {
                logger.info("Invalid Auth Key");
                return "Access Denied";
            }
            Pair<Integer, Integer> p = adminOperation.addProfessor(params.get("name"), params.get("gender"), params.get("password"),
                    params.get("address"), params.get("designation"), params.get("department"));

            logger.info("Successfully added the new professor");
            return "Professor " + params.get("name") + " Added! UserID: " + p.getValue() + " ProfId: " + p.getKey();
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Method to Approve Student Registration
     */
    @POST
    @Path("/approveStudent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String approveStudent(Map<String, String> params, @HeaderParam("authKey") String authKey) {

        logger.debug("Inside approveStudent");
        logger.debug("authKey is " + authKey);
        try{
            for (Map.Entry<String, String> entry : params.entrySet()) {
                logger.info(entry.getKey() + " " + entry.getValue());
            }
            if (UserAuth.isAdminLogin(authKey) == null) {
                logger.info("Invalid Auth Key");
                return "Access Denied";
            }
            logger.info("Successfully approved the new student");
            adminOperation.approveStudent(Integer.parseInt(params.get("studentId")));
            return "Student " + params.get("studentId") + " Approved!";
        }catch (Exception e){
            return e.getMessage();
        }


    }

    /**
     * Method to Delete course from catalogue
     */

    @DELETE
    @Path("/deleteCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteCourse(Map<String, String> params, @HeaderParam("authKey") String authKey) {
        try {
            if (UserAuth.isAdminLogin(authKey) == null) {
                return "Access Denied";
            }
            adminOperation.deleteCourse(Integer.parseInt(params.get("courseId")));
            return "Course " + params.get("courseId") + " deleted!";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Method to Add course to catalogue
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCourse(Map<String, String> params, @HeaderParam("authKey") String authKey) {
        try {
            if (UserAuth.isAdminLogin(authKey) == null) {
                return "Access Denied";
            }
            int cid = adminOperation.addCourse(params.get("courseName"), Integer.parseInt(params.get("instructorId")), Integer.parseInt(params.get("semester")));
            return "Course " + params.get("courseName") + " Added! CourseId: " + cid;
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
