package it.ispw.booknook;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Random;

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
        Random random = new Random();
        if (random.nextBoolean()) {
            borrowBtn.setText("Reserve");
        }
        setBtnHandler();
    }

    private void setBtnHandler() {
        borrowBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (borrowBtn.getText().equals("Borrow")) {
                    System.out.println("Borrow");
                    //apri dialog per scelta metodo consegna
                }
                else {
                    System.out.println("Reserve");
                    //apre dialog per avvenuta reservation e notifica alla biblioteca
                }

            }
        });
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
