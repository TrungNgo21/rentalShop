package com.example.officialjavafxproj.Controller.Component;

import Service.OrderDetailCartService;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarControllers implements Initializable {

    @FXML
    private Label userNameDisplay;

    @FXML
    private Label noCartItem;
    public void onLogoutButton(ActionEvent event) throws IOException {
        new UserServices().setCurrentUser(null);
        new SceneController().switchScene(event, "../Pages/login.fxml");
    }

    public void onAccountButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/userProfile.fxml");
    }

    public void onHomeButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/homepage.fxml");
    }

    public void onGoToCartButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/userCart.fxml");
    }

    public void onGoToOrders(ActionEvent event) throws IOException {
        new SceneController().switchScene(event, "../Pages/userOrders.fxml");
    }

    public void loadUserName(){
        userNameDisplay.setText(new UserServices().getCurrentUser().getUserName());
    }

    public void loadNoCartItem(){
        noCartItem.setText(String.valueOf(new OrderDetailCartService().getAll().size()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUserName();
//        loadNoCartItem();
    }

}
