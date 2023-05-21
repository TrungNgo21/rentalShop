package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.IOException;

public class AdminProductController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label productTitleDisplay;
    @FXML
    private Label productStockDisplay;
    @FXML
    private Label productPrice;
    @FXML
    private Label productStatus;
    @FXML
    private Label productType;
    @FXML
    private Label rentalType;
    @FXML
    private Label genre;

    private String productId;

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
        productPrice.setText(String.valueOf(product.getRentalFee()));
        productType.setText(product.getLoanType());
        productStatus.setText(product.getStatus());
        rentalType.setText(product.getRentalType());
        genre.setText(product.getGenre());
        productId = product.getId();

    }
    public void onClick(MouseEvent mouseEvent) throws IOException {
        ProductService productService = new ProductService();
        Product currentProduct = productService.getOne(productId);
        productService.setTargetProduct(currentProduct);
        new SceneController().switchScene(mouseEvent,"../Pages/adminEditProduct.fxml");
    }
    public void viewProductDetail(MouseEvent mouseEvent) throws IOException {
        ProductService productService = new ProductService();
        Product currentProduct = productService.getOne(productId);
        productService.setTargetProduct(currentProduct);
        new SceneController().switchScene(mouseEvent,"../Pages/adminProductDetail.fxml");
    }



}
