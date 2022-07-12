package com.example.course_project_5;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.course_project_5.models.Quote;
import com.example.course_project_5.models.QuoteWorker;
import com.example.course_project_5.models.Quotes;
import com.example.course_project_5.models.User;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static java.util.Date.*;

public class Controller_main {
    private User currentUser;
    private Quotes quotes;
    private Stage userStage = new Stage();

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    private ObservableList<Quote> quoteObservableList;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Quote> tableView;

    @FXML
    private Button adding_in_table;

    @FXML
    private Button changing_smth;

    @FXML
    void initialize() {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'main-screen.fxml'.";
        quotes = new Quotes();
        setupTableView();
        adding_in_table.setOnAction(
                event -> {
                    showEditorScreen();
                }
        );
        changing_smth.setOnAction(
                event -> {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("changing.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
                        userStage.setScene(scene);

                        Controller_change_data changeController = fxmlLoader.getController();
                        changeController.setUser(currentUser,
                                this
                        );

                        userStage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    TableColumn quoteColumn;
    TableColumn teacherColumn;
    TableColumn subjectColumn;
    TableColumn dateColumn;

    private void setupTableView() {
        tableView.setPlaceholder(new Label("Нет данных для отображения"));
        tableView.setItems(FXCollections.observableArrayList());
        quoteColumn = tableView.getColumns().get(0);
        teacherColumn = tableView.getColumns().get(1);
        subjectColumn = tableView.getColumns().get(2);
        dateColumn = tableView.getColumns().get(3);

        quoteColumn.setCellValueFactory(new PropertyValueFactory<>("text"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        quoteObservableList = FXCollections.observableArrayList(quotes);
        tableView.setItems(quoteObservableList);

        tableView.getSelectionModel()
                 .selectedItemProperty()
                 .addListener(
                         (ChangeListener) (observableValue, oldValue, newValue) -> {
                             //Check whether item is selected and set value of selected item to Label
//                             if (tableView.getSelectionModel().getSelectedItem() != null) {
//                                 TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
//                                 Object value = selectionModel.getSelectedItem();
//                         }

                             showEditorScreen();
                         }
                 );

    }

    private void showEditorScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("editing-quotes.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
//                        Parent root = fxmlLoader.getRoot();
            userStage.setScene(scene);

            Controller_editing editQuoteController = fxmlLoader.getController();
            editQuoteController.setup(currentUser,
                    tableView.getSelectionModel().getSelectedItem(),
                    this
            );

            userStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addQuote(Quote quote) {
        quoteObservableList.add(quote);
        tableView.refresh();
        userStage.close();
    }

    public void updateQuote(Quote quote) {
        quoteObservableList.remove(tableView.getSelectionModel().getSelectedItem());
        quoteObservableList.add(quote);
        tableView.refresh();
        userStage.close();
    }

    public void deleteQuote(Quote quote) {
        quoteObservableList.remove(quote);
        tableView.refresh();
        userStage.close();
    }
    public void newUser(User user) {
        currentUser = user;
        userStage.close();
    }
}
