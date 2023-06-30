module Signup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires com.google.gson;

    opens view to javafx.fxml;
    exports model;
    opens model to com.google.gson;
    exports view;
    exports viewG.menus;
    exports viewG.GUIController;
    exports model.Enums;
}