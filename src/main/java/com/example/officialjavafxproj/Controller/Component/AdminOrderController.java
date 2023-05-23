package com.example.officialjavafxproj.Controller.Component;

import DataAccess.DataAccess;
import Middleware.DateMiddleware;
import Model.Order.Order;
import Model.User.Customer;
import Service.OrderAdminService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminOrderController {
    @FXML
    private Label orderIdDisplay;
    @FXML
    private Label orderUserDisplay;
    @FXML
    private Label orderDateDisplay;
    @FXML
    private Label orderTotalPriceDisplay;
    private String orderID;

    public void loadDisplayOrder(Order order) {
        orderIdDisplay.setText(order.getOrderId());
        orderUserDisplay.setText(order.getUserId());
        orderDateDisplay.setText(new DateMiddleware().dateAfterFormat(order.getOrderDate()));
        orderTotalPriceDisplay.setText(order.getTotalPrice() + "");
        orderID = order.getOrderId();
    }

    public void onViewOrderButton(ActionEvent actionEvent) throws IOException {
        OrderAdminService adminService = new OrderAdminService(new DataAccess());
        Order order = adminService.getOne(orderID);
        adminService.setSelectedOrder(order);
        new SceneController().switchScene(actionEvent, "../Pages/adminViewOrderDetail.fxml");
    }
}
