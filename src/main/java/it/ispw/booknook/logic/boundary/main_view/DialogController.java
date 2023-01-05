package it.ispw.booknook.logic.boundary.main_view;

import it.ispw.booknook.logic.bean.LoginBean;
import it.ispw.booknook.logic.control.LoginController;
import it.ispw.booknook.logic.control.SignUpController;
import it.ispw.booknook.logic.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

//opera come controller grafico per i dialog
public class DialogController {

    private static final String LOGIN = "Login";
    private static final String SIGNUP = "Sign up";
    private static final String STYLESHEET = "/it/ispw/booknook/mainView/buttonYellow.css";

    public void createLoginDialog() {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(LOGIN);
        dialog.setHeaderText("Please log in");
        dialog.setResizable(true);

        Label label1 = new Label("Email: ");
        Label label2 = new Label("Password: ");
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        Label label3 = new Label("Don't have an account?");
        Button choiceBtn = new Button(SIGNUP);
        Label label4 = new Label("Username");
        label4.setVisible(false);
        TextField usernameField = new TextField();
        usernameField.setVisible(false);
        Label errorLabel = new Label("Invalid email or password");
        errorLabel.setVisible(false);

        GridPane grid = new GridPane();
        grid.setHgap(7);
        grid.setVgap(5);
        grid.add(label1, 1, 1);
        grid.add(emailField, 2, 1);
        grid.add(label2, 1, 2);
        grid.add(passwordField, 2, 2);
        grid.add(label3, 1, 4);
        grid.add(choiceBtn, 2, 4);
        grid.add(label4, 1,3);
        grid.add(usernameField, 2, 3);
        grid.add(errorLabel, 3, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeCancel);

        choiceBtn.setOnAction(actionEvent -> {
            switch (choiceBtn.getText()) {
                case SIGNUP -> {
                    choiceBtn.setText(LOGIN);
                    label3.setText("Have an account?");
                    label4.setVisible(true);
                    usernameField.setVisible(true);
                    dialog.setTitle(SIGNUP);
                    dialog.setHeaderText("Please sign up");
                    errorLabel.setVisible(false);
                    emailField.clear();
                    passwordField.clear();
                }
                case LOGIN -> {
                    choiceBtn.setText(SIGNUP);
                    label3.setText("Don't have an account?");
                    label4.setVisible(false);
                    usernameField.setVisible(false);
                    dialog.setTitle(LOGIN);
                    dialog.setHeaderText("Please log in");
                    errorLabel.setVisible(false);
                    emailField.clear();
                    passwordField.clear();
                    usernameField.clear();
                }
                default -> errorLabel.setVisible(false);
            }
        });

        Button btnOk = (Button) dialog.getDialogPane().lookupButton(buttonTypeOk);
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            LoginBean loginBean = new LoginBean();
            loginBean.setEmail(emailField.getText());
            loginBean.setPassword(passwordField.getText());
            switch (dialog.getTitle()) {
                case LOGIN -> {
                    LoginController controller = new LoginController();
                    if (!controller.checkUserLogged(loginBean)) {
                        event.consume();
                        errorLabel.setVisible(true);
                    }
                }
                case SIGNUP -> {
                    SignUpController signUpController = new SignUpController();
                    loginBean.setUsername(usernameField.getText());
                    if (loginBean.getEmail() == null || loginBean.getPassword() == null) {
                        errorLabel.setVisible(true);
                        event.consume();
                    } else signUpController.registerReader(loginBean);
                }
                default -> errorLabel.setVisible(true);
            }

        });

        dialog.showAndWait();
    }

    public void createBorrowDialog(ActionEvent actionEvent) {
        String[] choices= {"In-library pickup", "Home delivery"};
        ChoiceDialog d = new ChoiceDialog(choices[0], choices);
        d.setTitle("Delivery method");
        d.setHeaderText("Delivery method");
        d.setContentText("Choose your delivery method");
        d.getDialogPane().setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: Roboto ");
        ((Button) d.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
        d.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource(STYLESHEET)).toExternalForm());
        Optional result =  d.showAndWait();
        //selezionato ritiro in libreria
        if (result.isPresent() && result.get().equals("In-library pickup")) {
            createPickUpDialog();
        }
        //selezionata consegna a casa
        else if (result.isPresent() && result.get().equals("Home delivery")) {
            //passa a schermata con informazioni di spedizione
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/deliverydetails-view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = ((Button) (actionEvent.getSource())).getScene();
            scene.setRoot(root);
            assert root != null;
            root.requestFocus();
        }
    }

    private void createPickUpDialog() {
        //result contiene la stringa In-libraryPickup o homedelivery
        //se homedelivery apre pagina metodo di consegna
        //se pickup informa sui tempi di ritiro
        Dialog<ButtonType> dialogPickup = new Dialog<>();
        dialogPickup.setTitle("In-library Pickup");
        dialogPickup.setContentText("You can pick up your book within three days.");
        ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogPickup.getDialogPane().getButtonTypes().add(confirm);
        dialogPickup.getDialogPane().getButtonTypes().add(cancel);
        dialogPickup.getDialogPane().setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: Roboto ");
        dialogPickup.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource(STYLESHEET)).toExternalForm());
        Optional<ButtonType> result = dialogPickup.showAndWait();
        if (result.isPresent() && result.get() == confirm) {
            //conferma ordine con ritiro in libreria
        }
    }

    public void createReserveDialog(ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirm reservation");
        ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("The librarian has received your reservation,\nyou are queuing now.\nThank you!");
        dialog.getDialogPane().setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: Roboto ");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource(STYLESHEET)).toExternalForm());

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == type) {
            //conferma la prenotazione, invia email, riporta alla schermata iniziale
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/homepage-view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = ((Button)(actionEvent.getSource())).getScene();
            scene.setRoot(root);
            assert root != null;
            root.requestFocus();
        }

    }
}
