package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Product.Product;
import com.example.officialjavafxproj.Controller.Component.ProductComponentControllers;
import com.example.officialjavafxproj.Controller.Component.TopProductComponent;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class HomePageControllers implements Initializable {


    @FXML
    private AnchorPane navbarPane;

    @FXML
    private HBox topProductsContainer;

    @FXML
    private GridPane productsGridDisplay;



    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductToGridView(){
        int row = 1;
        int column = 0;
        for(Map.Entry<String, Product> product : DataAccess.getAllProducts().entrySet()){
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("../Component/topProductComponent.fxml"));
                AnchorPane productCard = fxmlLoader1.load();
                TopProductComponent productCardController = fxmlLoader1.getController();
                productCardController.loadTopProductData(product.getValue());
                topProductsContainer.getChildren().add(productCard);


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/productComponent.fxml"));
                AnchorPane productItem = fxmlLoader.load();
//                AnchorPane productItem = (AnchorPane) new SceneController().getComponentScene(new AnchorPane(), "../Component/productComponent.fxml");
//                ProductComponentControllers productItemsController = (ProductComponentControllers) new SceneController().getComponentController("../Component/productComponent.fxml");
                ProductComponentControllers productComponentControllers = fxmlLoader.getController();
                productComponentControllers.loadProductItemData(product.getValue());
                if(column == 4){
                    column = 0;
                    ++row;
                }
                productsGridDisplay.setHgap(10);
                productsGridDisplay.setVgap(10);
                productsGridDisplay.add(productItem, column++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addProductToGridView();
    }
}
