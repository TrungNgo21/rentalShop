package com.example.officialjavafxproj.Controller;


import DataAccess.DataAccess;
import Model.Product.Product;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;

import com.example.officialjavafxproj.Controller.Component.AdminProductController;
import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class adminViewUserControllers implements Initializable {
    @FXML
    private AnchorPane navbarPane;

    @FXML
    private ChoiceBox<String> accountType;
    @FXML
    private TextField searchUser;
//    @FXML
//    private Button search;
     @FXML
     private GridPane gridPane;
     @FXML
     private Button deleteSearch;
     @FXML
     private RadioButton increasingOrder;
     @FXML
     private RadioButton decreasingOrder;
     @FXML
     private ToggleGroup alphabet;

     private String choice;
    private final String[] userType = {"VIP Account", "Regular Account", "Guest Account", "All"};

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

    public void addUserToGridView() {
        accountType.setOnAction((ActionEvent) -> {
            accountType.getValue();
            HashMap<String, User> temp = new HashMap<>();
            Label emptylabel = new Label();
            int column = 1;
            int row = 0;
            AdminService adminService = new AdminService();
            DataAccess.getSortedUsers().clear();
            choice = accountType.getValue();
            gridPane.getChildren().clear();
            if(choice == null) {
                emptylabel.setText("You have chosen no filter!!");
                gridPane.add(emptylabel, 0, 0);
            }
            else if(choice.equals("All")) {
                temp = DataAccess.getAllUsers();
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("VIP Account")) {
                temp = adminService.filterAccountType("VIPAccount");
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("Guest Account")) {
                temp = adminService.filterAccountType("GuestAccount");
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("Regular Account")) {
                temp = adminService.filterAccountType("RegularAccount");
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }

            for(Map.Entry<String, User> user : temp.entrySet()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    HBox userItem = loader.load();
                    AdminUserControllers adminProductController = loader.getController();

                    adminProductController.loadDisplayUser((Customer) user.getValue());
                    DataAccess.getSortedUsers().put(user.getKey(), user.getValue());
                    adminService.setSelectedUser(user.getValue());
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(userItem,column,row++);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void onSearchUserButton(ActionEvent event) {
        int column = 0;
        int row = 1;
        AdminService adminService = new AdminService();
        HashMap<String, User> temp = new HashMap<>();
        alphabet.getToggles().addAll(increasingOrder, decreasingOrder);
        if(!increasingOrder.isSelected() && !decreasingOrder.isSelected()) {
            temp = DataAccess.getSortedUsers();
            for(Map.Entry<String, User> user : temp.entrySet()) {
                if(searchUser.getText().equals(user.getValue().getUserId())|| searchUser.getText().equals(user.getValue().getUserName())) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                        HBox userItem = loader.load();
                        AdminUserControllers adminProductController = loader.getController();

                        adminProductController.loadDisplayUser((Customer) user.getValue());
                        DataAccess.getSortedUsers().put(user.getKey(), user.getValue());
                        ToastBuilder.builder()
                                .withTitle("Annoucing Message")
                                .withMessage("You are searching for " + user.getValue().getUserName())
                                .withMode(Notifications.SUCCESS)
                                .show();
                        gridPane.getChildren().clear();
                        if(column == 1){
                            column = 0;
                            row++;
                        }
                        gridPane.setHgap(10);
                        gridPane.setVgap(10);
                        gridPane.add(userItem,column,row++);
                    }
                    catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else if(increasingOrder.isSelected()) {
            temp = adminService.sortFromAToZ(choice);
            for(Map.Entry<String, User> user : temp.entrySet()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    HBox userItem = loader.load();
                    AdminUserControllers adminProductController = loader.getController();

                    adminProductController.loadDisplayUser((Customer) user.getValue());
                    DataAccess.getSortedUsers().put(user.getKey(), user.getValue());
                    ToastBuilder.builder()
                            .withTitle("Annoucing Message")
                            .withMessage("You are searching for " + user.getValue().getUserName())
                            .withMode(Notifications.SUCCESS)
                            .show();
                    gridPane.getChildren().clear();
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(userItem,column,row++);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        else if(decreasingOrder.isSelected()) {
            temp = adminService.sortFromZToA(choice);
            for(Map.Entry<String, User> user : temp.entrySet()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    HBox userItem = loader.load();
                    AdminUserControllers adminProductController = loader.getController();

                    adminProductController.loadDisplayUser((Customer) user.getValue());
                    DataAccess.getSortedUsers().put(user.getKey(), user.getValue());
                    ToastBuilder.builder()
                            .withTitle("Annoucing Message")
                            .withMessage("You are searching for " + user.getValue().getUserName())
                            .withMode(Notifications.SUCCESS)
                            .show();
                    gridPane.getChildren().clear();
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(userItem,column,row++);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void sortByAlphabetical() {

    }
    public void onDeleteSearchButton() {
        gridPane.getChildren().clear();
        accountType.getSelectionModel().clearSelection();
        alphabet.selectToggle(null);
        searchUser.clear();
        int row = 0;
        int column = 1;
        for(Map.Entry<String, User> user : new AdminService().getAll().entrySet()){
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                AnchorPane userCard = fxmlLoader1.load();
                AdminUserControllers userCardController = fxmlLoader1.getController();
                userCardController.loadDisplayUser((Customer) user.getValue());
                if(column == 1){
                    column = 0;
                    row++;
                }
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(userCard, column, row++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addAccountType();
        addUserToGridView();
    }
}
