package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    @FXML
    private ListView<String> bookList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Creato controller");
    }
}
