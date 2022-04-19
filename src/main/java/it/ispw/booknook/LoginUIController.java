package it.ispw.booknook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;


public class LoginUIController {

    @FXML
    private TextField emailTf;

    @FXML
    private PasswordField passwTf;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        //recupera email e password
        //crea loginBean che esegue controllo sintattico
        LoginBean loginB = new LoginBean();
        loginB.setEmail(emailTf.getText());
        //verifica email e password inseriti su database
        //se corretti apre homepage


        //apre hompepage
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onSignupClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

}