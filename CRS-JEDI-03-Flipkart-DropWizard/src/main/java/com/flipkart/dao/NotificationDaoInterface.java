package com.flipkart.dao;

import com.flipkart.bean.Notification;

public interface NotificationDaoInterface {

    /**+
     * Method to save notifications
     * @param notification
     */
    public void saveNotification(Notification notification);
}
