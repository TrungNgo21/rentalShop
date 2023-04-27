package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.Account.GuestAccount;
import Model.Account.RegularAccount;
import Model.Account.VIPAccount;
import Model.User.Customer;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneSwitcher;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileControllers implements Initializable {

    @FXML
    private ImageView profileImage;

    @FXML
    private AnchorPane navbarPane;
    @FXML
    private Label customerIdDisplay;

    @FXML
    private Label fullNameDisplay;

    @FXML
    private Label addressDisplay;

    @FXML
    private Label phoneNumDisplay;

    @FXML
    private Label balanceDisplay;

    @FXML
    private Label statusDisplay;

    @FXML
    private Label accountIdDisplay;

    @FXML
    private Label accountTypeDisplay;

    @FXML
    private Label accountPointsDisplay;

    @FXML
    private Label noReturnedItemsDisplay;

    @FXML
    private Label noFreeToBorrowDisplay;

    @FXML
    private Label noMaximumItemsDisplay;

    public void loadUserData(){
        UserServices userServices = new UserServices();
        FileLocation imageDir = new FileLocation();

        if(userServices.getCurrentUser() instanceof Customer){
            Customer currentCustomer = (Customer) userServices.getCurrentUser();
            String profileImgUrl = imageDir.getImageDir() + currentCustomer.getImageLocation();
            Image currentUserProfileImg = null;
            try {
                currentUserProfileImg = new Image(new FileInputStream(profileImgUrl), 400, 400, false, false);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            profileImage.setImage(currentUserProfileImg);
            customerIdDisplay.setText(currentCustomer.getUserId());
            fullNameDisplay.setText(currentCustomer.getFullName());
            addressDisplay.setText(currentCustomer.getAddress());
            phoneNumDisplay.setText(currentCustomer.getPhoneNum());
            balanceDisplay.setText(String.valueOf(currentCustomer.getBalance()));
            statusDisplay.setText(String.valueOf(currentCustomer.getAccount().getIsCurrentlyBorrowed()));
            accountIdDisplay.setText(currentCustomer.getAccount().getAccountId());
            noReturnedItemsDisplay.setText(String.valueOf(currentCustomer.getAccount().getNumReturnedItems()));
            noMaximumItemsDisplay.setText(String.valueOf(currentCustomer.getAccount().getRentalThreshold()));
            accountTypeDisplay.setText(currentCustomer.getAccount().getAccountType());
            if(currentCustomer.getAccount() instanceof GuestAccount ){
                GuestAccount currentUserAccount = (GuestAccount) currentCustomer.getAccount();
                accountPointsDisplay.setText("Cannot accumulate points yet");
                noFreeToBorrowDisplay.setText("Cannot borrow anything free now");
            }else if(currentCustomer.getAccount() instanceof RegularAccount){
                RegularAccount currentUserAccount = (RegularAccount) currentCustomer.getAccount();
                accountPointsDisplay.setText("Cannot accumulate points yet");
                noFreeToBorrowDisplay.setText("Cannot borrow anything free now");
            }else{
                VIPAccount currentUserAccount = (VIPAccount) currentCustomer.getAccount();
                accountPointsDisplay.setText(String.valueOf(currentUserAccount.getPoints()));
                noFreeToBorrowDisplay.setText(currentUserAccount.getPoints() >= 100 ? "1" : "Points must be over 100 to borrow free");
            }
        }
    }

    public void addNavigationBar(){
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        try {
            navbarPane.getChildren().add(sceneSwitcher.getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadUserData();
    }
}
