package it.ispw.booknook.logic.boundary.mainView;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class UIController {

    protected void changePage(String page, Event event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page)));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }
}
