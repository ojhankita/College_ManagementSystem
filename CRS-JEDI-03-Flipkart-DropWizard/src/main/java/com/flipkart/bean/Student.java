package com.flipkart.bean;

/**
 * @author Pulkit
 */

public class Student extends User{

    private int studentId;
    private String branch;
    private int semester;
    private boolean isApproved = false;

    /**+
     * Parameterized Constructor to create object of Student Class
     * @param userId
     * @param name
     * @param gender
     * @param password
     * @param branch
     * @param studentId
     * @param semester
     * @param address
     * @param role
     */
    public Student(int userId, String name, String gender, String password, String branch, int studentId, int semester, String address, String role) {
        super(userId, name, gender, password,address,role);
        this.studentId = studentId;
        this.branch = branch;
        this.semester = semester;
    }

    public Student() {

    }
    /**+
     * Method to get studentID of Student
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**+
     * Method to set studentId of Student
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**+
     * Method to get Branch of Student
     * @return branch
     */
    public String getBranch() {
        return branch;
    }

    /**+
     * Method to set Branch of Student
     * @param branch
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**+
     * Method to get Semester of Student
     * @return semester
     */
    public int getSemester() {
        return semester;
    }

    /**+
     * Method to set Semester of Student
     * @param semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**+
     * Method to get Approval status of Student
     * @return Approval Status
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**+
     * Method to set approval status of Student
     * @param approved
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

}
