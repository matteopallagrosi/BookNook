package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class BookCell {

    @FXML
    private AnchorPane cell;

    @FXML
    private Button addToListBtn;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listCellItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
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
