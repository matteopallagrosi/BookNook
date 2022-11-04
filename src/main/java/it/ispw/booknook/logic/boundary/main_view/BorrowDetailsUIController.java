package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.entity.Library;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BorrowDetailsUIController extends UIController implements Initializable {

    @FXML
    private ListView<Library> libraryList;

    @FXML
    private WebView map;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Creato controller");
        setListView();
        map.getEngine().load("https://www.google.com/maps");
    }

    public void setListView()
    {
        Library library1 = new Library("London Library");
        Library library2 = new Library("London Library");
        Library library3 = new Library("London Library");
        Library library4 = new Library("London Library");
        Library library5 = new Library("London Library");
        Library library6 = new Library("London Library");
        Library library7 = new Library("London Library");
        ObservableList<Library> observableList = FXCollections.observableArrayList(library1, library2, library3, library4, library5, library6, library7);
        libraryList.setItems(observableList);
        libraryList.setStyle("-fx-focus-color: transparent;");
        libraryList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/libraryList.css")).toExternalForm());
        libraryList.setFocusTraversable(false);
        libraryList.setCellFactory(listView -> new LibraryViewCell());
    }


    @FXML
    void onDiscoverClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/homepage-view.fxml", event);
    }

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/consultation-view.fxml", event);
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
    public void onBackClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/homepage-view.fxml", event);
    }
}
