package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Product.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class AdminProductController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label productTitleDisplay;
    @FXML
    private Label productStockDisplay;
    @FXML
    private Button editButton;

    public void loadProductDisplay(Product product){
        String imageDir = new FileLocation().getImageDir() + product.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 175, false, false);
            imageView.setImage(productImage);

        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        productTitleDisplay.setText(product.getTitle());
        productStockDisplay.setText(String.valueOf(product.getNumOfCopies()));
    }


}
