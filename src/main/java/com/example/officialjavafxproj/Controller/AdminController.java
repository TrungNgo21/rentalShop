package com.example.officialjavafxproj.Controller;

import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminController {
    @FXML
    private AnchorPane adminNavbarPane;

    public void addNavigationBar(){
        try {
            adminNavbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
