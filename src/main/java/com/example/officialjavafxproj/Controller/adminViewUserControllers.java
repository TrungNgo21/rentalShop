package com.example.officialjavafxproj.Controller;


import Model.User.Customer;
import Model.User.User;
import Service.AdminService;

import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
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
    private final String[] userType = {"VIP Account", "Regular Account", "Guest Account"};

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
        int row = 0;
        int column = 1;
        for(Map.Entry<String, User> user : new AdminService().getAll().entrySet()){
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                AnchorPane userCard = fxmlLoader1.load();
                AdminUserControllers userCardController = fxmlLoader1.getController();
                userCardController.loadDisplayUser(user.getValue());
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
    public void onSearchUserButton(ActionEvent event) {
        AdminService admin = new AdminService();
        User displayUser =  admin.getOne(searchUser.getText());

        if(searchUser.getText().isEmpty() && !admin.filterAccountType(accountType.getValue()).isEmpty()) {
              gridPane.getChildren().clear();
                int row = 0;
                int column = 1;
              for(Map.Entry<String, User> tempUser : admin.filterAccountType(accountType.getValue()).entrySet()) {
                  try {
                      FXMLLoader loader = new FXMLLoader();
                      loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                      AnchorPane userCard = loader.load();
                      AdminUserControllers userControllers = loader.getController();
                      userControllers.loadDisplayUser(tempUser.getValue());
                      if(column == 1){
                          column = 0;
                          row++;
                      }
                      gridPane.setHgap(10);
                      gridPane.setVgap(10);
                      gridPane.add(userCard, column, row++);
                  }
                  catch (Exception e) {
                      throw new RuntimeException(e);
                  }
              }
        }
        else if(!searchUser.getText().isEmpty() && admin.filterAccountType(accountType.getValue()).isEmpty()) {
            gridPane.getChildren().clear();
            int row = 0;
            int column = 1;
            try {
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
               AnchorPane userCard = loader.load();
               AdminUserControllers userControllers = loader.getController();
               userControllers.loadDisplayUser(displayUser);
                if(column == 1){
                    column = 0;
                    row++;
                }
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(userCard, column, row++);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(!searchUser.getText().isEmpty() && !admin.filterAccountType(accountType.getValue()).isEmpty()) {
            gridPane.getChildren().clear();
            int row = 0;
            int column = 1;
            if(!admin.filterAccountType(accountType.getValue()).containsValue(displayUser)) {
                gridPane.add(new javafx.scene.control.Label(),0,0);
            }
            else {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                    AnchorPane userCard = loader.load();
                    AdminUserControllers userControllers = loader.getController();
                    userControllers.loadDisplayUser(displayUser);
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(userCard, column, row++);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void onDeleteSearchButton() {
        gridPane.getChildren().clear();
        accountType.getSelectionModel().clearSelection();
        searchUser.clear();
        int row = 0;
        int column = 1;
        for(Map.Entry<String, User> user : new AdminService().getAll().entrySet()){
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("../Component/adminViewUserComponent.fxml"));
                AnchorPane userCard = fxmlLoader1.load();
                AdminUserControllers userCardController = fxmlLoader1.getController();
                userCardController.loadDisplayUser(user.getValue());
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
