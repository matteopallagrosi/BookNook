package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController extends UIController implements Initializable {

    @FXML
    private ListView<Book> bookList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Creato controller");
        setListView();
    }

    public void setListView()
    {
        Book book1 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "Drama", "Teenagers");
        Book book2 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "Drama", "Teenagers");
        Book book3 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "Drama", "Teenagers");
        Book book4 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "Drama", "Teenagers");
        ObservableList<Book> observableList = FXCollections.observableArrayList(book1, book2, book3, book4);
        bookList.setItems(observableList);
        bookList.setStyle("-fx-focus-color: transparent;");
        bookList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/bookList.css")).toExternalForm());
        bookList.setFocusTraversable(false);
        bookList.setCellFactory(listView -> new ListViewCell());
    }

    @FXML
    void onBorrowClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/borrowdetails-view.fxml", event);
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
}
