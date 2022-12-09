package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.control.BorrowBookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DiscoverUIController extends UIController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultList;

    @FXML
    private Label categoriesLabel;

    @FXML
    private Button romanceBtn;

    @FXML
    private Button scifiBtn;

    @FXML
    private Button adventureBtn;

    @FXML
    private Button thrillerBtn;


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

    @FXML
    void borrowBookByName(ActionEvent event) {
            String searchedTx = searchField.getText(); //titolo o autore libro richiesto
            BookBean searchedBook = new BookBean(searchedTx);
            BorrowBookController controller = new BorrowBookController();

            List<String> results = controller.borrowByName(searchedBook);
            resultList.getItems().clear();
            resultList.getItems().addAll(results);
            categoriesLabel.setVisible(false);
            scifiBtn.setVisible(false);
            romanceBtn.setVisible(false);
            thrillerBtn.setVisible(false);
            adventureBtn.setVisible(false);
            resultList.setVisible(true);
    }

}


