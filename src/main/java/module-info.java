module it.ispw.booknook {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.web;
    requires commons.validator;

    opens it.ispw.booknook to javafx.fxml;
    exports it.ispw.booknook;
}