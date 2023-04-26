package com.example.officialjavafxproj.Controller;

import com.example.officialjavafxproj.Utils.SceneSwitcher;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileControllers implements Initializable {

    @FXML
    private AnchorPane navbarPane;
    @FXML
    private Label customerIdDisplay;

    @FXML
    private Label fullNameDisplay;

    @FXML
    private Label addressDisplay;

    @FXML
    private Label phoneNumDisplay;

    @FXML
    private Label balanceDisplay;

    @FXML
    private Label accountIdDisplay;

    @FXML
    private Label accountTypeDisplay;

    @FXML
    private Label accountPointsDisplay;

    @FXML
    private Label noReturnedItemsDisplay;

    @FXML
    private Label noFreeToBorrowDisplay;

    @FXML
    private Label noMaximumItemsDisplay;

    public void addNavigationBar(){
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        try {
            navbarPane.getChildren().add(sceneSwitcher.getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
    }
}
