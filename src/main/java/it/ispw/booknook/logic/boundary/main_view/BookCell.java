package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.entity.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
        MenuItem list1 = new MenuItem("Books on loan");
        MenuItem list2 = new MenuItem("Books I liked");
        MenuItem list3 = new MenuItem("Want to read");
        addToListBtn.getItems().add(list1);
        addToListBtn.getItems().add(list2);
        addToListBtn.getItems().add(list3);
    }

    public void setInfo(Book book)
    {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        tag1.setText(book.getTag1());
        tag2.setText(book.getTag2());
    }

    public AnchorPane getCell()
    {
        return cell;
    }
}
