package it.ispw.booknook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LibraryCell {
    @FXML
    private Button borrowBtn;

    @FXML
    private Label name;

    @FXML
    private AnchorPane cell;

    public LibraryCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("libraryListItem.fxml"));
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

    public void setInfo(Library library)
    {
        name.setText(library.getName());
    }

    public AnchorPane getCell()
    {
        return cell;
    }

}
