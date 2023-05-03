package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.Order.OrderDetail;
import Model.Product.Product;
import Service.*;
import com.example.officialjavafxproj.Controller.Component.ProductComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
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

    @FXML
    private Button decreaseButton;

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
        productDetailQuantityTextField.setText("1");

    }

    public void onAddToCartButton(ActionEvent event) throws IOException{
        OrderDetailService orderDetailService = new OrderDetailService();
        UserCartServices userCartServices = new UserCartServices();
        Product currentProduct = new ProductService().getTargetProduct();

        if(orderDetailService.getOne(currentProduct.getId()) != null){
            OrderDetail details = orderDetailService.getOne(currentProduct.getId());
            details.setQuantity(details.getQuantity() + Integer.parseInt(productDetailQuantityTextField.getText()));
        }else{
            OrderDetail detail = new OrderDetail(orderDetailService.idCreation(), "NaN", userCartServices.idCreation(), currentProduct, Integer.parseInt(productDetailQuantityTextField.getText()));
            orderDetailService.add(detail);
        }
        new SceneController().switchScene(event, "../Pages/userCart.fxml");

    }

    public void onCheckCartButton(ActionEvent event) throws IOException{
        new SceneController().switchScene(event, "../Pages/userCart.fxml");
    }

    public void onIncreaseButton(ActionEvent event){
        productDetailQuantityTextField.setText(String.valueOf(Integer.parseInt(productDetailQuantityTextField.getText()) + 1));
    }
    public void onDecreaseButton(ActionEvent event){
        if(Integer.parseInt(productDetailQuantityTextField.getText()) == 1){
            decreaseButton.setDisable(true);
        }else{
            decreaseButton.setDisable(false);
            productDetailQuantityTextField.setText(String.valueOf(Integer.parseInt(productDetailQuantityTextField.getText()) - 1));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadProductDetailData();
    }
}
