package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.ProductComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductDetailsControllers implements Initializable {
    @FXML
    private AnchorPane navbarPane;

    @FXML
    private ImageView productDetailImage;

    @FXML
    private Label productDetailTitleDisplay;

    @FXML
    private Label productDetailYearDisplay;

    @FXML
    private Label productDetailStatusDisplay;

    @FXML
    private Label productDetailTypeDisplay;

    @FXML
    private Label productDetailGenreDisplay;

    @FXML
    private Label productDetailLoanTypeDisplay;

    @FXML
    private TextField productDetailQuantityTextField;

    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductDetailData(){
        Product currentProduct = new ProductService().getTargetProduct();
        String imageDir = new FileLocation().getImageDir() + currentProduct.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 350, 280, false, false);
            productDetailImage.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        productDetailTitleDisplay.setText(currentProduct.getTitle());
        productDetailYearDisplay.setText(currentProduct.getPublishedYear());
        productDetailStatusDisplay.setText(currentProduct.getStatus());
        productDetailTypeDisplay.setText(currentProduct.getRentalType());
        productDetailGenreDisplay.setText(currentProduct.getGenre());
        productDetailLoanTypeDisplay.setText(currentProduct.getLoanType());

    }

    public void onAddToCartButton(ActionEvent event){

    }

    public void onCheckCartButton(ActionEvent event){

    }

    public void onIncreaseButton(ActionEvent event){

    }
    public void onDecreaseButton(ActionEvent event){

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadProductDetailData();
    }
}
