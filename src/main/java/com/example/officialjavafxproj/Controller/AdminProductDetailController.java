package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminProductDetailController implements Initializable {
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
    private Label productDetailStockDisplay;
    @FXML
    private Label productDetailRentalFee;


    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavBarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadProductDetail(){
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
        productDetailStockDisplay.setText(String.valueOf(currentProduct.getNumOfCopies()));
        productDetailRentalFee.setText(String.valueOf(currentProduct.getRentalFee()));
    }
    public void editProduct(ActionEvent actionEvent) throws IOException{
        Product currentProduct = new ProductService().getTargetProduct();
        new ProductService().setTargetProduct(currentProduct);
        new SceneController().switchScene(actionEvent, "../Pages/adminEditProduct.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductDetail();
        addNavigationBar();
    }
}
