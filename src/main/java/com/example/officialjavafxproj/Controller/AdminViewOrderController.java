package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Order.Order;
import Model.User.Customer;
import Model.User.User;
import Service.AdminService;
import Service.OrderAdminService;
import com.example.officialjavafxproj.Controller.Component.AdminOrderController;
import com.example.officialjavafxproj.Controller.Component.AdminUserControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminViewOrderController implements Initializable {
    @FXML
    private AnchorPane navbar;
    @FXML
    private TextField searchOrder;
    @FXML
    private GridPane gridPane;
    @FXML
    private RadioButton sortByOrderID;
    @FXML
    private RadioButton sortByOrderDate;
    @FXML
    private RadioButton sortByUserID;
    private OrderAdminService orderAdminService = new OrderAdminService(new DataAccess());

    private void addNavigationBar() {
        try {
            navbar.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAllOrder() {
        addOrder(new OrderAdminService(new DataAccess()).getAll());
    }

    private void addOrder(HashMap<String, Order> orderList) {
        int column = 0;
        int row = 0;
        gridPane.getChildren().clear();
        for(Map.Entry<String, Order> order: orderList.entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../Component/adminViewOrderComponent.fxml"));
                HBox userItem = loader.load();
                AdminOrderController adminOrderController = loader.getController();
                adminOrderController.loadDisplayOrder(order.getValue());
                DataAccess.getAllOrders().add(order.getValue());
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                if(column == 0) {
                    gridPane.add(userItem, column++, row);
                } else {
                    gridPane.add(userItem, column--, row++);
                }
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onSearchOrderButton() {
        int column = 0;
        int row = 0;
        gridPane.getChildren().clear();
        for(Map.Entry<String, Order> order: new OrderAdminService(new DataAccess()).getAll().entrySet()) {
            if(searchOrder.getText().equals(order.getKey())) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewOrderComponent.fxml"));
                    HBox userItem = loader.load();
                    AdminOrderController adminOrderController = loader.getController();
                    adminOrderController.loadDisplayOrder(order.getValue());
                    DataAccess.getAllOrders().add(order.getValue());
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(userItem,column,row++);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @FXML
    private void onOrderIDSortButton() {
        addOrder(orderAdminService.getSortedOrderID());
    }

    @FXML
    private void onOrderDateSortButton() {
        addOrder(orderAdminService.getSortedOrderDate());
    }

    @FXML
    private void onUserIDSortButton() {
        addOrder(orderAdminService.getSortedUserID());
    }

    private void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        sortByOrderDate.setToggleGroup(toggleGroup);
        sortByOrderID.setToggleGroup(toggleGroup);
        sortByUserID.setToggleGroup(toggleGroup);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setToggleGroup();
        addNavigationBar();
        addAllOrder();
    }
}
