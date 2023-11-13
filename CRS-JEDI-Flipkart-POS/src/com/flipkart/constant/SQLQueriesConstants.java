package com.flipkart.constant;
/**
 *
 * @author JEDI-07
 *
 */

public class SQLQueriesConstants {

    // UserDao Queries
    public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";

    //AdminDao Queries
    public static final String DELETE_COURSE = "delete from course where courseId = ?";
    public static final String ADD_COURSE = "insert into course( courseName,profId,semester) values (?, ?, ?)";
    public static final String APPROVE_STUDENT = "update student set isApproved = 1 where studentId = ?";
    public static final String ADD_USER_QUERY = "insert into user(password,role,name,address,gender) values (?, ?, ?, ?,?)";
    public static final String ADD_PROFESSOR = "insert into professor(userId,department, designation) values (?, ?, ?)";
    public static final String APPROVE_COURSE = "update registration set isApproved = 1 where studentId = ? and courseId = ?";
    public static final String ADD_STUDENT="insert into student (branch,semester,userId) values (?,?,?)";

    // Student Queries
    public static final String VIEW_REGISTERED_COURSES=" select * from registration  inner join course on course.courseId = registration.courseId where registration.studentId = ? AND registration.isApproved = 1";
    public static final String VIEW_AVAILABLE_COURSES=" select * from course where semester = ?";
    public static final String GET_STUDID="select studentId from student where userId = ?";
    public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
    public static final String VIEW_SELECTED_COURSES="select * from registration  inner join course on course.courseId = registration.courseId where registration.studentId = ?";
    public static final String STUDENT_ADD_COURSE="insert into registration (studentId,courseId) values ( ? , ?)";
    public static final String STUDENT_DROP_COURSE = "delete from registration where courseId = ? AND studentId = ?;";
    public static final String VIEW_GRADE = "select * from registration inner join course on course.courseId = registration.courseId where studentId = ? AND isApproved = 1 AND semester = ?;";
    public static final String MAKE_PAYMENT = "update student set feesPaid=1 where studentId=?";
    public static final String CHECK_PAYMENT = "select feesPaid from student where studentId=?";

    // Professor Queries
    public static final String ADD_GRADE="update registration set grade=? where courseId=? and studentId=?";
    public static final String GET_COURSES="select * from course where profId=?";
    public static final String GET_PROFID="select profId from professor where userId = ?";
    public  static  final  String GET_ENROLLED_STUDENTS = "select registration.studentId,student.userId,user.name from registration join student on student.studentId = registration.studentId join user on user.userId = student.userId where courseId = ?";

    // Notification Query
    public static final String SAVE_NOTIFICATION="insert into notification (paymentId,notification,timestamp,studentId) values (?,?,?,?)";
}
