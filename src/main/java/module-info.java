module Signup {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.desktop;
        requires javafx.media;
        requires com.google.gson;


        exports controller;
        exports model;
        opens model to com.google.gson;
        exports view;
        exports viewG.menus;
        exports viewG.GUIController;
        exports model.Enums;
        exports model.Enums.Images;

        opens controller to javafx.fxml;
        opens model to javafx.fxml;
//        opens model to javafx.fxml, com.google.gson;
        exports view.controller;
        opens view.controller to javafx.fxml;
        exports view.menus;
        opens view.menus to javafx.fxml;



        }