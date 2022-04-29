package it.ispw.booknook.logic.boundary.mainView;

import it.ispw.booknook.logic.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/borrowdetails-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onDiscoverClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/homepage-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/consultation-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onProfileClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/settings-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }


    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/myLists-view.fxml")));
        Scene scene = ((Node)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }
}
