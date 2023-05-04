package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Order.OrderDetail;
import Service.OrderDetailCartService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void onDownButton(ActionEvent event) throws IOException{
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        OrderDetail currentItem = orderDetailCartService.getOne(cartItemId);
        currentItem.setQuantity(currentItem.getQuantity() - 1);
        quantityTextField.setText(String.valueOf(currentItem.getQuantity()));
        productCartPriceDisplay.setText(String.valueOf(currentItem.getBoughtItem().getRentalFee() * Integer.parseInt(quantityTextField.getText())));
        new SceneController().switchScene(event, "../Pages/userCart.fxml");
    }
    public void onUpButton(ActionEvent event) throws IOException{
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        OrderDetail currentItem = orderDetailCartService.getOne(cartItemId);
        currentItem.setQuantity(currentItem.getQuantity() + 1);
        quantityTextField.setText(String.valueOf(currentItem.getQuantity()));
        productCartPriceDisplay.setText(String.valueOf(currentItem.getBoughtItem().getRentalFee() * Integer.parseInt(quantityTextField.getText())));
        new SceneController().switchScene(event, "../Pages/userCart.fxml");

    }
    public void onDeleteButton(ActionEvent event) throws IOException{
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        OrderDetail currentItem = orderDetailCartService.getOne(cartItemId);
        orderDetailCartService.delete(currentItem);
        new SceneController().switchScene(event, "../Pages/userCart.fxml");
    }

}
