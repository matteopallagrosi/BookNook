package it.ispw.booknook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MyClubsUIController implements Initializable {

    @FXML
    private ListView<Club> clubsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();
    }

    public void setListView()
    {
        Club club1 = new Club("Simo's wonderful book club ", 20500l, "Keeping romance, history, humor, mystery, love, intrigue, and passion interesting, fun, and clean for all Christians. We discuss everything from reading to music to playing games to what makes a book good...and the best part? It's all clean!!");
        Club club2 = new Club("Simo's wonderful book club ", 20500l, "Keeping romance, history, humor, mystery, love, intrigue, and passion interesting, fun, and clean for all Christians. We discuss everything from reading to music to playing games to what makes a book good...and the best part? It's all clean!!");
        Club club3 = new Club("Simo's wonderful book club ", 20500l, "Keeping romance, history, humor, mystery, love, intrigue, and passion interesting, fun, and clean for all Christians. We discuss everything from reading to music to playing games to what makes a book good...and the best part? It's all clean!!");
        Club club4 = new Club("Simo's wonderful book club ", 20500l, "Keeping romance, history, humor, mystery, love, intrigue, and passion interesting, fun, and clean for all Christians. We discuss everything from reading to music to playing games to what makes a book good...and the best part? It's all clean!!");
        ObservableList<Club> observableList = FXCollections.observableArrayList(club1, club2, club3, club4);
        clubsList.setItems(observableList);
        clubsList.setStyle("-fx-focus-color: transparent;");
        clubsList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("bookList.css")).toExternalForm());
        clubsList.setFocusTraversable(false);
        clubsList.setCellFactory(listView -> new ClubViewCell());
    }
}
