package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.ProductComponentControllers;
import com.example.officialjavafxproj.Controller.Component.TopProductComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


public class HomePageControllers implements Initializable {


    @FXML
    private AnchorPane navbarPane;

    @FXML
    private VBox sortPane;

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

    public void addSortedPane(){
        try {
            sortPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/sortPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductToGridView(){
        int row = 1;
        int column = 0;
        for(Map.Entry<String, Product> product : new ProductService().getAll().entrySet()){
            try {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("../Component/topProductComponent.fxml"));
                AnchorPane productCard = fxmlLoader1.load();
                TopProductComponentControllers productCardController = fxmlLoader1.getController();
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
                productsGridDisplay.setHgap(15);
                productsGridDisplay.setVgap(15);
                productsGridDisplay.add(productItem, column++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productsGridDisplay.getChildren().add(new Label("Loading...."));
        topProductsContainer.getChildren().add(new Label("Loading...."));
        topProductsContainer.setAlignment(Pos.CENTER_LEFT);
        sortPane.getChildren().add(new Label("Loading...."));
        sortPane.setAlignment(Pos.CENTER);
        addNavigationBar();
        Platform.runLater(()->{
            topProductsContainer.getChildren().clear();
            productsGridDisplay.getChildren().clear();
            sortPane.getChildren().clear();
            addProductToGridView();
            addSortedPane();
        });


    }
}
