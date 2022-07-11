package com.example.course_project_5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

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

    private void enter_user(String enter_username_text, String enter_password_text) {
    }
}