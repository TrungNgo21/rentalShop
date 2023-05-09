package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Controller.Component.AdminProductController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminViewProductController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private AnchorPane adminNavbar;
    @FXML
    private Label sortLabel;
    @FXML
    private RadioButton titleButton;
    @FXML
    private RadioButton idButton;
    @FXML
    private RadioButton stockButton;
    @FXML
    private TextField textField;
    @FXML
    private Button searchButton;
    @FXML
    private ChoiceBox<String> genreSelection;
    @FXML
    private GridPane gridPane;

    private String sortedChoice;
    private String genreChoice;
    @FXML
    private final ObservableList<String> list = FXCollections.observableArrayList( "All","DVD", "GAME", "MRecords");

    ToggleGroup toggleGroup = new ToggleGroup();



    public void addNavigationBar(){
        try {
            adminNavbar.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavBarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductToGridView(){
        System.out.println(genreChoice);
            HashMap<String,Product> temp = new HashMap<>();
            Label emptylabel = new Label();
            int column = 1;
            int row = 0;
            ProductService productService = new ProductService();
            gridPane.getChildren().clear();
            if(sortedChoice == null){
                if(genreChoice == null){
                    gridPane.add(emptylabel,0,0);
                }
                else if(genreChoice.equals("All")){
                    temp = DataAccess.getAllProducts();
                }
                else {
                    temp = productService.getProductByType(genreChoice);
                }
            }
            else if (sortedChoice.equals("SortById")){
                temp = productService.sortById(genreChoice);
            } else if (sortedChoice.equals("SortByTitle")) {
                temp = productService.sortByTitle(genreChoice);
            }
            System.out.println(sortedChoice);
            System.out.println(temp);
            for(Map.Entry<String, Product> product : temp.entrySet()){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../Component/adminViewProductComponent.fxml"));
                    HBox productItem = loader.load();
                    AdminProductController adminProductController = loader.getController();

                    adminProductController.loadProductDisplay(product.getValue());
                    if(column == 1){
                        column = 0;
                        row++;
                    }
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);
                    gridPane.add(productItem,column,row++);
                }
                catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
    }
    public void setChoiceBox(){
        genreSelection.setItems(list);
    }
    public void setUpSortByToggleGroup(ActionEvent actionEvent) throws IOException{
        stockButton.setToggleGroup(toggleGroup);
        idButton.setToggleGroup(toggleGroup);
        titleButton.setToggleGroup(toggleGroup);
        if(idButton.isSelected()){
            sortedChoice = "SortById";
        }
        if(titleButton.isSelected()){
            sortedChoice = "SortByTitle";
        }
    }
    public void getGenreSelection(ActionEvent actionEvent) throws IOException {
        genreChoice = genreSelection.getValue();
    }
    public void show(ActionEvent actionEvent) {
        System.out.println(genreChoice);
        System.out.println(sortedChoice);
        addProductToGridView();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        addNavigationBar();
        setChoiceBox();
    }
}
