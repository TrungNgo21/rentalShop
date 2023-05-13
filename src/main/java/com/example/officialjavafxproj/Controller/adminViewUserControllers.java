package com.example.officialjavafxproj.Controller;


import DataAccess.DataAccess;
import Model.Product.Product;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;

import Service.ProductService;
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
//        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        if (new AdminService().getSortedCustomer().size() == 0) {
            Label temp = new Label();
            temp.setText("No Users matched your requirement");
            gridPane.getChildren().add(temp);
        }
        for (Map.Entry<String, User> user : new AdminService().getSortedCustomer().entrySet()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                HBox userItem = fxmlLoader.load();
                AdminUserControllers adminUserController = fxmlLoader.getController();
                adminUserController.loadDisplayUser((Customer) user.getValue());
                DataAccess.getSortedUsers().put(user.getKey(), user.getValue());
                if(column == 1){
                    column = 0;
                    row++;
                }
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(userItem, column, row++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void onSearchUserButton(ActionEvent event) {
        gridPane.getChildren().clear();
        HashMap<String, User> tempUsers = new HashMap<String, User>();
        for(Map.Entry<String, User> user : new AdminService().getSortedCustomer().entrySet()) {
            if(searchUser.getText().equals(user.getValue().getUserId())|| searchUser.getText().equals(user.getValue().getUserName())) {
                tempUsers.put(user.getKey(), user.getValue());
            }
        }
        DataAccess.setSortedUsers(tempUsers);
        addUserToGridView();
    }

    public void sortUsers(ActionEvent event) {
        if(increasingOrder.isSelected()) {
            gridPane.getChildren().clear();
            new AdminService().sortIncreasingOrderId();
            addUserToGridView();
        }
        else if(decreasingOrder.isSelected()) {
            gridPane.getChildren().clear();
            new AdminService().sortDecreasingOrderId();
            addUserToGridView();
        }
    }
    public void setToggle() {
        ToggleGroup toggleGroup = new ToggleGroup();
        increasingOrder.setToggleGroup(toggleGroup);
        decreasingOrder.setToggleGroup(toggleGroup);
    }
    public void filterByType() {
            accountType.setOnAction((ActionEvent) -> {
            HashMap<String, User> temp = new HashMap<>();
            Label emptylabel = new Label();
            AdminService adminService = new AdminService();
            DataAccess.getSortedUsers().clear();
            choice = accountType.getValue();
            gridPane.getChildren().clear();
            if(choice.equals(null)) {
                emptylabel.setText("You have chosen no filter!!");
                gridPane.add(emptylabel, 0, 0);
            }
             if(choice.equals("All")) {
                temp = DataAccess.getAllUsers();
                DataAccess.setSortedUsers(temp);
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("VIP Account")) {
                temp = adminService.filterAccountType("VIPAccount");
                DataAccess.setSortedUsers(temp);
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("Guest Account")) {
                temp = adminService.filterAccountType("GuestAccount");
                DataAccess.setSortedUsers(temp);
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
            else if(choice.equals("Regular Account")) {
                temp = adminService.filterAccountType("RegularAccount");
                DataAccess.setSortedUsers(temp);
                ToastBuilder.builder()
                        .withTitle("Load Message")
                        .withMessage("You are viewing All users")
                        .withMode(Notifications.NOTICE)
                        .show();
            }
                if (new AdminService().getSortedCustomer().size() == 0) {
                    Label tmp = new Label();
                    tmp.setText("No Users matched your requirement");
                    gridPane.getChildren().add(tmp);
                }
                addUserToGridView();
        });
    }
    public void onDeleteSearchButton(ActionEvent event) {
        gridPane.getChildren().clear();
        accountType.getSelectionModel().clearSelection();
        increasingOrder.setSelected(false);
        decreasingOrder.setSelected(false);
        searchUser.clear();
        DataAccess.getSortedUsers().clear();
        addUserToGridView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addAccountType();
        filterByType();
        addUserToGridView();
        setToggle();
    }
}
