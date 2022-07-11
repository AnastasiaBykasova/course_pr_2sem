module com.example.course_project_5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.course_project_5 to javafx.fxml;
    exports com.example.course_project_5;
}