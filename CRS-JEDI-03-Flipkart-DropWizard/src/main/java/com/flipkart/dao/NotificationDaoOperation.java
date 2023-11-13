package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NotificationDaoOperation implements NotificationDaoInterface{

    /**+
     * Method to save notifications
     * @param notification
     */
    @Override
    public void saveNotification(Notification notification) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.SAVE_NOTIFICATION);

            statement.setLong(1, notification.getPaymentId());
            statement.setString(2, notification.getNotificationMessage());
            statement.setTimestamp(3, Timestamp.valueOf(notification.getTimestamp()));
            statement.setInt(4, notification.getStudentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
