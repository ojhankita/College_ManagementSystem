package com.flipkart.business;

import com.flipkart.bean.Grade;
import org.apache.log4j.Logger;

import java.util.HashMap;

public class GradecardOperation implements GradecardInterface{
    private static Logger logger = Logger.getLogger(AdminOperation.class);

    /**
     * Method to calculate CGPA
     * @param grade
     * @return
     */
    @Override
    public float calculateCGPA(Grade grade) {

        HashMap<String,Double> grades = grade.getGrades();

        double cgpa = grades.values().stream().reduce(0.0,Double::sum);
        try {
            cgpa = cgpa / grades.size();
        }
        catch(ArithmeticException ex) {
            logger.error("Student has no courses");
            cgpa = 0;
        }
        grade.setCGPA((float)cgpa);
        return (float)cgpa;
    }
}
