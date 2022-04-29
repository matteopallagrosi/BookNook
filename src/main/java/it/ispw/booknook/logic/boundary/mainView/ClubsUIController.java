package it.ispw.booknook.logic.boundary.mainView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClubsUIController implements Initializable {
    @FXML
    private BorderPane currentPane;

    @FXML
    private Label createClubBtn;

    @FXML
    private Rectangle myClubsBtn;

    @FXML
    private Rectangle mapBtn;

    @FXML
    private Rectangle topicBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane topicPane = null;
        try {
            topicPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/topics-view.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPane.setCenter(topicPane);

    }

    @FXML
    void onBackClick(MouseEvent event) {

    }

    @FXML
    void onConsultationClick(ActionEvent event) {

    }

    @FXML
    void onCreateClubClick(MouseEvent event) {

    }

    @FXML
    void onDiscoverClick(ActionEvent event) {

    }

    @FXML
    void onMapClick(MouseEvent event) throws IOException {
        mapBtn.setFill(Color.web("#e9bf8e"));
        topicBtn.setFill(Color.web("#8a8a8a66"));
        myClubsBtn.setFill((Color.web("#8a8a8a66")));
        AnchorPane mapPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/clubsMap-view.fxml"));
        currentPane.setCenter(mapPane);
    }

    @FXML
    void onMyClubsClick(MouseEvent event) throws IOException {
        mapBtn.setFill(Color.web("#8a8a8a66"));
        topicBtn.setFill(Color.web("#8a8a8a66"));
        myClubsBtn.setFill((Color.web("#e9bf8e")));
        AnchorPane mapPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/myClubs-view.fxml"));
        currentPane.setCenter(mapPane);

    }

    @FXML
    void onMyListClick(ActionEvent event) {

    }

    @FXML
    void onTopicsClick(MouseEvent event) throws IOException {
        mapBtn.setFill(Color.web("#8a8a8a66"));
        topicBtn.setFill(Color.web("#e9bf8e"));
        myClubsBtn.setFill((Color.web("#8a8a8a66")));
        AnchorPane topicPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/topics-view.fxml"));
        currentPane.setCenter(topicPane);
    }
}
