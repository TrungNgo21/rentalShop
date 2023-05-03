package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControllers {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button forgotPassButton;

    @FXML
    private Label loginMessage;


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        UserServices service = new UserServices();
        SceneController sceneController = new SceneController();
        if(service.login(usernameTextField.getText(), passwordField.getText())){
            sceneController.switchScene(event, "../Pages/userProfile.fxml");
        }else{
            loginMessage.setText("Sai r dan vl!");
        }
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchScene(event, "../Pages/register.fxml");
    }

    public void onExitButton(ActionEvent event) throws IOException{
        DataAccess.transferAllData();
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}
