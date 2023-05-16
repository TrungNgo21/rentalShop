package com.example.officialjavafxproj.Controller.Component;

import Middleware.DateMiddleware;
import Model.Order.Order;
import Model.Order.OrderDetail;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//import java.awt.*;

public class AdminOrderDetailController {
    @FXML
    private Label orderDetailID;
    @FXML
    private Label orderDate;
    @FXML
    private Label orderCartID;
    @FXML
    private Label orderProductID;
    @FXML
    private Label orderQuantity;

    public void loadDisplayOrder(OrderDetail item, Order order) {
        orderDetailID.setText(item.getOrderDetailId());
        orderDate.setText(new DateMiddleware().dateAfterFormat(order.getOrderDate()));
        orderCartID.setText(item.getCartId());
        orderProductID.setText(item.getBoughtItem().getId());
        orderQuantity.setText(item.getQuantity()+"");
    }
}
