package com.example.course_project_5;
import java.sql.*;

/*
public class DB_handler extends Configs {
    Connection db_connect;
    public Connection getDb_connect() throws ClassNotFoundException, SQLException {
        String connect_string = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.Driver");
        db_connect = DriverManager.getConnection(connect_string, dbUser, dbPassword);
        return db_connect;
    }

    public void regist_save_data(String create_username_text, String create_password_text) throws ClassNotFoundException {
        String insert_data = "INSERT INTO" + Constants.USER_TABLE + "(" + Constants.USER_USERNAME + "," + Constants.USER_HASH + ")" +
                "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDb_connect().prepareStatement(insert_data);
            prSt.setString(1, create_username_text);
            prSt.setString(2, create_password_text);
            prSt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }*/

//}
