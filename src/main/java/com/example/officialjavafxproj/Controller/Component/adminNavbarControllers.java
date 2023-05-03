package com.example.officialjavafxproj.Controller.Component;

import Service.OrderDetailService;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class adminNavbarControllers {
    @FXML
    private Label userNameDisplay;

    @FXML
//    private Label noCartItem;
    public void onLogoutButton(ActionEvent event) throws IOException {
        new UserServices().setCurrentUser(null);
        new SceneController().switchScene(event, "../Pages/login.fxml");
    }

    public void onAccountButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/userProfile.fxml"); // Lam profile admin
    }

    public void onHomeButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/homepage.fxml");
    }

    public void onGoToOrderButton(ActionEvent event) throws IOException{
//        new SceneController().switchScene(event, "../Pages/userCart.fxml"); lam view all order pages
    }

    public void loadUserName(){
        userNameDisplay.setText(new UserServices().getCurrentUser().getUserName());
    }

//    public void loadNoCartItem(){
//        noCartItem.setText(String.valueOf(new OrderDetailService().getAll().size()));
//    }
}
