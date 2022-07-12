package com.example.course_project_5;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.course_project_5.models.Quote;
import com.example.course_project_5.models.QuoteWorker;
import com.example.course_project_5.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_editing {
    private User currentUser;
    private Quote currentQuote;
    private Controller_main main;

    public void setup(User user, Quote quote, Controller_main main) {
        currentUser = user;
        currentQuote = quote;
        this.main = main;
        setupFields(quote);
        editButton.setDisable(!isUserAuthorized());
        deleteButton.setDisable(!isUserAuthorized());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private TextArea quoteTextArea;

    @FXML
    private TextField subjectTextField;

    @FXML
    private TextField teacherTextField;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert datePicker != null : "fx:id=\"datePicker\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert quoteTextArea != null : "fx:id=\"quoteTextArea\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert subjectTextField != null : "fx:id=\"subjectTextField\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        assert teacherTextField != null : "fx:id=\"teacherTextField\" was not injected: check your FXML file 'editing-quotes.fxml'.";
        addButton.setOnAction(
                event -> {
                    if (!quoteTextArea.getText().isEmpty() &&
                            !subjectTextField.getText().isEmpty() &&
                            !teacherTextField.getText().isEmpty()) {
                        Quote quote = new Quote(-1,
                                quoteTextArea.getText(),
                                java.sql.Date.valueOf(datePicker.getValue()),
                                subjectTextField.getText(),
                                teacherTextField.getText(),
                                currentUser.getId(),
                                currentUser.getLevel()
                        );
                        quote = QuoteWorker.addQuote(quote);
                        main.addQuote(quote);
                    }
                }
        );
        editButton.setOnAction(
                event -> {
                    if (isUserAuthorized()) {
                        if (!quoteTextArea.getText().isEmpty() &&
                                !subjectTextField.getText().isEmpty() &&
                                !teacherTextField.getText().isEmpty()) {
                            Quote quote = new Quote(currentQuote.getId(),
                                    quoteTextArea.getText(),
                                    java.sql.Date.valueOf(datePicker.getValue()),
                                    subjectTextField.getText(),
                                    teacherTextField.getText(),
                                    currentUser.getId(),
                                    currentUser.getLevel()
                            );
                            quote = QuoteWorker.updateQuote(quote);
                            main.updateQuote(quote);
                        }
                    } else {
                        System.out.println("Access denied");
                    }


                }
        );
        deleteButton.setOnAction(
                event -> {
                    if (isUserAuthorized()) {
                        QuoteWorker.deleteQuote(currentQuote.getId());
                        main.deleteQuote(currentQuote);
                    } else {
                        System.out.println("Access denied");
                    }
                }
        );
    }

    private boolean isUserAuthorized() {
        if (currentQuote.getCreatorID() == currentUser.getId() ||
                currentUser.getStatus().equals(User.UserStatus.Super) ||
                currentUser.getLevel() == (currentQuote.getAccessLevel())
        ) {
            return true;
        }
        return false;
    }
    private void setupFields(Quote quote) {
        if (quote != null) {
            quoteTextArea.setText(quote.getText());
            teacherTextField.setText(quote.getTeacher());
            subjectTextField.setText(quote.getSubject());
            datePicker.setValue(quote.getCreationDate().toLocalDate());
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        } else {
            quoteTextArea.setText("");
            teacherTextField.setText("");
            subjectTextField.setText("");
            datePicker.setValue(LocalDate.now());
            editButton.setDisable(true);
            deleteButton.setDisable(true);
        }
    }

}
