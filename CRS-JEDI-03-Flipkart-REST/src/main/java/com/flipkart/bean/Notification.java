package com.flipkart.bean;

import java.time.LocalDateTime;

public class Notification {

    long paymentId;
    int studentId;
    LocalDateTime timestamp;
    String notificationMessage;

    public Notification(int studentId, String notificationMessage, long paymentId){
        this(studentId, notificationMessage);
        this.paymentId = paymentId;
    }
    public Notification(int studentId, String notificationMessage){
        this(notificationMessage);
        this.studentId = studentId;
    }
    public Notification(String notificationMessage) {
        this.notificationMessage = notificationMessage;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Method to get payment ID
     *
     * @return payment ID
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Method to set payment ID
     *
     * @param paymentId
     */
    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Method to get student ID
     *
     * @return user ID
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set student ID
     *
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get notification message
     *
     * @return notification message
     */
    public String getNotificationMessage() {
        return notificationMessage;
    }

    /**
     * Method to set notification message
     *
     * @param notificationMessage
     */
    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    /**+
     * Method to get timestamp of the notification
     * @return Timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
