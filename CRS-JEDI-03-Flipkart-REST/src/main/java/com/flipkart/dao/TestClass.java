package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestClass {
    public static void main(String[] args) {
////        (password,role,name,address,gender)
//        Connection connection  = DBUtil.getConnection();
//        try {
//            String generatedColumns[] = { "userId" };
//
//            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY, generatedColumns);
//            statement.setString(1,"efgh");
//            statement.setString(2, "S");
//            statement.setString(3, "Sadh");
//            statement.setString(4, "UP");
//            statement.setString(5, "Male");
//            statement.executeUpdate();
//            ResultSet rs = statement.getGeneratedKeys();
//            if(rs.next()){
//                System.out.println(rs.getInt(1));
//            }
////            System.out.println(rs.getInt(1));
//
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }
//        UserDaoInterface userDaoInterface = new UserDaoOperation();
//        System.out.println(userDaoInterface.verifyCredentials(1,"efgh"));
    }
}
