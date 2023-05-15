package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.AdminProductController;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.SearchController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminSortProductController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox sortLayout;
    @FXML
    private RadioButton priceButton;
    @FXML
    private RadioButton titleButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;


    public void setDisableButton() {
        titleButton.setOnMouseClicked(mouseEvent -> {
            searchTextField.setDisable(true);
        });
        priceButton.setOnMouseClicked(MouseEvent -> {
            searchTextField.setDisable(true);
        });
    }

    public void resetToBegin(ActionEvent actionEvent) {
        titleButton.setDisable(false);
        priceButton.setDisable(false);
        titleButton.setSelected(false);
        priceButton.setSelected(false);
        searchTextField.setDisable(false);
        searchTextField.clear();
        DataAccess.getSortedProducts().clear();
        loadSortedProducts();
    }

    public void onFiedReleased() {
        String search = searchTextField.getText();
        if (!search.trim().isEmpty()) {
            titleButton.setDisable(true);
            priceButton.setDisable(true);
        } else {
            titleButton.setDisable(false);
            priceButton.setDisable(false);
        }
    }

    public void addSortedPane() {
        try {
            sortLayout.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/sortPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSortedProducts() {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        if (new ProductService().getSortedProducts().size() == 0) {
            Label temp = new Label();
            temp.setText("No Products matched your requirement");
            gridPane.getChildren().add(temp);
        }
        for (Map.Entry<String, Product> product : new ProductService().getSortedProducts().entrySet()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/adminViewProductComponent.fxml"));
                HBox productItem = fxmlLoader.load();
                AdminProductController adminProductController = fxmlLoader.getController();
                adminProductController.loadProductDisplay(product.getValue());
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(productItem, column, row++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadSearchProducts() {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        if (SearchController.getTempContainer().isEmpty()) {
            Label temp = new Label();
            temp.setText("No Products matched your requirement");
            gridPane.getChildren().add(temp);
        }
        for (Map.Entry<String, Product> product : SearchController.getTempContainer().entrySet()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Component/adminViewProductComponent.fxml"));
                HBox productItem = fxmlLoader.load();
                AdminProductController adminProductController = fxmlLoader.getController();
                adminProductController.loadProductDisplay(product.getValue());
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.add(productItem, column, row++);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setToggleGroup() {
        ToggleGroup toggleGroup = new ToggleGroup();
        priceButton.setToggleGroup(toggleGroup);
        titleButton.setToggleGroup(toggleGroup);
    }

    public void search(ActionEvent actionEvent) {
        String search = searchTextField.getText().trim();
        if (priceButton.isSelected()) {
            new ProductService().sortByPrice();
        }
        if (titleButton.isSelected()) {
            new ProductService().sortByTitle();
        }
        if (!search.trim().isEmpty()) {
            if (DataAccess.getSortedProducts().isEmpty()) {
                SearchController.searchByIdentify(search, DataAccess.getAllProducts());
                loadSearchProducts();
            } else {
                SearchController.searchByIdentify(search, DataAccess.getSortedProducts());
                loadSearchProducts();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSortedProducts();
        addSortedPane();
        setToggleGroup();
        setDisableButton();
    }
}
