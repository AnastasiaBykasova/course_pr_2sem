package com.example.course_project_5;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller_registing {
    //Controller_registing regist = FXMLLoader.getController();



    @FXML
    private Button confirm_button;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;

    @FXML
    public void initialize() {
        Connecting setting_connect = new Connecting();
        confirm_button.setOnAction(Event -> {
            System.out.println(" ");
            try {
                //db_Handler.regist_save_data(username_field.getText(), password_field.getText());
                setting_connect.regist_save_data(username_field.getText(), password_field.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

    /*
    @FXML
    public void initialize() {
        DB_handler db_Handler = new DB_handler();
        confirm_button.setOnAction(Event -> {
            try {
                db_Handler.regist_save_data(username_field.getText(), password_field.getText());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

     */



}
