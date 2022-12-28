package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.control.BorrowBookController;
import it.ispw.booknook.logic.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultsUIController extends UIController {

    @FXML
    private ListView<BookBean> bookList;

    @FXML
    private Label author;

    @FXML
    private ImageView coverImage;

    @FXML
    private Label title;

    @FXML
    private FlowPane box;

    @FXML
    private MenuButton listBtn;

    @FXML
    private Button borrowBtn;

    public void setListView(String details)
    {
        BookBean bookBean = new BookBean();
        //invoca il controller applicativo
        bookBean.splitDetails(details);
        List<BookBean> results = new BorrowBookController().processRequestedBook(bookBean);
        author.setText(results.get(0).getAuthor());
        title.setText(results.get(0).getTitle());
        coverImage.setImage(new Image(results.get(0).getCover()));

        results.get(0).getTags().forEach(tag -> {
            Button tagBtn = new Button(tag);
            tagBtn.setStyle("-fx-background-radius: 8; -fx-background-color:  #c9c9c9; -fx-effect:  dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2)");
            box.getChildren().add(tagBtn);
        });

        String ISBN = results.get(0).getISBN();

        //L'utente ha selezionato il libro principale
        borrowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/it/ispw/booknook/mainView/borrowdetails-view.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BorrowDetailsUIController controller = loader.getController();
                controller.displayLibraryList(ISBN, title.getText());
                Scene scene = ((Node)(actionEvent.getSource())).getScene();
                scene.setRoot(root);
                root.requestFocus();
            }
        });


        results.remove(0);
        ObservableList<BookBean> observableList = FXCollections.observableArrayList(results);
        bookList.setItems(observableList);
        bookList.setStyle("-fx-focus-color: transparent;");
        bookList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/bookList.css")).toExternalForm());
        bookList.setFocusTraversable(false);
        bookList.setCellFactory(listView -> new ListViewCell());

        MenuItem list1 = new MenuItem("Books I liked");
        MenuItem list2 = new MenuItem("Want to read");
        listBtn.getItems().add(list1);
        listBtn.getItems().add(list2);
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
