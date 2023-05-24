package com.example.officialjavafxproj.Controller;


import DataAccess.DataAccess;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;

import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.SearchController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    @FXML
    private RadioButton sortByName;

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
    public void setDisableSearch(){
        increasingOrder.setDisable(true);
        decreasingOrder.setDisable(true);
        sortByName.setDisable(true);
        searchUser.setDisable(true);
    }
    public void setDisableButton(){
        accountType.setOnAction((ActionEvent event) -> {
            increasingOrder.setDisable(false);
            decreasingOrder.setDisable(false);
            sortByName.setDisable(false);
        });
        System.out.println(accountType.getValue());
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
                    adminUserController.loadDisplayUser(user.getValue());
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
                    adminUserController.loadDisplayUser(user.getValue());
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
    public void addSearchUserToGridView() {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        if (SearchController.getTempUserContainer().isEmpty()) {
            Label temp = new Label();
            temp.setText("No Users matched your requirement");
            gridPane.getChildren().add(temp);
        }
        for (Map.Entry<String, User> user : SearchController.getTempUserContainer().entrySet()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                HBox userItem = fxmlLoader.load();
                AdminUserControllers adminUserController = fxmlLoader.getController();
                if (user.getValue().getUserId().equals("ADMIN")) {
                    continue;
                }
                adminUserController.loadDisplayUser(user.getValue());
                gridPane.setHgap(20);
                gridPane.setVgap(10);
                if (column == 0) {
                    gridPane.add(userItem, column++, row);
                } else {
                    gridPane.add(userItem, column--, row++);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void onSearchUserButton(ActionEvent event) {
        gridPane.getChildren().clear();
        choice =  accountType.getValue();
        ArrayList<RadioButton> sortOptions = new ArrayList<>(Arrays.asList(increasingOrder, decreasingOrder, sortByName));
        new AdminService().searchByChoice(choice,sortOptions);
        addUserToGridView();
        String searchField = searchUser.getText();
        if(!searchField.trim().isEmpty()){
            SearchController.searchByUserIdentify(searchField,new AdminService().getSortedCustomer());
            addSearchUserToGridView();
        }
    }

    public void setToggle() {
        ToggleGroup toggleGroup = new ToggleGroup();
        increasingOrder.setToggleGroup(toggleGroup);
        decreasingOrder.setToggleGroup(toggleGroup);
        sortByName.setToggleGroup(toggleGroup);
    }

    public void onDeleteSearchButton(ActionEvent event) {
        gridPane.getChildren().clear();
        accountType.getSelectionModel().clearSelection();
        increasingOrder.setSelected(false);
        decreasingOrder.setSelected(false);
        sortByName.setSelected(false);
        searchUser.clear();
        setDisableSearch();
        DataAccess.getSortedUsers().clear();
        addUserToGridView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDisableSearch();
        addNavigationBar();
        addAccountType();
        addUserToGridView();
        addFooterPane();
        setDisableButton();
        setToggle();
    }
}