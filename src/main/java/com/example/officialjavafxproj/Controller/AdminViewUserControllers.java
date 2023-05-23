package com.example.officialjavafxproj.Controller;


import DataAccess.DataAccess;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;

import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminViewUserControllers implements Initializable {
    @FXML
    private AnchorPane navbarPane;
    @FXML
    private AnchorPane footerPane;

    @FXML
    private ChoiceBox<String> accountType;
    @FXML
    private TextField searchUser;
    @FXML
    private Button search;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button deleteSearch;
    @FXML
    private RadioButton increasingOrder;
    @FXML
    private RadioButton decreasingOrder;

    private HashMap<String, User> filteredUser;

    private String choice;
    private final String[] userType = {"VIP Account", "Regular Account", "Guest Account", "All"};

    public void addFooterPane() {
        try {
            footerPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/footer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetSearchDisable() {
        if(search.isPressed()) {
            searchUser.setDisable(false);
        }
        else {
            searchUser.setDisable(true);
        }
    }
    public void resetSearchButtonDisable() {
        increasingOrder.setOnMouseClicked(mouseEvent -> {
            search.setDisable(false);
        });
        decreasingOrder.setOnMouseClicked(mouseEvent -> {
            search.setDisable(false);
        });

    }
    public void resetDisable() {
        accountType.setOnAction((ActionEvent event) -> {
            increasingOrder.setDisable(false);
            decreasingOrder.setDisable(false);
        });
    }
    public void setDisable() {
        increasingOrder.setDisable(true);
        decreasingOrder.setDisable(true);
        search.setDisable(true);
        searchUser.setDisable(true);

    }
    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addAccountType() {
        accountType.getItems().addAll(userType);
    }

    public void addUserToGridView() {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        if (new AdminService().getSortedCustomer().size() == 0) {
            for (Map.Entry<String, User> user : new AdminService().getAll().entrySet()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    HBox userItem = fxmlLoader.load();
                    AdminUserControllers adminUserController = fxmlLoader.getController();
                    if(user.getValue().getUserId().equals("ADMIN")){
                        continue;
                    }
                    adminUserController.loadDisplayUser(user.getValue(), false);
                    gridPane.setHgap(20);
                    gridPane.setVgap(10);
                    if(column == 0) {
                        gridPane.add(userItem, column++, row);
                    } else {
                        gridPane.add(userItem, column--, row++);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            for (Map.Entry<String, User> user : new AdminService().getSortedCustomer().entrySet()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    HBox userItem = fxmlLoader.load();
                    AdminUserControllers adminUserController = fxmlLoader.getController();
                    if(user.getValue().getUserId().equals("ADMIN")){
                        continue;
                    }
                    adminUserController.loadDisplayUser(user.getValue(), true);
                    gridPane.setHgap(20);
                    gridPane.setVgap(10);
                    if(column == 0) {
                        gridPane.add(userItem, column++, row);
                    } else {
                        gridPane.add(userItem, column--, row++);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public void onSearchUserButton(ActionEvent event) {
        gridPane.getChildren().clear();
        DataAccess.getSortedUsers().clear();
        HashMap<String, User> tempUsers = new HashMap<String, User>();
        increasingOrder.setDisable(false);
        decreasingOrder.setDisable(false);
        increasingOrder.setSelected(false);
        decreasingOrder.setSelected(false);
        searchUser.setDisable(false);
        choice =  accountType.getValue();
        if(choice.equals("All")) {
            filterByType(choice);
        }
        else if(choice.equals("Guest Account")) {
            filterByType(choice);
        }
        else if(choice.equals("VIP Account")) {
            filterByType(choice);
        }
        else if(choice.equals("Regular Account")) {
            filterByType(choice);
        }

        if(searchUser.getText().trim().isEmpty()) {
            DataAccess.setSortedUsers(filteredUser);
            sortUsers();
            addUserToGridView();
        }
        else if(!searchUser.getText().trim().isEmpty()) {
            for(Map.Entry<String, User> tmp :filteredUser.entrySet()) {
                if(searchUser.getText().equals(tmp.getValue().getUserId()) || tmp.getValue().getFullName().contains(searchUser.getText().trim())) {
                    tempUsers.put(tmp.getValue().getUserId(), tmp.getValue());
                }
            }
            DataAccess.setSortedUsers(tempUsers);
            sortUsers();
            addUserToGridView();
        }
    }

    public void sortUsers() {
        if(increasingOrder.isSelected()) {
            gridPane.getChildren().clear();
            new AdminService().sortIncreasingOrderId();
            search.setDisable(true);
            addUserToGridView();
        }
        else if(decreasingOrder.isSelected()) {
            gridPane.getChildren().clear();
            new AdminService().sortDecreasingOrderId();
            search.setDisable(true);
            addUserToGridView();
        }
    }
    public void setToggle() {
        ToggleGroup toggleGroup = new ToggleGroup();
        increasingOrder.setToggleGroup(toggleGroup);
        decreasingOrder.setToggleGroup(toggleGroup);
    }
    public void filterByType(String type) {
        HashMap<String, User> temp = new HashMap<>();
        filteredUser = new HashMap<>();
        AdminService adminService = new AdminService();
        DataAccess.getSortedUsers().clear();
        gridPane.getChildren().clear();
        if(type == null) {
            temp = adminService.getAll();
            DataAccess.setSortedUsers(temp);
            filteredUser.putAll(temp);
            addUserToGridView();
        }
        if(type.equals("All")) {
            temp = adminService.getAll();
            DataAccess.setSortedUsers(temp);
            filteredUser.putAll(temp);
        }
        else if(type.equals("VIP Account")) {
            temp = adminService.filterAccountType("VIPAccount");
            DataAccess.setSortedUsers(temp);
            filteredUser.putAll(temp);
        }
        else if(type.equals("Guest Account")) {
            temp = adminService.filterAccountType("GuestAccount");
            DataAccess.setSortedUsers(temp);
            filteredUser.putAll(temp);
        }
        else if(type.equals("Regular Account")) {
            temp = adminService.filterAccountType("RegularAccount");
            DataAccess.setSortedUsers(temp);
            filteredUser.putAll(temp);
        }
    }
    public void onDeleteSearchButton(ActionEvent event) {
        gridPane.getChildren().clear();
        accountType.getSelectionModel().clearSelection();
        increasingOrder.setSelected(false);
        decreasingOrder.setSelected(false);
        increasingOrder.setDisable(true);
        decreasingOrder.setDisable(true);
        searchUser.clear();
        searchUser.setDisable(true);
        search.setDisable(true);
        DataAccess.getSortedUsers().clear();
        addUserToGridView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        setDisable();
        resetDisable();
        resetSearchButtonDisable();
        resetSearchDisable();
        addAccountType();
        addUserToGridView();
        addFooterPane();
        setToggle();
    }
}