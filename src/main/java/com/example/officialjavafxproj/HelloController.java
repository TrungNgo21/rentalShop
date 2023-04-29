package com.example.officialjavafxproj;

import DataAccess.DataAccess;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label welcomeText;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button exitButton;

    @FXML
    public void onLoginButton(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchScene(event, "../Pages/login.fxml");
    }

    public void onRegisterButton(ActionEvent event) throws IOException{
        SceneController sceneSwitcher = new SceneController();
        sceneSwitcher.switchScene(event, "../Pages/register.fxml");
    }

    public void onExitButton(){
        DataAccess.transferAllData();
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}