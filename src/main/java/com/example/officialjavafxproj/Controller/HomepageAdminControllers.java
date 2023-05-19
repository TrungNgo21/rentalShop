package com.example.officialjavafxproj.Controller;

import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageAdminControllers implements Initializable {

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private GridPane chartDisplay;

    public void addNavigationBar(){
        navbarPane.getChildren().clear();
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCharts(){
        FXMLLoader barChartLoader = new FXMLLoader(getClass().getResource("../Component/barChartComponent.fxml"));
        FXMLLoader pieChartLoader = new FXMLLoader(getClass().getResource("../Component/pieChartComponent.fxml"));

        try {
            AnchorPane barChart = barChartLoader.load();
            AnchorPane pieChart = pieChartLoader.load();
            chartDisplay.setAlignment(Pos.CENTER);
            chartDisplay.add(pieChart,1,0);
            chartDisplay.add(barChart,0,0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addCharts();
    }
}
