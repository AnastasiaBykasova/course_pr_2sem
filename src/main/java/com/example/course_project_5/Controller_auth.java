package com.example.course_project_5;

import com.example.course_project_5.helpers.Constants;
import com.example.course_project_5.models.User;
import com.example.course_project_5.models.UserWorker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller_auth {
    @FXML
    private Button confirm_button;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button regist_button;

    @FXML
    private TextField username_field;

    @FXML
    public void initialize() {

        confirm_button.setOnAction(Event -> {
            String enter_username_text = username_field.getText().trim();
            String enter_password_text = password_field.getText().trim();
            
            if ((!enter_username_text.equals("")) && (!enter_password_text.equals(""))) {
                enter_user(enter_username_text, enter_password_text);
                }
            else {
                System.out.println("A field is empty");
            }
        });


        regist_button.setOnAction(Event -> {
            System.out.println("Button 'Confirm' is chosen");
            regist_button.getScene().getWindow().hide();

            FXMLLoader load1 = new FXMLLoader();
            load1.setLocation(getClass().getResource("registing.fxml"));

            try {
                load1.load();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = load1.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });
    }

    private User enter_user(String enter_username_text,
                            String enter_password_text
    ) {
        String hash_password = enter_password_text; //TODO add hashing
        try {
            Connection connection = Connecting.getDb_connect();
            String query = "SELECT * FROM " + Constants.USER_TABLE.table_name +
                    " WHERE " + Constants.USER_TABLE.login_columm + " = ? AND " + Constants.USER_TABLE.password_columm + " = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, enter_username_text);
            ps.setString(2, hash_password);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString(Constants.USER_TABLE.login_columm);
                String password = resultSet.getString(Constants.USER_TABLE.password_columm);
                int id = resultSet.getInt(Constants.USER_TABLE.id_column);
                String role = resultSet.getString(Constants.USER_TABLE.status_columm);
                User.UserStatus status = User.UserStatus.valueOf(role);
                Integer level = resultSet.getInt(Constants.USER_TABLE.level_columm);
                User user = new User(id, username, password, status, level);
                return user;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
