module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    exports controller;
    exports model;

    opens controller to javafx.fxml;
    opens model to javafx.fxml, com.google.gson;
    exports view.controller;
    opens view.controller to javafx.fxml;
    exports view.menus;
    opens view.menus to javafx.fxml;



}