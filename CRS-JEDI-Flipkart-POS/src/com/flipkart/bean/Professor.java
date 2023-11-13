package com.flipkart.bean;



/**
 *
 * @author JEDI-07
 * Professor Class
 *
 */
public class Professor extends User{

    private String designation;
    private String department;
    private int profId;

    public Professor(int userId, String name, String gender, String password, String address, String designation, String department, int profId, String role) {
        super(userId, name, gender, password, address,role);
        this.designation = designation;
        this.department = department;
        this.profId = profId;
    }

    /**
     * Method to get Professor Designation
     * @return designation of professor
     */
    public String getDesignation() {
        return designation;
    }


    /**
     * Method to get Professor Designation
     * @return department of professor
     */
    public String getDepartment() {
        return department;
    }


    /**
     * Method to set Professor Designation
     * @param designation of professor
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }



    /**
     * Method to set Professor Department
     * @param department of professor
     */
    public void setDepartment(String department) {
        this.department = department;
    }


}
