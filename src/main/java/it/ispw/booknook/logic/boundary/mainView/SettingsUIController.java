package it.ispw.booknook.logic.boundary.mainView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsUIController extends UIController implements Initializable {
    @FXML
    private BorderPane currentSettingsPane;

    @FXML
    private Rectangle accountBtn;

    @FXML
    private Rectangle profSettingsBtn;

    @FXML
    private Rectangle deleteBtn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AnchorPane accountPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/accountsettings-view.fxml"));
            currentSettingsPane.setCenter(accountPane);
        } catch (IOException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        }
    }

    @FXML
    void onAccountSettingsClick(MouseEvent event) throws IOException {
        accountBtn.setFill(Color.web("#e9bf8e"));
        profSettingsBtn.setFill(Color.web("#8a8a8a66"));
        AnchorPane accountPane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/accountsettings-view.fxml"));
        currentSettingsPane.setCenter(accountPane);

    }

    @FXML
    void onBackClick(MouseEvent event) {

    }

    @FXML
    void onProfileSettingsClick(MouseEvent event) throws IOException {
        profSettingsBtn.setFill(Color.web("#e9bf8e"));
        accountBtn.setFill(Color.web("#8a8a8a66"));
        AnchorPane profilePane = FXMLLoader.load(getClass().getResource("/it/ispw/booknook/mainView/profile-settings-view.fxml"));
        currentSettingsPane.setCenter(profilePane);
    }

    @FXML
    void onDeleteClick(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete account");
        alert.setHeaderText("Delete account");
        alert.setContentText("By confirming you will lose all data associated with your account. Are you sure?");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Confirm");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancel);
        ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
        alert.getDialogPane().setStyle("-fx-font-size: 15px;" +
                "-fx-font-family: Roboto ");
        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-color: #e9bf8e;" +
                "-fx-background-radius: 8;" +
                "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2);" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Roboto Medium'"));
        alert.getDialogPane().setPrefWidth(300);
        Optional result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                System.out.println("Confermato");
            }
            else {
                System.out.println("Annullato");
            }
        }
    }

    @FXML
    void onDiscoverClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/homepage-view.fxml", event);
    }

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/consultation-view.fxml", event);
    }

    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/myLists-view.fxml", event);
    }

}
