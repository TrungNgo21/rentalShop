package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Order.Order;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;
import com.example.officialjavafxproj.Controller.Component.AdminOrderController;
import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminViewOrderController implements Initializable {
    @FXML
    private AnchorPane navbar;
    @FXML
    private TextField searchOrder;
    @FXML
    private GridPane gridPane;

    private void addNavigationBar() {
        try {
            navbar.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "com/example/officialjavafxproj/Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAllOrder() {
        int column = 0;
        int row = 1;
        for(Order order: DataAccess.getAllOrders()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../Component/adminViewOrderComponent.fxml"));
                HBox userItem = loader.load();
                AdminOrderController adminOrderController = loader.getController();
                adminOrderController.loadDisplayOrder(order);
                DataAccess.getAllOrders().add(order);
                gridPane.getChildren().clear();
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(userItem,column,row++);
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onSearchOrderButton() {
        int column = 0;
        int row = 1;
        for(Order order : DataAccess.getAllOrders()) {
            if(searchOrder.getText().equals(order.getOrderId())) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewOrderComponent.fxml"));
                    HBox userItem = loader.load();
                    AdminOrderController adminOrderController = loader.getController();
                    adminOrderController.loadDisplayOrder(order);
                    DataAccess.getAllOrders().add(order);
                    gridPane.getChildren().clear();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addAllOrder();
    }
}
