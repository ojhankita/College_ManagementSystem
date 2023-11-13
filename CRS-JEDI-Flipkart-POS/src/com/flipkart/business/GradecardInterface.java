package com.flipkart.business;

import com.flipkart.bean.Grade;

public interface GradecardInterface {
    /**
     * Method to calculate CGPA
     * @param grade
     * @return
     */
    public float calculateCGPA(Grade grade);
}
