package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.OrderDetail;
import Service.OrderCustomerService;
import com.example.officialjavafxproj.Controller.Component.CartComponentControllers;
import com.example.officialjavafxproj.Controller.Component.OrderItemControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

public class UserOrderIdControllers implements Initializable {

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private VBox orderItemsDisplay;

    @FXML
    private Label orderItemsQuantityDisplay;

    @FXML
    private Label orderIdDisplay;



    public void addNavigationBar() {
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllOrderItems(){
        OrderCustomerService orderCustomerService = new OrderCustomerService(new DataAccess(), new OrderMiddleware());
        orderIdDisplay.setText(orderCustomerService.getCurrentOrder().getOrderId());
        orderItemsQuantityDisplay.setText(String.valueOf(orderCustomerService.getCurrentOrder().getOrders().size()));
        for(OrderDetail detail : orderCustomerService.getCurrentOrder().getOrders()){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/orderItemComponent.fxml"));
                HBox cartItem = fxmlLoader.load();
                OrderItemControllers orderItemControllers = fxmlLoader.getController();
                orderItemControllers.loadAllOrderItemData(detail);
                orderItemsDisplay.getChildren().add(cartItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadAllOrderItems();
    }
}
