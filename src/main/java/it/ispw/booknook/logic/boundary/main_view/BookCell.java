package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.BookBean;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class BookCell {

    @FXML
    private AnchorPane cell;

    @FXML
    private MenuButton addToListBtn;

    @FXML
    private Label author;

    @FXML
    private Button borrowBtn;

    @FXML
    private ImageView cover;

    @FXML
    private Button tag1;

    @FXML
    private Button tag2;

    @FXML
    private Label title;

    @FXML
    private FlowPane box;


    public BookCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/ispw/booknook/mainView/listCellItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        //inizializzare le liste dei preferiti
        setFavorites();
    }

    private void setFavorites() {
        //recuperare liste effettive
        MenuItem list1 = new MenuItem("Books I liked");
        MenuItem list2 = new MenuItem("Want to read");
        addToListBtn.getItems().add(list1);
        addToListBtn.getItems().add(list2);
    }

    public void setInfo(BookBean book)
    {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        cover.setImage(book.getCoverImage());
        book.getTags().forEach(tag -> {
            Button tagBtn = new Button(tag);
            tagBtn.setStyle("-fx-background-radius: 8; -fx-background-color:  #c9c9c9; -fx-effect:  dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2)");
            box.getChildren().add(tagBtn);
        });

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
                controller.displayLibraryList(book.getIsbn(), title.getText());
                Scene scene = ((Node)(actionEvent.getSource())).getScene();
                scene.setRoot(root);
                root.requestFocus();
            }
        });
    }

    public AnchorPane getCell()
    {
        return cell;
    }
}
