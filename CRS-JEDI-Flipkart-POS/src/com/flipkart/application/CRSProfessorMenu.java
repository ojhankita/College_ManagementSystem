package com.flipkart.application;

import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.constant.ConsoleColors;
import javafx.util.Pair;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Abhinav
 */
public class CRSProfessorMenu {

    ProfessorInterface professorInterface = new ProfessorOperation();
    Scanner sc = new Scanner(System.in);

    /**
     * Method to Show the professor Menu
     * @param profId
     */
    public void showMenu(int profId) {
        System.out.println(ConsoleColors.GREEN+"-----Welcome Professor-----"+ConsoleColors.RESET);
        System.out.println();
        String input="0";
        while(CRSApplicationClient.loggedIn)
        {
            System.out.println("-----Professor Options-----");
            System.out.println("1. View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Logout");
            System.out.println();
            System.out.println("Enter choice:-");

            input = sc.nextLine();

            switch (input) {
                case "1":
                    getCourses(profId);
                    break;
                case "2":
                    viewEnrolledStudents(profId);
                    break;
                case "3":
                    addGrade(profId);
                    break;
                case "4":
                    CRSApplicationClient.loggedIn = false;
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Method to Add grade for a given student and course
     * @param profId
     */
    private void addGrade(int profId) {
        double grade;
        int studentId, courseId;
        try {
            System.out.println(ConsoleColors.GREEN+"-----Add Grade-----"+ConsoleColors.RESET);
            System.out.println("StudentID:");
            studentId = sc.nextInt();
            sc.nextLine();
            System.out.println("Course Code:");
            courseId = sc.nextInt();
            sc.nextLine();
            System.out.println("Grade:");
            grade = sc.nextDouble();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Please enter only digits");
            System.out.println();
            return;
        }
        professorInterface.addGrade(studentId,courseId,grade);
        System.out.println("Grade added successfully!");
        System.out.println();
    }

    /**
     * Method to View enrolled students for a specific course
     * @param profId
     */
    private void viewEnrolledStudents(int profId) {
        int courseId;
        try {
            System.out.println("Course Code:");
            courseId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Course Code must contain only digits");
            System.out.println();
            return;
        }

        System.out.println(ConsoleColors.GREEN+"-------------------------------------------------------");
        System.out.println("The following students have registered for this course:");
        System.out.println("-------------------------------------------------------"+ConsoleColors.RESET);
        System.out.format("%-6s%20s",ConsoleColors.BLUE+"STUDENT ID","STUDENT NAME"+ConsoleColors.RESET);
        System.out.println();
        List<String> studentList =  professorInterface.viewEnrolledStudents(courseId);
        for(String student: studentList) {
            String[] arrOfStr = student.split(":", 2);
            System.out.format("%-6s%15s",arrOfStr[0],arrOfStr[1]);
            System.out.println();
        }

        System.out.println();
    }

    /**
     * Method to Get course list that professor teaches
     * @param profId
     */
    private void getCourses(int profId) {
        System.out.println(ConsoleColors.GREEN+"---------------------------------------------------------------");
        System.out.println("These are the following available courses for prof with id "+profId+" : ");
        System.out.println("---------------------------------------------------------------"+ConsoleColors.RESET);
        System.out.printf("%-6s%20s",ConsoleColors.BLUE+"COURSE ID","COURSE NAME"+ConsoleColors.RESET);
        System.out.println();
        List<Pair<Integer,String>> courseList = professorInterface.getCourses(profId);
        for(Pair<Integer,String>course: courseList) {
            System.out.format("%-6d%15s",course.getKey(),course.getValue());
            System.out.println();
        }
        System.out.println();
    }

}
