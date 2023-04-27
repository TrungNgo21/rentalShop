package com.example.officialjavafxproj.Controller.Component;

import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class NavbarControllers {
    public void onLogoutButton(ActionEvent event) throws IOException {
        new UserServices().setCurrentUser(null);
        new SceneSwitcher().switchScene(event, "../Pages/login.fxml");
    }

}
