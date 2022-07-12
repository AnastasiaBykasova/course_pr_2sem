package com.example.course_project_5;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.course_project_5.models.User;
import com.example.course_project_5.models.UserWorker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller_change_data {
    private User user;
    private Controller_main main;

    public void setUser(User user, Controller_main main) {
        this.user = user;
        this.main = main;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirm_button;

    @FXML
    private TextField name_of_group;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField username_field;

    @FXML
    void initialize() {
        assert confirm_button != null : "fx:id=\"confirm_button\" was not injected: check your FXML file 'changing.fxml'.";
        assert name_of_group != null : "fx:id=\"name_of_group\" was not injected: check your FXML file 'changing.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'changing.fxml'.";
        assert username_field != null : "fx:id=\"username_field\" was not injected: check your FXML file 'changing.fxml'.";
        confirm_button.setOnAction(event -> {
                    if (!username_field.getText().isEmpty()) {
                        user = UserWorker.updateLogin(user, username_field.getText());
                    }
                    if (!password_field.getText().isEmpty()) {
                        user = UserWorker.updatePassword(user, password_field.getText());
                    }
                    if (!name_of_group.getText().isEmpty()) {
                        user = UserWorker.updateGroup(user, Integer.parseInt(name_of_group.getText()));
                    }
                    main.newUser(user);
                }
        );
    }

}
