package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.business.GradecardInterface;
import com.flipkart.business.GradecardOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.constant.ConsoleColors;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;

import java.util.*;

/**
 * @author Abhinav
 */
public class CRSStudentMenu {

    StudentInterface studentInterface = new StudentOperation();
    Scanner sc = new Scanner(System.in);

    /**
     * Method to Show the student menu
     * @param studentId
     */
    public void showMenu(int studentId) {
        //System.out.println("--------Welcome Student--------");
        System.out.println(ConsoleColors.GREEN+"--------Welcome Student--------"+ConsoleColors.RESET);
        while (CRSApplicationClient.loggedIn)
        {
//            System.out.println("--------Student Choices--------");
            System.out.println();
            System.out.println(ConsoleColors.GREEN+"--------Student Choices--------"+ConsoleColors.RESET);
            System.out.println("1. Course Registration");
            System.out.println("2. Add Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Available Courses");
            System.out.println("5. View Registered Courses");
            System.out.println("6. View Grade Card");
            System.out.println("7. Make Payment");
            System.out.println("8. Logout");
            System.out.println();
            System.out.println("Enter choice:-");

            String choice="0";
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    registerCourses(studentId);
                    break;
                case "2":
                    addCourse(studentId);
                    break;
                case "3":
                    dropCourse(studentId);
                    break;
                case "4":
                    viewCourse(studentId);
                    break;
                case "5":
                    viewRegisteredCourse(studentId);
                    break;
                case "6":
                    viewGradeCard(studentId);
                    break;
                case "7":
                    makePayment(studentId);
                    break;
                case "8":
                    CRSApplicationClient.loggedIn = false;
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    /**
     * Method to Make payment for the semester
     * @param studentId
     */
    private void makePayment(int studentId) {
        //System.out.println("-----Make Payment-----");
        System.out.println(ConsoleColors.GREEN+"-----Make Payment-----"+ConsoleColors.RESET);
        System.out.println("Semester:");
        int semester;
        try {
            semester = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Semester must be a digit");
            System.out.println();
            return;
        }
        studentInterface.makePayment(studentId,semester);
    }

    /**
     * Method to View Grade card for the semester
     * @param studentId
     */
    private void viewGradeCard(int studentId) {
        //System.out.println("-----View Grade Card-----");
        System.out.println(ConsoleColors.GREEN+"-----View Grade Card-----"+ConsoleColors.RESET);
        System.out.println("Semester:");
        int semester;
        try {
            semester = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Semester must be a digit");
            System.out.println();
            return;
        }

        Grade gradeCard = studentInterface.viewGradeCard(studentId,semester);
        HashMap <String, Double> grades = gradeCard.getGrades();
        if(grades.size()>=6) {
            System.out.printf("%-6s%20s\n", "COURSE NAME", "GRADE");
            if (gradeCard != null) {
                for (Map.Entry mapElement : grades.entrySet()) {
                    String courseName = (String) mapElement.getKey();
                    Double grade = (Double) mapElement.getValue();
                    System.out.printf("%-6s%27f\n", courseName, grade);
                }
                GradecardInterface gradecardInterface = new GradecardOperation();
                float cgpa = gradecardInterface.calculateCGPA(gradeCard);
                System.out.println(ConsoleColors.YELLOW+"CGPA: "+cgpa+ConsoleColors.RESET);
                System.out.println();
            }
        } else {
            System.out.println("Semester not yet completed");
        }
    }

    /**
     * Method to View enrolled courses
     * @param studentId
     */
    private void viewRegisteredCourse(int studentId) {

        List<Course> courseList = studentInterface.getRegisteredCourses(studentId);
        if(courseList.size() == 0){
            System.out.println("No courses found!");
            return;
        }
//
        System.out.println(ConsoleColors.GREEN+"----------------------------------------------");
        System.out.println("You have registered for the following courses:");
        System.out.println("----------------------------------------------"+ConsoleColors.RESET);

        System.out.printf("%-6s%20s%19s%20s\n",ConsoleColors.BLUE+"COURSE ID", "COURSE NAME","SEMESTER","PROFESSOR ID"+ConsoleColors.RESET);
        for(Course course: courseList) {
            //System.out.printf("%-6d%15s\n",course.getCourseID(), course.getCourseName());
            System.out.printf("%-6d%15s%20s%16s\n",course.getCourseID(), course.getCourseName(),course.getSemester(),course.getCourseID());
        }
    }

    /**
     * Method to View available courses
     * @param studentId
     */
    private void viewCourse(int studentId) {
        System.out.println("----------------------");
        System.out.println("Semester:");
        int semester = sc.nextInt();
        sc.nextLine();
        List<Course> courseList = studentInterface.getCourses(semester);
        System.out.println(ConsoleColors.GREEN+"------------------------------------------");
        System.out.println("These are the following available courses:");
        System.out.println("------------------------------------------"+ConsoleColors.RESET);
        //System.out.printf("%-6s%20s\n","COURSE ID", "COURSE NAME");
        System.out.printf("%-6s%20s%19s%20s\n",ConsoleColors.BLUE+"COURSE ID", "COURSE NAME","SEMESTER","PROFESSOR ID"+ConsoleColors.RESET);
        for(Course course: courseList) {
            //System.out.printf("%-6d%15s\n",course.getCourseID(),course.getCourseName());
            System.out.printf("%-6d%15s%20s%16s\n",course.getCourseID(), course.getCourseName(),course.getSemester(),course.getCourseID());

        }
    }

    /**
     * Method to Add a course
     * @param studentId
     */
    private void addCourse(int studentId) {
        int courseId;
        System.out.println("----------------------");
        System.out.println("Enter Semester:");
        int semester = sc.nextInt();
        sc.nextLine();
        viewCourse(semester);
        System.out.println("Enter Course ID:");
        try {
            courseId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Course Code must be a digit");
            System.out.println();
            return;
        }
        try{
            studentInterface.addCourse(courseId, studentId);
        }catch (CourseLimitExceedException ex){
            System.out.println(ConsoleColors.RED+ex.getMessage()+ConsoleColors.RESET);
        }

    }

    /**
     * Method to Drop a course
     * @param studentId
     */
    private void dropCourse(int studentId) {
        int courseId;
        System.out.println("-----------------------");
        viewRegisteredCourse(studentId);
        System.out.println("Enter Course ID:");
        try {
            courseId = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException ex) {
            System.out.println("Course Code must be a digit");
            System.out.println();
            return;
        }
        studentInterface.dropCourse(courseId, studentId);


    }

    /**
     * Method to Register for selected courses
     * @param studentId
     */
    private void registerCourses(int studentId) {
        studentInterface.registerForCourses(studentId);

    }
}
