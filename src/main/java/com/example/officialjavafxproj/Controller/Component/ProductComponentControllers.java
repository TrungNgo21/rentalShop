package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductComponentControllers {

    @FXML
    private ImageView productImageView;
    @FXML
    private Label productTitleDisplay;
    @FXML
    private Label productPriceDisplay;

    private String productId;

    public void loadProductItemData(Product product){
        String imageDir = new FileLocation().getImageDir() + product.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 220, false, false);
            productImageView.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        productTitleDisplay.setText(product.getTitle());
        productPriceDisplay.setText(String.valueOf(product.getRentalFee()));
        productId = product.getId();

    }

    public void onProductClicked(MouseEvent mouseEvent) throws IOException {
        ProductService productService = new ProductService();
        Product currentProduct = productService.getOne(productId);
        productService.setTargetProduct(currentProduct);
        new SceneController().switchScene(mouseEvent, "../Pages/productDetails.fxml");
    }
}
