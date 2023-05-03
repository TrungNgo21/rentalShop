package com.example.officialjavafxproj.Controller;

import Model.Order.OrderDetail;
import Service.OrderDetailService;
import Service.UserServices;
import com.example.officialjavafxproj.Controller.Component.CartComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UserCartControllers implements Initializable {
    @FXML
    private AnchorPane navbarPane;

    @FXML
    private Label cartItemsQuantityDisplay;

    @FXML
    private VBox cartItemsDisplay;

    @FXML
    private Label subTotalDisplay;

    @FXML
    private Label totalPriceDisplay;

    @FXML
    private Button checkoutButton;



    public void onCheckoutButton(ActionEvent event) {

    }

    public void addNavigationBar() {
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllCartItems() {
        OrderDetailService orderDetailService = new OrderDetailService();
        cartItemsQuantityDisplay.setText(String.valueOf(orderDetailService.getAll().size()));
        if(orderDetailService.getAll().size() == 0){
            Label messageLabel = new Label();
            messageLabel.setText("You have not added anything yet");
            cartItemsDisplay.getChildren().add(messageLabel);
        }else{
            for (Map.Entry<String, OrderDetail> details : orderDetailService.getAll().entrySet()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../Component/CartComponent.fxml"));
                    HBox cartItem = fxmlLoader.load();
                    CartComponentControllers cartComponentControllers = fxmlLoader.getController();
                    cartComponentControllers.loadCartItemData(details.getValue());
                    cartItemsDisplay.getChildren().add(cartItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public void handleDeleteItem(int index){
        cartItemsDisplay.getChildren().remove(index);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadAllCartItems();
    }
}
