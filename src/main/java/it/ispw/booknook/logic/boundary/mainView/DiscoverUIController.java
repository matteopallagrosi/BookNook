package it.ispw.booknook.logic.boundary.mainView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.*;

public class DiscoverUIController {

    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/consultation-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onSearchClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/bookorder-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
        //riempire la lista dei libri risultanti nella schermata successiva
    }


    @FXML
    void onProfileClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/settings-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }


    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/myLists-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onClubsClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/clubs-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }
}


