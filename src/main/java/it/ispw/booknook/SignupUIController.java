package it.ispw.booknook;

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

public class SignupUIController {

    @FXML
    private TextField emailTf;

    @FXML
    private PasswordField passwTf;

    @FXML
    private PasswordField passwdConfirmTf;

    @FXML
    private Rectangle errorPanel;

    @FXML
    private Label emailErrorField;

    @FXML
    private Label passwordErrorField;

    @FXML
    private Label confirmErrorField;

    @FXML
    void onSignUpClick(ActionEvent event) throws IOException {
        //recuperare email e password inserite
        //creare il bean che esegue controllo sintattico email e password
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(emailTf.getText());
        if (loginBean.getEmail() == null) {
            System.out.println("Sbagliato");
            showEmailError();
            return;
        }
        loginBean.setPassword(passwTf.getText());
        if (loginBean.getPassword() == null) {
            System.out.println("Sbagliato");
            showPasswordError();
            return;
        }
        if (!passwTf.getText().equals(passwdConfirmTf.getText())) {
            showConfirmError();
            return;
        }
        //se corrette registra utente (inserisce dati sul db), chiamando controller applicativo
        //apre l'homepage
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();

    }

    private void showEmailError() {
        errorPanel.setVisible(true);
        passwordErrorField.setVisible(false);
        confirmErrorField.setVisible(false);
        emailErrorField.setVisible(true);
    }

    private void showPasswordError() {
        errorPanel.setVisible(true);
        emailErrorField.setVisible(false);
        confirmErrorField.setVisible(false);
        passwordErrorField.setVisible(true);
    }

    private void showConfirmError() {
        errorPanel.setVisible(true);
        emailErrorField.setVisible(false);
        passwordErrorField.setVisible(false);
        confirmErrorField.setVisible(true);
    }


    @FXML
    void onSigninClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    public void setErrorMessage() {

    }

    public void registerUser() {

    }


}
