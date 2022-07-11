package com.example.course_project_5;

import java.sql.*;

public class Connecting {

    public static Connection getDb_connect() {//throws ClassNotFoundException, SQLException
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection(
                            "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1992_quotes_of_teachers",
                            "std_1992_quotes_of_teachers",
                            Secrets.dbPassword
                    );
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка. Библиотека баз данных не найдена");
            e.printStackTrace();
        } catch (SQLException e) {

            System.out.println("Ошибка. Ошибка SQL. Возможно отсуствует подключение к серверу");
            e.printStackTrace();
        }
        return null;
    }


    public Integer regist_save_data(String create_username_text, String create_password_text) throws ClassNotFoundException {
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
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = getDb_connect().prepareStatement(insert_data, Statement.RETURN_GENERATED_KEYS);
            prSt.setNull(1, Types.INTEGER);
            prSt.setString(2, create_username_text);
            prSt.setString(3, create_password_text);
            prSt.setString(4, "Basic");
            prSt.setInt(5, 211362);
            //TODO добавить параметр роли
            prSt.execute();
            ResultSet resultSet = prSt.getGeneratedKeys();
            Integer generatedKey = -1;
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }
            return generatedKey;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}