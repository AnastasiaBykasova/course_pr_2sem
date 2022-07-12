package com.example.course_project_5;

import com.example.course_project_5.models.User;
import com.example.course_project_5.models.UserWorker;
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
    private TextField name_of_group;

    @FXML
    public void initialize() {
        Connecting setting_connect = new Connecting();
        confirm_button.setOnAction(Event -> {
                    System.out.println(" ");
                    String enter_username_text = username_field.getText().trim();
                    String enter_password_text = password_field.getText().trim();
                    String enter_group_text = name_of_group.getText().trim();

                    if ((!enter_username_text.isEmpty()) &&
                            (!enter_password_text.isEmpty()) &&
                            (!enter_group_text.isEmpty()) &&
                            Integer.getInteger(enter_group_text) != null) {
                        User user = UserWorker.createUser(username_field.getText(),
                                password_field.getText(),
                                Integer.getInteger(name_of_group.getText())
                        );
                        callMainScreen(user);
                    }
                    else {
                        System.out.println("Не все поля заполнены");
                    }
                }
        );

    }

    private void callMainScreen(User user) {
        FXMLLoader load1 = new FXMLLoader();
        load1.setLocation(getClass().getResource("main-screen.fxml"));

        try {
            load1.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller_main main = load1.getController();
        main.setCurrentUser(user);
        Parent root1 = load1.getRoot();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.showAndWait();
    }
}
