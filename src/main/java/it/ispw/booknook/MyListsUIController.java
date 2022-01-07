package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class MyListsUIController {
    @FXML
    void onBackClick(MouseEvent event) {

    }

    @FXML
    void onLikesClick(MouseEvent event) {
        System.out.println("Like");

    }

    @FXML
    void onLoanClick(MouseEvent event) {
        System.out.println("loan");

    }

    @FXML
    void onWantToReadClick(MouseEvent event) {
        System.out.println("WantToRead");
    }

}
