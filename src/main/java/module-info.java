module it.ispw.booknook {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires javafx.web;
    requires commons.validator;
    requires java.sql;
    requires bcrypt;

    opens it.ispw.booknook to javafx.fxml;
    exports it.ispw.booknook;
    exports it.ispw.booknook.logic.boundary.mainView;
    opens it.ispw.booknook.logic.boundary.mainView to javafx.fxml;
    exports it.ispw.booknook.logic.entity;
    opens it.ispw.booknook.logic.entity to javafx.fxml;
    exports it.ispw.booknook.logic.bean;
    opens it.ispw.booknook.logic.bean to javafx.fxml;
}