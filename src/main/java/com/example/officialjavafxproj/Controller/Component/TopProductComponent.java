package com.example.officialjavafxproj.Controller.Component;

import FileLocation.FileLocation;
import Model.Product.Product;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TopProductComponent {

    @FXML
    private AnchorPane topProductPane;
    @FXML
    private Label productTitleDisplay;
    @FXML
    private Label productPriceDisplay;
    @FXML
    private ImageView productViewDisplay;

    public void loadTopProductData(Product product){
        String imageDir = new FileLocation().getImageDir() + product.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 140, 140, false, false);
            productViewDisplay.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        productTitleDisplay.setText(product.getTitle());
        productPriceDisplay.setText(String.valueOf(product.getRentalFee()));

    }

    public void onProductClicked(MouseEvent mouseEvent) throws IOException {
        new SceneController().switchScene(mouseEvent, "../Pages/userProfile.fxml");
    }

}
