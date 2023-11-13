package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

public interface UserDaoInterface {

    /**
     * Method to update password
     * @param userId
     * @param password
     */
    public void updatePassword(int userId, String password);


    /**
     * Method to verify Credentials
     * @param userId
     * @param password
     * @return
     */
    public boolean verifyCredentials(int userId, String password) throws UserNotFoundException;

    /**
     * Method to return User Role
     * @param userId
     * @return
     */
    String userType(int userId);
}
