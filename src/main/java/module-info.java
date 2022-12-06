module it.ispw.booknook {
    requires java.sql;
    requires bcrypt;
    requires com.esri.arcgisruntime;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires commons.validator;

    opens it.ispw.booknook to javafx.fxml;
    exports it.ispw.booknook;
    exports it.ispw.booknook.logic.boundary.main_view;
    opens it.ispw.booknook.logic.boundary.main_view to javafx.fxml;
    exports it.ispw.booknook.logic.entity;
    opens it.ispw.booknook.logic.entity to javafx.fxml;
    exports it.ispw.booknook.logic.bean;
    opens it.ispw.booknook.logic.bean to javafx.fxml;
}