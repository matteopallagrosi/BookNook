package it.ispw.booknook.logic.boundary.main_view;

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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static final String selectedColor = "#e9bf8e";
    private static final String defaultColor = "#8a8a8a66";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane topicPane = null;
        try {
            topicPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/topics-view.fxml"));
        } catch (IOException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
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
        mapBtn.setFill(Color.web(selectedColor));
        topicBtn.setFill(Color.web(defaultColor));
        myClubsBtn.setFill((Color.web(defaultColor)));
        AnchorPane mapPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/clubsMap-view.fxml"));
        currentPane.setCenter(mapPane);
    }

    @FXML
    void onMyClubsClick(MouseEvent event) throws IOException {
        mapBtn.setFill(Color.web(defaultColor));
        topicBtn.setFill(Color.web(defaultColor));
        myClubsBtn.setFill((Color.web(selectedColor)));
        AnchorPane mapPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/myClubs-view.fxml"));
        currentPane.setCenter(mapPane);

    }

    @FXML
    void onMyListClick(ActionEvent event) {

    }

    @FXML
    void onTopicsClick(MouseEvent event) throws IOException {
        mapBtn.setFill(Color.web(defaultColor));
        topicBtn.setFill(Color.web(selectedColor));
        myClubsBtn.setFill((Color.web(defaultColor)));
        AnchorPane topicPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/topics-view.fxml"));
        currentPane.setCenter(topicPane);
    }
}
