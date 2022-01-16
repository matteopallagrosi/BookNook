package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FavoritesCell {

    @FXML
    private AnchorPane cell;

    @FXML
    private Label title;

    @FXML
    private Label author;

    @FXML
    private Label expireData;



    public FavoritesCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("favoritesCell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInfo(Book book)
    {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        expireData.setText(book.expireData);
    }

    public AnchorPane getCell()
    {
        return cell;
    }



}
