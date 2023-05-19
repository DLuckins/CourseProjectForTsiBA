module com.example.bapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.bapp to javafx.fxml;
    exports com.example.bapp;
}