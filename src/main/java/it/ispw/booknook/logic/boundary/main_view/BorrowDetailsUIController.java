package it.ispw.booknook.logic.boundary.main_view;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import it.ispw.booknook.logic.bean.BookBean;
import it.ispw.booknook.logic.control.BorrowBookController;
import it.ispw.booknook.logic.entity.Library;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;


public class BorrowDetailsUIController extends UIController {

    @FXML
    private ListView<Library> libraryList;

    @FXML
    private AnchorPane mapPane;

    private MapView mapView;

    private GraphicsOverlay graphicsOverlay;

    @FXML
    private Label introLabel;


    public void displayLibraryList(String ISBN, String title) {
        introLabel.setText("'" + title + "' is available in the following libraries.");


        BorrowBookController controller = new BorrowBookController();
        BookBean book = new BookBean();
        book.setIsbn(ISBN);
        controller.calculateLibraries(book);

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        mapView.setId("map");
        mapPane.getChildren().add(mapView);
        mapPane.lookup("#map").prefWidth(330);
        mapPane.lookup("#map").prefHeight(220);
        AnchorPane.setTopAnchor(mapView, 166.0);
        AnchorPane.setLeftAnchor(mapView, 106.0);
        AnchorPane.setRightAnchor(mapView, 91.0);
        AnchorPane.setBottomAnchor(mapView, 15.0);

        // create an ArcGISMap with an imagery basemap
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_STREETS);

        // display the map by setting the map on the map view
        mapView.setMap(map);

        mapView.setViewpoint(new Viewpoint(41.832553079101, 12.598051228413429, 5000));

        // create a graphics overlay
        graphicsOverlay = new GraphicsOverlay();

        // create a map point for the Santa Monica pier
        Point point = new Point(12.598051228413429, 41.832553079101, SpatialReferences.getWgs84());

        // create a red (0xFFFF0000) circle simple marker symbol
        SimpleMarkerSymbol redCircleSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 20);

        // create a graphic from the point and symbol
        Graphic pointGraphic = new Graphic(point, redCircleSymbol);

        graphicsOverlay.getGraphics().add(pointGraphic);
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        setListView();
    }

    private int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.
        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }


    public void setListView()

    {
        Library library1 = new Library("London Library");
        Library library2 = new Library("London Library");
        Library library3 = new Library("London Library");
        Library library4 = new Library("London Library");
        Library library5 = new Library("London Library");
        Library library6 = new Library("London Library");
        Library library7 = new Library("London Library");
        ObservableList<Library> observableList = FXCollections.observableArrayList(library1, library2, library3, library4, library5, library6, library7);
        libraryList.setItems(observableList);
        libraryList.setStyle("-fx-focus-color: transparent;");
        libraryList.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/it/ispw/booknook/mainView/libraryList.css")).toExternalForm());
        libraryList.setFocusTraversable(false);
        libraryList.setCellFactory(listView -> new LibraryViewCell());
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
    void onProfileClick(MouseEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/settings-view.fxml", event);
    }


    @FXML
    void onMyListClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/myLists-view.fxml", event);
    }

    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        changePage("/it/ispw/booknook/mainView/homepage-view.fxml", event);
    }
}
