package it.ispw.booknook;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


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
        paneCenter.setVisible(true);

        //cambia schermata quando clicco sulla libreria -->
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
            WebView webView = (WebView)scene.lookup("#map");
            webView.getEngine().load("https://www.google.com/maps");

            DatePicker picker = (DatePicker)scene.lookup("#datePicker");
            picker.setStyle("-fx-focus-color: transparent;");
            picker.setDayCellFactory(p -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();

                    setDisable(empty || date.compareTo(today) < 0 || date.getDayOfWeek() == DayOfWeek.SUNDAY);
                }
            });
        });
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
        System.out.println("OK");
        //mostra gli orari disponibili per quella data
        DatePicker picker = (DatePicker)event.getSource();
        ListView<String> timeTable = (ListView<String>) picker.getScene().lookup("#timeTable");
        //mostra soltanto gli orari disponibili
        ObservableList<String> items = FXCollections.observableArrayList("8.00", "9.30", "10.15", "10.30",
                "11.00", "11.30", "15.15", "16.30");
        timeTable.setItems(items);
    }

}
