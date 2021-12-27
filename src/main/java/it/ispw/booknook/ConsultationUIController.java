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
import java.util.List;
import java.util.Objects;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;


public class ConsultationUIController {

    private ObservableList<String> items;

    private void createListLibraries(ObservableList<String> items, MouseEvent event, Parent root) {
        //compare la lista delle biblioteche con disponiilità
        ListView<String> libraries = new ListView<>();
        libraries.setItems(items);
        libraries.setPrefHeight(136);
        libraries.setPrefWidth(377);
        libraries.setLayoutX(475);
        libraries.setLayoutY(21);
        libraries.setStyle("-fx-focus-color: transparent;");
        libraries.setFixedCellSize(43);
        libraries.getStylesheets().add(Objects.requireNonNull(getClass().getResource("list.css")).toExternalForm());
        AnchorPane pane = (AnchorPane)root.lookup("#listPane");
        pane.getChildren().add(libraries);
        pane.setVisible(true);

        //cambia schermata quando clicco sulla libreria -->
        libraries.setOnMouseClicked(event1 -> {

            System.out.println("clicked on " + libraries.getSelectionModel().getSelectedItem());
            Parent newRoot = null;
            try {
                newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("consultationdetails-view.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = ((ListView)(event1.getSource())).getScene();
            scene.setRoot(newRoot);
            assert newRoot != null;
            newRoot.requestFocus();
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
    void onSearchClick(MouseEvent event) {
        //recupera librerie con disponibilità e le inserisce nella lista view
        items = FXCollections.observableArrayList("Primo", "Secondo", "Terzo", "Quarto",
                "Quinto", "Sesto", "Settimo", "Ottavo");

        createListLibraries(items, event, ((ImageView)(event.getSource())).getScene().getRoot());
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
        ObservableList<String> times = FXCollections.observableArrayList("8.00", "9.30", "10.15", "10.30",
                "11.00", "11.30", "15.15", "16.30");
        timeTable.setItems(times);
    }



    @FXML
    void onBackClick(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("consultation-view.fxml")));
        Scene scene = ((ImageView)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
        items = FXCollections.observableArrayList("Primo", "Secondo", "Terzo", "Quarto",
                "Quinto", "Sesto", "Settimo", "Ottavo");
        createListLibraries(items, event, root);
    }

    @FXML
    void onConsultationClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("consultation-view.fxml")));
        Scene scene = ((Button)(event.getSource())).getScene();
        scene.setRoot(root);
        root.requestFocus();
    }

    @FXML
    void onConfirmClick(ActionEvent event) {
        System.out.println("Click");
        //compare il popup in caso di successo
        Popup popup = new Popup();
        Label label = new Label("This is a Popup");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.setStyle(" -fx-background-color: white;");
        popup.getContent().add(label);


        popup.show(((Button)event.getSource()).getScene().getWindow());


    }

}
