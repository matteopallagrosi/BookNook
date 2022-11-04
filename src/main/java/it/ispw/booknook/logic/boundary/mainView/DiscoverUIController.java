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

public class DiscoverUIController extends UIController {

    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/consultation-view.fxml", event);
    }

    @FXML
    void onSearchClick(MouseEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/bookorder-view.fxml", event);
        //riempire la lista dei libri risultanti nella schermata successiva
    }


    @FXML
    void onProfileClick(MouseEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/settings-view.fxml", event);
    }


    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/myLists-view.fxml", event);

    }

    @FXML
    void onClubsClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/clubs-view.fxml", event);
    }
}


