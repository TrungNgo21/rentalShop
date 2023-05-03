package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Order.Cart;
import Model.Order.OrderDetail;
import Service.OrderDetailService;
import com.example.officialjavafxproj.Controller.UserCartControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartComponentControllers {

    private String cartItemId;
    @FXML
    private ImageView productCartImage;

    @FXML
    private Label productCartTitleDisplay;

    @FXML
    private Label productCartPriceDisplay;

    @FXML
    private TextField quantityTextField;


    public void loadCartItemData(OrderDetail details){
        String imageDir = new FileLocation().getImageDir() + details.getBoughtItem().getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 175, false, false);
            productCartImage.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        cartItemId = details.getOrderDetailId();
        productCartTitleDisplay.setText(details.getBoughtItem().getTitle());
        quantityTextField.setText(String.valueOf(details.getQuantity()));
        productCartPriceDisplay.setText(String.valueOf(details.getBoughtItem().getRentalFee() * Integer.parseInt(quantityTextField.getText())));
    }

    public void onDownButton(ActionEvent event){
        OrderDetailService orderDetailService = new OrderDetailService();
        OrderDetail currentItem = orderDetailService.getOne(cartItemId);
        currentItem.setQuantity(currentItem.getQuantity() - 1);
        quantityTextField.setText(String.valueOf(currentItem.getQuantity()));
        productCartPriceDisplay.setText(String.valueOf(currentItem.getBoughtItem().getRentalFee() * Integer.parseInt(quantityTextField.getText())));
    }
    public void onUpButton(ActionEvent event){
        OrderDetailService orderDetailService = new OrderDetailService();
        OrderDetail currentItem = orderDetailService.getOne(cartItemId);
        currentItem.setQuantity(currentItem.getQuantity() + 1);
        quantityTextField.setText(String.valueOf(currentItem.getQuantity()));
        productCartPriceDisplay.setText(String.valueOf(currentItem.getBoughtItem().getRentalFee() * Integer.parseInt(quantityTextField.getText())));
    }
    public void onDeleteButton(ActionEvent event) throws IOException{
        OrderDetailService orderDetailService = new OrderDetailService();
        OrderDetail currentItem = orderDetailService.getOne(cartItemId);
        orderDetailService.delete(currentItem);
        new SceneController().switchScene(event, "../Pages/userCart.fxml");
    }

}
