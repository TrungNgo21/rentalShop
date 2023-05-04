package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Order.OrderDetail;
import Model.Product.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderItemControllers {

    @FXML
    private Label productOrderTitleDisplay;

    @FXML
    private Label productOrderYearDisplay;

    @FXML
    private Label productOrderPriceDisplay;

    @FXML
    private Label productOrderGenreDisplay;

    @FXML
    private Label productOrderRentalDisplay;

    @FXML
    private Label productOrderQuantityDisplay;

    @FXML
    private ImageView productOrderImage;

    public void loadAllOrderItemData(OrderDetail detail){
        String imageDir = new FileLocation().getImageDir() + detail.getBoughtItem().getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 175, false, false);
            productOrderImage.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        productOrderTitleDisplay.setText(detail.getBoughtItem().getTitle());
        productOrderYearDisplay.setText(detail.getBoughtItem().getPublishedYear());
        productOrderRentalDisplay.setText(detail.getBoughtItem().getRentalType());
        productOrderGenreDisplay.setText(detail.getBoughtItem().getGenre());
        productOrderQuantityDisplay.setText(String.valueOf(detail.getQuantity()));
        productOrderPriceDisplay.setText(String.valueOf(detail.getBoughtItem().getRentalFee() * detail.getQuantity()));
    }

    public void onReturnButton(ActionEvent event){

    }
}
