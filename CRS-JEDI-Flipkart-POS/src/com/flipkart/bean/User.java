package com.flipkart.bean;

/**
 * @author Sarthak
 */
public class User {
    private int id;
    private String password;
    private String name;
    private String gender;
    private String address;
    private String role;

    /**
     * Constructor to create new user
     * @param id
     * @param name
     * @param gender
     * @param password
     * @param address
     * @param role
     */
    public User(int id, String name, String gender, String password, String address, String role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return address
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
