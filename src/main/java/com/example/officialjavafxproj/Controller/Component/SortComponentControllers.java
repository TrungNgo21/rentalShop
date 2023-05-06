package com.example.officialjavafxproj.Controller.Component;

import Model.Product.Product;
import Service.ProductService;
import com.example.officialjavafxproj.Utils.CheckboxController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class SortComponentControllers {
    @FXML
    private CheckBox gameCheckbox;
    @FXML
    private CheckBox dvdsCheckbox;
    @FXML
    private CheckBox movieCheckbox;
    @FXML
    private CheckBox day2Checkbox;
    @FXML
    private CheckBox week1Checkbox;
    @FXML
    private CheckBox availableCheckbox;
    @FXML
    private CheckBox borrowedCheckbox;
    @FXML
    private CheckBox actionCheckbox;
    @FXML
    private CheckBox horrorCheckbox;

    @FXML
    private CheckBox dramaCheckbox;

    @FXML
    private CheckBox comedyCheckbox;


    public void onSearchButton(ActionEvent event) throws IOException {
        ProductService productService = new ProductService();
        ArrayList<CheckBox> typeCheckboxes = new ArrayList<>(Arrays.asList(gameCheckbox, dvdsCheckbox, movieCheckbox));
        ArrayList<CheckBox> genresCheckboxes = new ArrayList<>(Arrays.asList(actionCheckbox, horrorCheckbox, comedyCheckbox, dramaCheckbox));
        ArrayList<CheckBox> loanCheckboxes = new ArrayList<>(Arrays.asList(day2Checkbox, week1Checkbox));
        ArrayList<CheckBox> availabilityCheckboxes = new ArrayList<>(Arrays.asList(availableCheckbox, borrowedCheckbox));

        productService.getSortedOptions().clear();
        productService.addSortedOptions(CheckboxController.getAllOptions(typeCheckboxes));
        productService.addSortedOptions(CheckboxController.getAllOptions(genresCheckboxes));
        productService.addSortedOptions(CheckboxController.getAllOptions(loanCheckboxes));
        productService.addSortedOptions(CheckboxController.getAllOptions(availabilityCheckboxes));

        productService.addToSortedProducts(productService.getSortedOptions());
        new SceneController().switchScene(event, "../Pages/sortPage.fxml");

//        for(String[] options : productService.getSortedOptions()){
//            for (String option : options){
//                System.out.println(option);
//            }
//        }
//
//        for(Map.Entry<String, Product> product : productService.getSortedProducts().entrySet()){
//            System.out.println(product.getValue().getTitle());
//        }
    }
    public void onResetButton(){
        gameCheckbox.setSelected(false);
        dvdsCheckbox.setSelected(false);
        movieCheckbox.setSelected(false);
        actionCheckbox.setSelected(false);
        horrorCheckbox.setSelected(false);
        comedyCheckbox.setSelected(false);
        dramaCheckbox.setSelected(false);
        day2Checkbox.setSelected(false);
        week1Checkbox.setSelected(false);
        availableCheckbox.setSelected(false);
        borrowedCheckbox.setSelected(false);
    }

}
