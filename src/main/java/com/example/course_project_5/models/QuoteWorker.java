package com.example.course_project_5.models;

import com.example.course_project_5.Connecting;
import com.example.course_project_5.helpers.Constants;

import java.sql.*;

public class QuoteWorker {
    public static Quote addQuote(Quote quote) {
        String insert_data =
                "INSERT INTO " +
                        Constants.QUOTE_TABLE.table_name + " ( "
                        + Constants.QUOTE_TABLE.id_column + ", "
                        + Constants.QUOTE_TABLE.quote_text_column + ", "
                        + Constants.QUOTE_TABLE.quote_date_column + ", "
                        + Constants.QUOTE_TABLE.subject_column + ", "
                        + Constants.QUOTE_TABLE.teacher_column + ", "
                        + Constants.QUOTE_TABLE.creator_column + ", "
                        + Constants.QUOTE_TABLE.access_level_column + ") "
                        + "VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        System.out.println(insert_data);

        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data, Statement.RETURN_GENERATED_KEYS);
            prSt.setNull(1, Types.INTEGER);
            prSt.setString(2, quote.getText());
            prSt.setDate(3, quote.getCreationDate());
            prSt.setString(4, quote.getSubject());
            prSt.setString(5, quote.getTeacher());
            prSt.setInt(6, quote.getCreatorID());
            prSt.setInt(7, quote.getAccessLevel());
            prSt.execute();
            ResultSet resultSet = prSt.getGeneratedKeys();
            Integer generatedKey = -1;
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }
            connect.close();
            return new Quote(
                    generatedKey,
                    quote
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Quote updateQuote(Quote quote) {
        String insert_data =
                "UPDATE " +
                        Constants.QUOTE_TABLE.table_name + " SET " +
                         Constants.QUOTE_TABLE.quote_text_column + " = ? , " +
                         Constants.QUOTE_TABLE.quote_date_column + " = ? , " +
                         Constants.QUOTE_TABLE.subject_column + " = ? , " +
                         Constants.QUOTE_TABLE.teacher_column + " = ?" +
                        " WHERE id = ?";
        System.out.println(insert_data);
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data);
            prSt.setString(1, quote.getText());
            prSt.setDate(2, quote.getCreationDate());
            prSt.setString(3, quote.getSubject());
            prSt.setString(4, quote.getTeacher());
            prSt.setInt(5, quote.getId());
            prSt.execute();
            connect.close();
            return quote;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean deleteQuote(int quoteID) {
        String insert_data =
                "DELETE FROM " +
                        Constants.QUOTE_TABLE.table_name +
                        " WHERE id = ?";
        System.out.println(insert_data);
        try {
            Connection connect = Connecting.getDb_connect();
            PreparedStatement prSt = connect.prepareStatement(insert_data);
            prSt.setInt(1, quoteID);
            prSt.execute();
            connect.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
