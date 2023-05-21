package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Middleware.DateMiddleware;
import Model.Order.Order;
import Model.Order.OrderDetail;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

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
    @FXML
    private ImageView image;
    public void loadDisplayOrder(OrderDetail item, Order order) {
        orderDetailID.setText(item.getOrderDetailId());
        orderDate.setText(new DateMiddleware().dateAfterFormat(order.getOrderDate()));
        orderCartID.setText(item.getCartId());
        orderProductID.setText(item.getBoughtItem().getId());
        orderQuantity.setText(item.getQuantity()+"");

        String imageDir = new FileLocation().getImageDir() + item.getBoughtItem().getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 100, 74, false, false);
            image.setImage(productImage);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
