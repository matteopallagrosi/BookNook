package it.ispw.booknook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClubsUIController implements Initializable {
    @FXML
    private BorderPane currentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane topicPane = null;
        try {
            topicPane = FXMLLoader.load(getClass().getResource("topics-view.fxml"));
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
    void onMapClick(MouseEvent event) {

    }

    @FXML
    void onMyClubsClick(MouseEvent event) {

    }

    @FXML
    void onMyListClick(ActionEvent event) {

    }

    @FXML
    void onTopicsClick(MouseEvent event) throws IOException {
        AnchorPane topicPane = FXMLLoader.load(getClass().getResource("topics-view.fxml"));
        currentPane.setCenter(topicPane);
    }
}
