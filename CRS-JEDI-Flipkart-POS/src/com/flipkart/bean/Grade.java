package com.flipkart.bean;

import java.util.HashMap;

public class Grade {
    int studentID;
    float CGPA;
    HashMap<String, Double> grades;

    public Grade(int studentId, HashMap<String, Double> grades) {
        this.studentID = studentId;
        this.grades = grades;
    }
    /*courseCode to gradePoint*/


    /**
     * Method to get studentId
     *
     * @return student Id
     */

    public int getStudentID() {
        return studentID;
    }

    /**
     * Method to set Courses name
     *
     * @param studentID
     */

    public void setStudentId(int studentID) {
        this.studentID = studentID;
    }


    /**
     * Method to get CGPA
     *
     * @return CGPA
     */

    public float getCGPA() {
        return CGPA;
    }

    /**
     * Method to set CGPA
     *
     * @param CGPA
     */
    public void setCGPA(float CGPA) {
        this.CGPA = CGPA;
    }

    /**
     * Method to get Grades
     *
     * @return Hashmap with CourseID as key and Grade point as value
     */

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    /**
     * Method to set Grades
     *
     * @param grades : Hashmap with CourseID as key and Grade point as value
     */

    public void setGrades(HashMap<String, Double> grades) {
        this.grades = grades;
    }
}
