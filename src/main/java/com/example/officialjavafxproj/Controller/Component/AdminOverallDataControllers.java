package com.example.officialjavafxproj.Controller.Component;

import Middleware.DateMiddleware;
import Service.ProductService;
import Service.RevenueService;
import Service.UserServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminOverallDataControllers implements Initializable {
    @FXML
    private Label totalCustomerDisplay;
    @FXML
    private Label totalItemsDisplay;
    @FXML
    private Label totalRevenueDisplay;
    @FXML
    private Label todayRevenueDisplay;

    private void loadOverallData(){
        totalRevenueDisplay.setText(String.valueOf(new RevenueService().getRevenue()));
        totalCustomerDisplay.setText(String.valueOf(new UserServices().getAll().size() - 1));
        totalItemsDisplay.setText(String.valueOf(new ProductService().getAll().size()));
        todayRevenueDisplay.setText(String.valueOf(new DateMiddleware().dateAfterFormat(LocalDate.now()) + " " + (new RevenueService().getAllRevenue().get(LocalDate.now()) == null ? 0 : new RevenueService().getAllRevenue().get(LocalDate.now()) )));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOverallData();
    }
}
