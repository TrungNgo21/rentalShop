package com.example.officialjavafxproj.Controller.Component;

//import Service.OrderDetailService;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminNavbarControllers {
    @FXML
    private Label userNameDisplay;

    @FXML
//    private Label noCartItem;
    public void onLogoutButton(ActionEvent event) throws IOException {
        new UserServices().setCurrentUser(null);
        new SceneController().switchScene(event, "../Pages/login.fxml");
    }

    public void onViewProductButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/adminViewProduct.fxml");
    }
    public void onViewUserButton(ActionEvent actionEvent) throws IOException{
        new SceneController().switchScene(actionEvent, "../Pages/adminViewCustomers.fxml");
    }
}
