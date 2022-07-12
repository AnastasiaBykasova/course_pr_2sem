module com.example.course_project_5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.course_project_5 to javafx.fxml;
    exports com.example.course_project_5;
    exports com.example.course_project_5.helpers;
    exports com.example.course_project_5.models;
    opens com.example.course_project_5.helpers to javafx.fxml;
    opens com.example.course_project_5.models to javafx.fxml;
}