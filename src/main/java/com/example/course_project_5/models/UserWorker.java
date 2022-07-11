package com.example.course_project_5.models;

import com.example.course_project_5.Connecting;
import com.example.course_project_5.helpers.Constants;

import java.sql.*;

public class UserWorker {
    public static User createUser(String login, String password) {
        String insert_data =
                "INSERT INTO " +
                        Constants.USER_TABLE.table_name + " ( "
                        + Constants.USER_TABLE.id_column + ", "
                        + Constants.USER_TABLE.login_columm + ", "
                        + Constants.USER_TABLE.password_columm + ", "
                        + Constants.USER_TABLE.status_columm + ", "
                        + Constants.USER_TABLE.level_columm + ") "
                        + "VALUES ( ?, ?, ?, ?, ?)";
        System.out.println(insert_data);

        String passwordHash = password; //TODO add encryption
        Integer level = 211361; //TODO добавить параметр уровня
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data, Statement.RETURN_GENERATED_KEYS);
            prSt.setNull(1, Types.INTEGER);
            prSt.setString(2, login);
            prSt.setString(3, passwordHash);
            prSt.setString(4, "Basic");
            prSt.setInt(5, level);
            prSt.execute();
            ResultSet resultSet = prSt.getGeneratedKeys();
            Integer generatedKey = -1;
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }
            connect.close();
            return new User(
                    generatedKey,
                    login,
                    passwordHash,
                    User.UserStatus.Basic,
                    level
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User updateLogin(User oldUser, String login) {
        String insert_data =
                "UPDATE " +
                        Constants.USER_TABLE.table_name + " SET "
                        + Constants.USER_TABLE.login_columm + " = ? " +
                        " WHERE id = ?";
        System.out.println(insert_data);
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data);
            prSt.setString(1, login);
            prSt.setInt(2, oldUser.getId());
            prSt.execute();
            connect.close();
            return new User(oldUser, login);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User updatePassword(User oldUser, String password) {
        String insert_data =
                "UPDATE " +
                        Constants.USER_TABLE.table_name + " SET "
                        + Constants.USER_TABLE.password_columm + " = ? " +
                        " WHERE id = ?";

        String passwordHash = password; //TODO add encryption
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data);
            prSt.setString(1, passwordHash);
            prSt.setInt(2, oldUser.getId());
            prSt.execute();
            connect.close();
            return new User(
                    oldUser,
                    passwordHash,
                    true
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
