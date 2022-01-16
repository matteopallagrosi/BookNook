package it.ispw.booknook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileSettingsUIController {

    @FXML
    private ImageView profilePicture;

    @FXML
    private ImageView avatar1;

    @FXML
    private ImageView avatar2;

    @FXML
    private ImageView avatar3;

    @FXML
    private ImageView avatar4;

    @FXML
    void onChangePictureClick(ActionEvent event) {
        avatar1.setVisible(true);
        avatar2.setVisible(true);
        avatar3.setVisible(true);
        avatar4.setVisible(true);
    }

    @FXML
    void onFirstAvatarClick(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\BookNook\\src\\main\\resources\\it\\ispw\\booknook\\avatar_1.png");
        Image image = new Image(stream);
        profilePicture.setImage(image);
    }

    @FXML
    void onSecondAvatarClick(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\BookNook\\src\\main\\resources\\it\\ispw\\booknook\\avatar_2.png");
        Image image = new Image(stream);
        profilePicture.setImage(image);
    }

    @FXML
    void onThirdAvatarClick(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\BookNook\\src\\main\\resources\\it\\ispw\\booknook\\avatar_3.png");
        Image image = new Image(stream);
        profilePicture.setImage(image);
    }

    @FXML
    void onFourthAvatarClick(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\BookNook\\src\\main\\resources\\it\\ispw\\booknook\\avatar_4.png");
        Image image = new Image(stream);
        profilePicture.setImage(image);
    }

}
