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

}