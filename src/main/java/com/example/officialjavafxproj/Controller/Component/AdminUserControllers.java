package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.User.User;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public class AdminUserControllers {
    @FXML
    private ImageView imageView;
    @FXML
    private Label userNameDisplay;
    @FXML
    private Label userIdDisplay;
    @FXML
    private Button viewButton;

    public void loadDisplayUser(User user) {
        String imageDir = new FileLocation().getImageDir() + user.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 175, false, false);
            imageView.setImage(productImage);

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        userNameDisplay.setText(user.getUserName());
        userIdDisplay.setText(user.getUserId());
    }

    public void onViewUserProfileButton(ActionEvent event) throws IOException {
        new SceneController().switchScene(event, "../Pages/userProfile.fxml");
    }
}
