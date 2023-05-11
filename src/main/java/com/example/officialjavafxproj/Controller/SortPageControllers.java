package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.ProductComponentControllers;
import com.example.officialjavafxproj.Controller.Component.TopProductComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class SortPageControllers implements Initializable {

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private VBox sortPane;

    @FXML
    private GridPane sortProductDisplay;

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

    public void loadSortedProducts(){
        int row = 1;
        int column = 0;
        if(new ProductService().getSortedProducts().size() == 0){
            Label temp = new Label();
            temp.setText("No Products matched your requirement");
            sortProductDisplay.getChildren().add(temp);
        }
        for(Map.Entry<String, Product> product : new ProductService().getSortedProducts().entrySet()){
            try {
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
                sortProductDisplay.setHgap(10);
                sortProductDisplay.setVgap(10);
                sortProductDisplay.add(productItem, column++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        addNavigationBar();
        addSortedPane();
        loadSortedProducts();
    }
}
