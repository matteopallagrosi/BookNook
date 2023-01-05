package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.LibraryBean;
import it.ispw.booknook.logic.control.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LibraryCell {
    @FXML
    private Button borrowBtn;

    @FXML
    private Label name;

    @FXML
    private AnchorPane cell;

    public LibraryCell()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/ispw/booknook/mainView/libraryListItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        setBtnHandler();
    }

    private void setBtnHandler() {
        borrowBtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DialogController dialogController = new DialogController();
                //verifica se l'utente è loggato oppure no
                LoginController loginController = new LoginController();
                //se l'utente non è loggato apre un dialog per login/signup
                if (!loginController.verifyLogin()) {
                    //apre un dialog per login/signup
                    dialogController.createLoginDialog();
                }

                if (borrowBtn.getText().equals("Borrow")) {
                    dialogController.createBorrowDialog(actionEvent);
                }
                else {
                    //apre dialog per avvenuta reservation e notifica alla biblioteca
                    dialogController.createReserveDialog(actionEvent);
                }
            }
        });
    }


    public void setInfo(LibraryBean library)
    {
        name.setText(library.getName());
        if (library.isAvailable()) {
            borrowBtn.setText("Borrow");
        }
        else {
            borrowBtn.setText("Reserve");
        }
    }

    public AnchorPane getCell()
    {
        return cell;
    }

}
