package com.flipkart.application;

import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.constant.ConsoleColors;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import javafx.util.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Abhinav
 */
public class CRSAdminMenu {

    AdminInterface adminOperation = new AdminOperation();
    Scanner scanner = new Scanner(System.in);

    /**
     * Method to Show the Admin Menu
     */
    public void showMenu() {
        System.out.println(ConsoleColors.GREEN+"----------Welcome Admin----------"+ConsoleColors.RESET);
        while(CRSApplicationClient.loggedIn) {
            System.out.println(ConsoleColors.GREEN+"----------Admin Choices----------"+ConsoleColors.RESET);
            System.out.println("1. Add course in catalog");
            System.out.println("2. Delete Course from catalog");
            System.out.println("3. Approve Student Registration");
            System.out.println("4. Add Professor");
            System.out.println("5. Approve Student Courses");
            System.out.println("6. Logout");
            System.out.println();
            System.out.println("Enter choice:-");

            String choice = "0";
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addCourse();
                    break;

                case "2":
                    deleteCourse();
                    break;

                case "3":
                    approveStudent();
                    break;

                case "4":
                    addProfessor();
                    break;

                case "5":
                    approveCourse();
                    break;

                case "6":
                    CRSApplicationClient.loggedIn = false;
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    /**
     * Method to approve student's course
     */
    private void approveCourse() {
        int studentId, courseId;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(ConsoleColors.GREEN+"-----Course Approval-----"+ConsoleColors.RESET);
            System.out.println("StudentID:");
            studentId = sc.nextInt();
            sc.nextLine();
            System.out.println("CourseID:");
            courseId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Enter only digits in each field");
            System.out.println();
            return;
        }
        adminOperation.approveCourse(studentId,courseId);
    }

    /**
     * Method to Add a professor into the system
     */
    private void addProfessor() {
        String name;
        String password;
        String gender;
        String department;
        String designation;
        String address;
        Scanner sc=new Scanner(System.in);

        try {
            System.out.println(ConsoleColors.GREEN+"-----Professor Registration-----"+ConsoleColors.RESET);
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();
            System.out.println("Gender:");
            gender = sc.nextLine();
            System.out.println("Department:");
            department = sc.nextLine();
            System.out.println("Designation:");
            designation = sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
        } catch(InputMismatchException ex) {
            ex.printStackTrace();
            return;
        }
        Pair<Integer, Integer> pair = adminOperation.addProfessor(name,gender,password,address,designation,department);
        if(pair.getKey()!=0 && pair.getValue()!=0){
            System.out.println("Professor Added! User Id: "+pair.getValue()+" Prof Id: "+pair.getKey());
        }
    }

    /**
     * Method to Approve Student Registration
     */
    private void approveStudent() {
        Scanner sc = new Scanner(System.in);
        int studentId;
        try {
            System.out.println("StudentID:");
            studentId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("StudentID must contain only digits");
            System.out.println();
            return;
        }
        adminOperation.approveStudent(studentId);


    }

    /**
     * Method to Delete course from catalogue
     */
    private void deleteCourse() {
        Scanner sc = new Scanner(System.in);
        int courseId;
        try {
            System.out.println("CourseID:");
            courseId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("CourseID must contain only digits");
            return;
        }
        adminOperation.deleteCourse(courseId);
    }

    /**
     * Method to Add course to catalogue
     */
    private void addCourse() {
        String courseName;
        int instructorId;
        int semester;
        Scanner sc=new Scanner(System.in);

        try {
            System.out.println(ConsoleColors.GREEN+"-----Add Course-----"+ConsoleColors.RESET);
            System.out.println("Course Name:");
            courseName = sc.nextLine();
            System.out.println("ProfessorId:");
            instructorId = sc.nextInt();
            sc.nextLine();
            System.out.println("Semester:");
            semester = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("ProfessorID and Semester must contain only digits");
            return;
        }
        int courseId = adminOperation.addCourse(courseName, instructorId, semester);
        if(courseId!=0){
            System.out.println("Course Added! Course Id: "+courseId);
        }
    }
}