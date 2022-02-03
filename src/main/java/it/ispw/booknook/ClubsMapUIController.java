package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ClubsMapUIController implements Initializable {
    @FXML
    private WebView map;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        map.getEngine().load("https://www.google.com/maps");
    }


}
