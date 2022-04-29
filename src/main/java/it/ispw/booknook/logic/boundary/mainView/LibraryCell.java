package it.ispw.booknook.logic.boundary.mainView;

import it.ispw.booknook.logic.entity.Library;
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
                    String[] choices= {"In-library pickup", "Home delivery"};
                    ChoiceDialog d = new ChoiceDialog(choices[0], choices);
                    d.setTitle("Delivery method");
                    d.setHeaderText("Delivery method");
                    d.setContentText("Choose your delivery method");
                    d.getDialogPane().setStyle("-fx-font-size: 15px;" +
                            "-fx-font-family: Roboto ");
                    ButtonBar buttonBar = (ButtonBar)d.getDialogPane().lookup(".button-bar");
                    ((Button) d.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Cancel");
                    buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-color: #e9bf8e;" +
                            "-fx-background-radius: 8;" +
                            "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2);" +
                            "-fx-text-fill: white;" +
                            "-fx-font-family: 'Roboto Medium'"));

                    Optional<String> result =  d.showAndWait();
                    if (result.isPresent()) {
                        System.out.println(result.get());
                        //result contiene la stringa In-libraryPickup o homedelivery
                        //se homedelivery apre pagina metodo di consegna
                        //se pickup informa sui tempi di ritiro
                        switch(result.get()) {
                            case "In-library pickup": {
                                Dialog<ButtonType> dialogPickup = new Dialog<ButtonType>();
                                dialogPickup.setTitle("In-library Pickup");
                                dialogPickup.setContentText("You can pick up your book within three days.");
                                ButtonType confirm = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
                                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                                dialogPickup.getDialogPane().getButtonTypes().add(confirm);
                                dialogPickup.getDialogPane().getButtonTypes().add(cancel);
                                dialogPickup.getDialogPane().setStyle("-fx-font-size: 15px;" +
                                        "-fx-font-family: Roboto ");
                                buttonBar.getButtons().forEach(b -> b.setStyle("-fx-background-color: #e9bf8e;" +
                                        "-fx-background-radius: 8;" +
                                        "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.5), 10, 0, 0, 2);" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-family: 'Roboto Medium'"));
                                if (dialogPickup.showAndWait().get() == confirm) {
                                    System.out.println("Confermato");
                                    //conferma ordine con ritiro in libreria
                                }
                                break;
                            }
                            case "Home delivery": {
                                //passa a schermata con informazioni di spedizione
                                Parent root = null;
                                try {
                                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/deliverydetails-view.fxml")));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Scene scene = ((Button)(actionEvent.getSource())).getScene();
                                scene.setRoot(root);
                                root.requestFocus();
                            }
                        }
                    }
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
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/homepage-view.fxml")));
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
