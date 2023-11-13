package com.flipkart.application;

import com.flipkart.business.*;
import com.flipkart.constant.RoleConstants;
import com.flipkart.dao.*;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.flipkart.constant.ConsoleColors;
import javafx.util.Pair;

/**
 * @author Abhinav
 */
public class CRSApplicationClient {

    static boolean loggedIn = false;
    StudentInterface studentInterface= new StudentOperation();
    UserInterface userInterface = new UserOperation();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CRSApplicationClient CRSApplicationClient=new CRSApplicationClient();

        System.out.printf("%105s\n",ConsoleColors.BLUE+"------------------------WELCOME-----------------------"+ ConsoleColors.RESET);
        System.out.printf("%105s\n",ConsoleColors.BLUE+"---------------Course Management System---------------"+ ConsoleColors.RESET);
        System.out.println();


        showMainMenu();

        String userInput="0";
        userInput=sc.nextLine();
        while(!userInput.equals("4"))
        {
            switch (userInput) {
                // login
                case "1":
                    CRSApplicationClient.loginUser();
                    break;
                // student registration
                case "2":
                    CRSApplicationClient.registerStudent();
                    break;
                // update Password
                case "3":
                    CRSApplicationClient.updatePassword();
                    break;
                default:
                    System.out.println("Invalid input");
                    System.out.println();
                    break;
            }
            showMainMenu();
            userInput=sc.nextLine();
        }
        sc.close();
        DBUtil.closeConnection();
    }

    /**
     * Method to Show the main CRS Menu
     */
    public static void showMainMenu()
    {
        System.out.println(ConsoleColors.GREEN+"---------Choose an Option---------"+ConsoleColors.RESET);
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Update password");
        System.out.println("4. Exit");
        System.out.println();
        System.out.println(ConsoleColors.GREEN+"Enter choice:-"+ConsoleColors.RESET);
    }


    /**
     * Method to Log-in to the CRS
     */
    public void loginUser()
    {
        Scanner sc=new Scanner(System.in);
        String password;
        int userId;

        try {
            System.out.println("-----Login-----");
            System.out.println("UserID:");
            userId = sc.nextInt();
            sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();
        }
        catch(InputMismatchException ex) {
            System.out.println("UserID must contain only digits");
            System.out.println();
            return;
        }

            loggedIn = userInterface.verifyCredentials(userId, password);
            if(loggedIn)
            {

                String userType=userInterface.userType(userId);
                switch (userType) {
                    case RoleConstants.ADMIN:
                        CRSAdminMenu adminMenu = new CRSAdminMenu();
                        adminMenu.showMenu();
                        break;
                    case RoleConstants.PROF:
                        CRSProfessorMenu professorMenu = new CRSProfessorMenu();
                        ProfessorDaoInterface professorDaoInterface = new ProfessorDaoOperation();
                        int profId = professorDaoInterface.getProfId(userId);
                        professorMenu.showMenu(profId);
                        break;
                    case RoleConstants.STUDENT:
                        StudentDaoOperation studentDaoOperation = new StudentDaoOperation();
                        int studentId = studentDaoOperation.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                            CRSStudentMenu studentMenu = new CRSStudentMenu();
                            studentMenu.showMenu(studentId);

                        } else {
                            System.out.println("Administrator approval pending.");
                            loggedIn = false;
                        }
                        break;
                }
            }
            else
            {
                System.out.println();
                System.out.println("Invalid Credentials");
                System.out.println();
            }


    }

    /**
     * Method for Student registration into the system
     */
    public void registerStudent()
    {
        Scanner sc=new Scanner(System.in);

        String name,password,branchName,gender,address;
        int semester;

        try {
            System.out.println();
            System.out.println("------------------------------");
            System.out.println(ConsoleColors.GREEN+"-----Student Registration-----"+ConsoleColors.RESET);

            System.out.println();
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();
            System.out.println("Gender:");
            gender = sc.nextLine();
            System.out.println("Branch:");
            branchName = sc.nextLine();
            System.out.println("Semester:");
            semester = sc.nextInt();
            sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
            System.out.println();
        }
        catch(InputMismatchException ex) {
            System.out.println("Semester must be a number");
            System.out.println();
            return;
        }
        Pair<Integer, Integer> pair = studentInterface.register(name, password, gender, branchName, semester, address);
        if(pair.getKey()!=0 && pair.getValue()!=0) {
            System.out.println("Student Registered! User Id: " + pair.getValue() + " Student Id: " + pair.getKey());
        }
    }

    /**
     * Method to Update Password of User
     */
    public void updatePassword()
    {
        Scanner sc=new Scanner(System.in);
        String password;
        int userId;

        try {
//            System.out.println("-----Login-----");
            System.out.println(ConsoleColors.GREEN+"------------------------------");
            System.out.println("-------------Login------------"+ ConsoleColors.RESET);
            System.out.println("UserID:");
            userId = sc.nextInt();
            sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();
        }
        catch(InputMismatchException ex) {
            System.out.println("UserID must contain only digits");
            System.out.println();
            return;
        }
            loggedIn = userInterface.verifyCredentials(userId, password);

            if(loggedIn) {
                String newPassword;

                System.out.println();
                System.out.println(ConsoleColors.GREEN+"------------------------------");
                System.out.println("-----Update Password-----"+ConsoleColors.RESET);
                System.out.println();
                System.out.println("New Password:");
                newPassword = sc.nextLine();
                userInterface.updatePassword(userId, newPassword);
                System.out.println("Password updated successfully.");
                System.out.println();
            }
            else {
                System.out.println("Invalid Credentials");
            }


    }
}
