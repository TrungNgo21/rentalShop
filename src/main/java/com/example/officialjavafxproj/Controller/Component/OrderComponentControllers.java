package com.example.officialjavafxproj.Controller.Component;

import DataAccess.DataAccess;
import FileLocation.FileLocation;
import Middleware.DateMiddleware;
import Middleware.OrderMiddleware;
import Model.Order.Order;
import Service.OrderCustomerService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class OrderComponentControllers {
    @FXML
    private Label orderStatus;

    @FXML
    private Label orderDate;

    @FXML
    private Label orderId;

    @FXML
    private Label orderTotalPrice;

    @FXML
    private ImageView orderImage;

    public void loadOrderData(Order order){
        String imageDir = new FileLocation().getImageDir() + "Public/1.png";
        try {
            Image image = new Image(new FileInputStream(imageDir), 200, 156, false, false);
            orderImage.setImage(image);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(order.getOrderDate().compareTo(LocalDate.now()) < 0){
            orderStatus.setText("Warning");
            orderStatus.setStyle("-fx-border-color: #E57C23; -fx-text-fill: #E57C23; -fx-border-radius: 20; -fx-border-width: 2");

        }else{
            orderStatus.setText("Good");
            orderStatus.setStyle("-fx-border-color: #54B435; -fx-text-fill: #54B435; -fx-border-radius: 20; -fx-border-width: 2");

        }

        orderDate.setText(new DateMiddleware().dateAfterFormat(order.getOrderDate()));
        orderId.setText(order.getOrderId());
        orderTotalPrice.setText(String.valueOf(order.getTotalPrice()));
    }

    public void onOrderClicked(MouseEvent mouseEvent) throws IOException {
        OrderCustomerService orderCustomerService = new OrderCustomerService(new DataAccess(), new OrderMiddleware());
        Order currentOrder = orderCustomerService.getOne(orderId.getText());
        orderCustomerService.setCurrentOrder(currentOrder);
        new SceneController().switchScene(mouseEvent, "../Pages/userOrderId.fxml");
    }


}
