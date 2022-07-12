package com.example.course_project_5.models;

import com.example.course_project_5.Connecting;
import com.example.course_project_5.helpers.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quotes extends ArrayList<Quote> {

    public Quotes() {
        loadQuotes();

    }
    public ArrayList<Quote> getQuotes() {
        return this;
    }

    public void loadQuotes() {
        this.clear();
        String query = "SELECT * FROM " + Constants.QUOTE_TABLE.table_name;
        try {
            Connection connection = Connecting.getDb_connect();
            PreparedStatement prSt = connection.prepareStatement(query);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                this.add(
                        new Quote(
                                resultSet.getInt(Constants.QUOTE_TABLE.id_column),
                                resultSet.getString(2),
                                resultSet.getDate(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getInt(6),
                                resultSet.getInt(7)
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
