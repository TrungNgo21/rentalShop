package com.example.officialjavafxproj.Controller.Component;

import com.example.officialjavafxproj.Utils.AlertBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class FooterComponent {

    public void onViewInfoButton(ActionEvent event) {
        Alert showProjectInfo = AlertBuilder.builder()
                .withType(Alert.AlertType.INFORMATION)
                .withBodyText("Our application is a website for a local video rental shop. This website allows users to rent, borrow and return a variety of products including videos, games, movie records, and DVDs. You can access our website as a Guest, which would have the least actions on our websites. However, as soon as you register, you are able to rent and borrow more products for a longer period of time. VIP is the highest customer we have with the most rights. Apart from having basic features, we allow our users to be able to search for items, sort items, and many more features. \n" +
                        "The website's admin can manage all the users, products, and orders of the website. Similar to the customer, the admin can search, sort, or separate those into different categories.")
                .withHeaderText("Project Information")
                .build();
        showProjectInfo.getDialogPane().setPrefHeight(500);
        showProjectInfo.getDialogPane().setPrefWidth(500);
        showProjectInfo.show();
    }
}
