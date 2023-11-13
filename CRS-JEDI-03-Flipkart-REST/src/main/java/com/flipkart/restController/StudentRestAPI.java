package com.flipkart.restController;


import com.flipkart.bean.Grade;

import com.flipkart.business.GradecardInterface;
import com.flipkart.business.GradecardOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;

import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.utils.UserAuth;
import org.apache.log4j.Logger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author Sarthak
 */
@Path("/student")
public class StudentRestAPI {
    private static Logger logger = Logger.getLogger(AdminRestAPI.class);

    StudentInterface studentInterface = new StudentOperation();
    /**
     * Method to Make payment for the semester
     * @param
     */
    @POST
    @Path("/makePayment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String makePayment(Map<String,String> params,@HeaderParam("authKey") String authKey) {
        logger.debug("Inside makePayment");
        logger.debug("authKey is "+authKey);
        for (Map.Entry<String,String> entry : params.entrySet())
        {
            logger.info(entry.getKey()+" "+entry.getValue());
        }
        if(UserAuth.isStudentLogin(authKey) == null){
            logger.info("Invalid AuthKey");
            return "Access Denied";
        }
        studentInterface.makePayment(UserAuth.isStudentLogin(authKey),Integer.parseInt(params.get("semester")));
        logger.info("Payment Successful");
        return  "Payment Successful";

    }

    /**
     * Method to View Grade card for the semester
     * @param
     */
    @POST
    @Path("/viewGradeCard")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object  viewGradeCard(Map<String,String> params,@HeaderParam("authKey") String authKey) {
        logger.debug("Inside viewGradeCard");
        logger.debug("authKey is "+authKey);
        try {
            for (Map.Entry<String,String> entry : params.entrySet())
            {
                logger.info(entry.getKey()+" "+entry.getValue());
            }
            if(UserAuth.isStudentLogin(authKey) == null){
                logger.info("Invalid AuthKey");
                return "Access Denied";
            }
            Grade gradeCard = studentInterface.viewGradeCard(UserAuth.isStudentLogin(authKey),Integer.parseInt(params.get("semester")));
            GradecardInterface gradecardInterface = new GradecardOperation();
            logger.info("Successfully retrieved graceCard");
            gradecardInterface.calculateCGPA(gradeCard);
            HashMap <String, Double> grades = gradeCard.getGrades();
            if(grades.size()>=6) {
                return gradeCard;
            } else {
                return "Semester not yet completed";
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Method to View enrolled courses
     * @param
     */

    @GET
    @Path("/viewRegisteredCourses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object viewRegisteredCourse(@HeaderParam("authKey") String authKey) {

        try {
            if(UserAuth.isStudentLogin(authKey) == null){
                return "Access Denied";
            }
            return studentInterface.getRegisteredCourses(UserAuth.isStudentLogin(authKey));
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Method to View available courses
     * @param
     */

    @POST
    @Path("/viewCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object viewCourse(Map<String,String> params,@HeaderParam("authKey") String authKey) {
        try {
            if(UserAuth.isStudentLogin(authKey) == null){
                return "Access Denied";
            }
            return studentInterface.getCourses(Integer.parseInt(params.get("semester")));
        }catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Method to Add a course
     * @param
     */

    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addCourse(Map<String,String> params,@HeaderParam("authKey") String authKey) {
        try {
            if(UserAuth.isStudentLogin(authKey) == null){
                return "Access Denied";
            }
            try{
                studentInterface.addCourse(Integer.parseInt(params.get("courseId")), UserAuth.isStudentLogin(authKey));
                return "Course Added";
            }catch (CourseLimitExceedException ex){
                return ex.getMessage();
            }
        }catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Method to Drop a course
     * @param
     */

    @POST
    @Path("/dropCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String dropCourse(Map<String,String> params,@HeaderParam("authKey") String authKey) {
       try {
           if(UserAuth.isStudentLogin(authKey) == null){
               return "Access Denied";
           }
           studentInterface.dropCourse(Integer.parseInt(params.get("courseId")), UserAuth.isStudentLogin(authKey));
           return "Course Deleted!";

       }catch ( Exception e){
           return e.getMessage();
       }

    }

    /**
     * Method to Register for selected courses
     * @param
     */
    @POST
    @Path("/dropCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registerCourse(@HeaderParam("authKey") String authKey) {
        try {
            if(UserAuth.isStudentLogin(authKey) == null){
                return "Access Denied";
            }
            studentInterface.registerForCourses(UserAuth.isStudentLogin(authKey));
            return "Semester Registered!";

        }catch (Exception e){
            return e.getMessage();
        }

    }
}
