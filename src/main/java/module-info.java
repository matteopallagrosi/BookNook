module it.ispw.booknook {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens it.ispw.booknook to javafx.fxml;
    exports it.ispw.booknook;
}