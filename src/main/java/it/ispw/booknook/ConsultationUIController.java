package it.ispw.booknook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;

public class ConsultationUIController {

    @FXML
    void onSearchClick(MouseEvent event) {
        //compare la lista delle biblioteche con disponiilità
        ListView<String> libraries = new ListView<>();
        //recupera librerie con disponibilità e le inserisce nella lista view
        ObservableList<String> items = FXCollections.observableArrayList("Primo", "Secondo", "Terzo", "Quarto",
                "Quinto", "Sesto", "Settimo", "Ottavo");
        libraries.setItems(items);
        libraries.setPrefHeight(136);
        libraries.setPrefWidth(377);
        ImageView searchIcon = (ImageView) event.getSource();
        AnchorPane anchorPane = (AnchorPane) searchIcon.getParent();
        BorderPane borderPane = (BorderPane) anchorPane.getParent();
        AnchorPane paneCenter = (AnchorPane) borderPane.getCenter();
        paneCenter.getChildren().add(libraries);
        libraries.setLayoutX(475);
        libraries.setLayoutY(21);
        libraries.setStyle("-fx-focus-color: transparent;");
        libraries.setFixedCellSize(43);
        libraries.getStylesheets().add(Objects.requireNonNull(getClass().getResource("list.css")).toExternalForm());

        //cambia schermata -->
        libraries.setOnMouseClicked(event1 -> {
                System.out.println("clicked on " + libraries.getSelectionModel().getSelectedItem());
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("consultationdetails-view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = ((ImageView)(event.getSource())).getScene();
            scene.setRoot(root);
            assert root != null;
            root.requestFocus();
            DatePicker picker = (DatePicker)scene.lookup("#datePicker");
            picker.setStyle("-fx-focus-color: transparent;");
        });
        paneCenter.setVisible(true);
    }


    @FXML
    void onDiscoverClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    //selezione della data
    @FXML
    void onDateClick(ActionEvent event) {
        //mostra gli orari disponibili per quella data
        DatePicker picker = (DatePicker)event.getSource();
        ListView<String> timeTable = (ListView<String>) picker.getScene().lookup("#timeTable");
        ObservableList<String> items = FXCollections.observableArrayList("8.00", "9.30", "10.15", "10.30",
                "11.00", "11.30", "15.15", "16.30");
        timeTable.setItems(items);
    }

}
