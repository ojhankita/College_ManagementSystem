package com.flipkart.restController;

import com.flipkart.bean.Student;
import com.flipkart.business.*;

import com.flipkart.constant.RoleConstants;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.utils.UserAuth;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * @author Abhinav
 */
@Path("/user")
public class UserRestAPI {

    private static Logger logger = Logger.getLogger(AdminRestAPI.class);

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Method for Student registration into the system
     */
    public String registerStudent(Map<String,String> m)
    {

        logger.debug("Inside registerStudent");
        for (Map.Entry<String,String> entry : m.entrySet())
        {
            logger.info(entry.getKey()+" "+entry.getValue());
        }
        StudentInterface studentInterface = new StudentOperation();

        Pair<Integer,Integer> p;
        try {
            p = studentInterface.register(m.get("name"), m.get("password"), m.get("gender"), m.get("branch"), Integer.parseInt(m.get("semester")), m.get("address"));
        }
        catch (NullPointerException ex) {
            return "Invalid Input";
        }

        logger.info("Registered the student inside the database");
        return "Admin approval pending. UserId: "+p.getValue()+" StudentId: "+p.getKey();
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Method to verify credentials
     */
    public String verifyCredentials(Map<String,String> map){
        logger.debug("Inside verifyCredentials");
        for (Map.Entry<String,String> entry : map.entrySet())
        {
            logger.info(entry.getKey()+" "+entry.getValue());
        }

        int userId;
        String password;
        try {
            password = map.get("password");
            userId = Integer.parseInt(map.get("userId"));
        }
        catch (NullPointerException ex) {
            return "Invalid Input";
        }

        UserInterface userInterface = new UserOperation();
        Boolean loggedIn = userInterface.verifyCredentials(userId, password);
        if(loggedIn)
        {
            String userType=userInterface.userType(userId);
            switch (userType) {
                case RoleConstants.ADMIN:
                    return UserAuth.loginAdmin(userId);

                case RoleConstants.PROF:

                    ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();
                    int profId = professorDaoInterface.getProfId(userId);
                    return UserAuth.loginProfessor(profId);
                case RoleConstants.STUDENT:
                    StudentInterface studentInterface = new StudentOperation();
                    StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
                    int studentId = studentDaoOperation.getStudentId(userId);
                    boolean isApproved = studentInterface.isApproved(studentId);
                    if (isApproved) {
                        logger.debug("The user has logged in");
                        return UserAuth.loginStudent(studentId);
                    } else {
                        logger.debug("Approval pending for the current user");
                        return "Approval Pending";
                    }
            }
        }
        else
        {
            logger.info("Invalid Credentials");
            return "Invalid Credentials";
        }
        return "PENDING";
    }

    /**
     * Method to Update Password of User
     */

    @POST
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePassword(Map<String,String> params,@HeaderParam("authKey") String authKey)
    {
        logger.debug("Inside updatePassword");
        logger.debug("authKey is "+authKey);
        for (Map.Entry<String,String> entry : params.entrySet())
        {
            logger.info(entry.getKey()+" "+entry.getValue());
        }
        if(!UserAuth.isUserLogin(authKey)){
            logger.info("Invalid Credentials");
            return "Access Denied";
        }
        UserInterface userInterface = new UserOperation();

        try {
            userInterface.updatePassword(Integer.parseInt(params.get("userId")), params.get("newPassword"));
        }
        catch (NullPointerException ex) {
            return "Invalid Input";
        }

        logger.info("Password updated successfully");
        return "Password updated successfully.";

    }
}
