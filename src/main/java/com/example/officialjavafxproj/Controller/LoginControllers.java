package com.example.officialjavafxproj.Controller;

import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneSwitcher;
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


    public void loginButtonOnAction(ActionEvent event) {
        UserServices service = new UserServices();
        if(service.login(usernameTextField.getText(), passwordField.getText())){
            loginMessage.setText("Login Successfully!");
        }else{
            loginMessage.setText("Sai r dan vl!");
        }
    }

    public void registerButtonOnAction(ActionEvent event) throws IOException {
        SceneSwitcher sceneController = new SceneSwitcher();
        sceneController.switchScene(event, "../Pages/register.fxml");
    }

    public void onExitButton(ActionEvent event) throws IOException{
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }
}
