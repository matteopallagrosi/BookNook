package it.ispw.booknook.logic.boundary.mainView;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.Objects;


public class ConsultationUIController extends UIController {

    private ObservableList<String> items;

    @FXML
    private AnchorPane listPane;


    public void createListLibraries(ObservableList<String> items) {
        //compare la lista delle biblioteche con disponiilità
        ListView<String> libraries = new ListView<>();
        libraries.setItems(items);
        libraries.setPrefHeight(136);
        libraries.setPrefWidth(377);
        libraries.setLayoutX(475);
        libraries.setLayoutY(21);
        libraries.setStyle("-fx-focus-color: transparent;");
        libraries.setFixedCellSize(43);
        libraries.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/list.css")).toExternalForm());
        listPane.getChildren().add(libraries);
        listPane.setVisible(true);

        //cambia schermata quando clicco sulla libreria -->
        libraries.setOnMouseClicked(event1 -> {

            System.out.println("clicked on " + libraries.getSelectionModel().getSelectedItem());
            Parent newRoot = null;
            try {
                newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/consultationdetails-view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = ((ListView)(event1.getSource())).getScene();
            scene.setRoot(newRoot);
            assert newRoot != null;
            newRoot.requestFocus();
        });

    }


    @FXML
    void onSearchClick(MouseEvent event) {
        //recupera librerie con disponibilità e le inserisce nella lista view
        items = FXCollections.observableArrayList("Primo", "Secondo", "Terzo", "Quarto",
                "Quinto", "Sesto", "Settimo", "Ottavo");

        createListLibraries(items);
    }

    @FXML
    void onDiscoverClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/homepage-view.fxml", event);
    }

    @FXML
    void onProfileClick(MouseEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/settings-view.fxml", event);
    }

    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/myLists-view.fxml", event);
    }

}
