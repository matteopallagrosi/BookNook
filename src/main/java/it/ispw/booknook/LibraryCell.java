package it.ispw.booknook;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
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
        borrowBtn.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (borrowBtn.getText().equals("Borrow")) {
                    System.out.println("Borrow");
                    //apri dialog per scelta metodo consegna
                }
                else {
                    System.out.println("Reserve");
                    //apre dialog per avvenuta reservation e notifica alla biblioteca
                    borrowBtn.setOnMouseClicked(mouseEvent -> {
                        Dialog<ButtonType> dialog = new Dialog<>();
                        dialog.setTitle("Confirm reservation");
                        ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("The librarian has received your reservation,\nyou are queuing now.\nThank you!");
                        dialog.getDialogPane().setStyle("-fx-font-size: 15px;" +
                                "-fx-font-family: Roboto ");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        ButtonBar buttonBar = (ButtonBar)dialog.getDialogPane().lookup(".button-bar");
                        buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-color: #e9bf8e;" +
                                "-fx-background-radius: 8;" +
                                "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2);" +
                                "-fx-text-fill: white;" +
                                "-fx-font-family: 'Roboto Medium'"));

                        Optional<ButtonType> result = dialog.showAndWait();
                        if (result.isPresent() && result.get() == type) {
                            //conferma la prenotazione, invia email, riporta alla schermata iniziale
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage-view.fxml")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = ((Button)(mouseEvent.getSource())).getScene();
                            scene.setRoot(root);
                            assert root != null;
                            root.requestFocus();
                        }
                    });
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
