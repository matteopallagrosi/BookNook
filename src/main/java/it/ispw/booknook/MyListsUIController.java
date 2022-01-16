package it.ispw.booknook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MyListsUIController implements Initializable {

    @FXML
    private ListView<Book> currentList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentList.setStyle("-fx-focus-color: transparent;");
        currentList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("bookList.css")).toExternalForm());
        currentList.setFocusTraversable(false);
        Book book1 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "03/07/2023");
        Book book2 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "04/05/2024");
        Book book3 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "07/10/2028");
        Book book4 = new Book("Harry Potter and the Half-Blood Prince", "J. K. Rowling", "05/12/2029");
        ObservableList<Book> observableList = FXCollections.observableArrayList(book1, book2, book3, book4);
        currentList.setItems(observableList);
        currentList.setCellFactory(listView -> new FavoritesViewCell());

    }

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
