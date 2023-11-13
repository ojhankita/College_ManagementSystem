package com.flipkart.restController;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.utils.UserAuth;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @author Sarthak
 */
@Path("/professor")
public class ProfessorRestAPI {

    ProfessorInterface professorInterface = new ProfessorOperation();
    private static Logger logger = Logger.getLogger(AdminRestAPI.class);

    /**
     * Method to Add grade for a given student and course
     * @param
     */

    @POST
    @Path("/addGrade")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addGrade(Map<String,String> params, @HeaderParam("authKey") String authKey) {
        logger.debug("Inside addGrade");
        logger.debug("authKey is "+authKey);
        for (Map.Entry<String,String> entry : params.entrySet())
        {
            logger.info(entry.getKey()+" "+entry.getValue());
        }
        if(UserAuth.isProfessorLogin(authKey) ==null){
            logger.info("Invalid AuthKey");
            return "Access Denied!";
        }
        professorInterface.addGrade(Integer.parseInt(params.get("studentId")),Integer.parseInt(params.get("courseId")),Double.parseDouble(params.get("grade")));
        logger.info("Grade added for student");
        return "Grade added successfully!";
    }

    /**
     * Method to View enrolled students for a specific course
     * @param
     */

    @POST
    @Path("/viewEnrolledStudents")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object viewEnrolledStudents(Map<String,String> params, @HeaderParam("authKey") String authKey) {
        logger.debug("Inside viewEnrolledStudents");
        logger.debug("authKey is "+authKey);

        if(UserAuth.isProfessorLogin(authKey) ==null){
            //TODO CHECK IF IT WORKS!
            logger.info("Invalid AuthKey");
            return "Access Denied!";
        }
        logger.info("Successfully retrieved the list of students");
        return professorInterface.viewEnrolledStudents(Integer.parseInt(params.get("courseId")));
    }

    /**
     * Method to Get course list that professor teaches
     * @param
     */
    @GET
    @Path("/getCourses")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object getCourses(@HeaderParam("authKey") String authKey) {
        logger.info("Grade added for student");
        if(UserAuth.isProfessorLogin(authKey) ==null){
            logger.info("Invalid AuthKey");
            //TODO CHECK IF IT WORKS!
            return "Access Denied!";
        }
        logger.info("Successfully retrieved the list of courses");
        return professorInterface.getCourses(UserAuth.isProfessorLogin(authKey));
    }

}
