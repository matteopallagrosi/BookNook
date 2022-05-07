package it.ispw.booknook.logic.boundary.mainView;

import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.control.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Objects;


public class LoginUIController {

    @FXML
    private TextField emailTf;

    @FXML
    private PasswordField passwTf;

    @FXML
    private Label errorField;

    @FXML
    private Rectangle errorPanel;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        //recupera email e password
        //crea loginBean
        LoginBean loginB = new LoginBean(emailTf.getText(), passwTf.getText());
        //verifica email e password inseriti su database
        LoginController controller = new LoginController();
        //se corretti apre homepage
        if (controller.checkUserLogged(loginB)) {
            //apre hompepage
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/homepage-view.fxml")));
            Scene scene = ((Button)(event.getSource())).getScene();
            scene.setRoot(root);
            root.requestFocus();
        }
        //altrimenti mostra messaggio d'errore
        else {
            errorPanel.setVisible(true);
            errorField.setVisible(true);
        }
    }

    @FXML
    void onSignupClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/signup-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

}