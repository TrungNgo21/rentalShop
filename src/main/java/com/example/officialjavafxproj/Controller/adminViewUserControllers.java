package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminViewUserControllers implements Initializable {
    @FXML
    private AnchorPane navbarPane;

    @FXML
    private ChoiceBox<String> accountType;
    @FXML
    private RadioButton userWithStock;

    @FXML
    private TextField searchUser;
    @FXML
    private Button search;
    @FXML
    private ImageView userImage;
    @FXML
    private Label errorLabel;

    private String[] userType = {"VIP Account", "Regular Account", "Guest Account"};

    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addAccountType() {
        accountType.getItems().addAll(userType);
        accountType.setOnAction(this::onSearchUserButton);
    }

    public void onSearchUserButton(ActionEvent event) {
        AdminService admin = new AdminService();
        Customer displayUser = (Customer) admin.getOne(searchUser.getText());

        if(displayUser == null && admin.filterAccountType(accountType.getValue()).isEmpty()) {
            errorLabel.setText("There are no users with your ID!!");
        }

        else if(displayUser != null && admin.filterAccountType(accountType.getValue()).containsValue(displayUser.getAccount())) {
            FileLocation imageDir = new FileLocation();
            String displayedCustomerImageURL = imageDir.getImageDir() + displayUser.getImageLocation();
            Image displayUserImage = null;
            try {
                displayUserImage = new Image(new FileInputStream(displayedCustomerImageURL), 200, 200, false, false);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            userImage.setImage(displayUserImage);
        }
        else if(displayUser == null && !admin.filterAccountType(accountType.getValue()).isEmpty()) {
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addAccountType();
    }
}
