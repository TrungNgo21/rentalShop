package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Service.OrderAdminService;
import com.example.officialjavafxproj.Controller.Component.AdminOrderDetailController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminViewOrderDetailController implements Initializable {
    @FXML
    private AnchorPane navbar;
    private Order order;
    @FXML
    private GridPane container;
    private void addNavigationBar() {
        try {
            navbar.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addOrderDetail() {
        int column = 0;
        int row = 0;
        order = new OrderAdminService(new DataAccess()).getSelectedOrder();
        for(Map.Entry<String, OrderDetail> orderDetail: new OrderAdminService(new DataAccess()).getAllOrderDetail(order).entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../Component/adminViewOrderDetailComponent.fxml"));
                VBox userItem = loader.load();
                AdminOrderDetailController adminOrderController = loader.getController();
                adminOrderController.loadDisplayOrder(orderDetail.getValue(), order);
                if(column == 1) {
                    column = 0;
                    row++;
                }

                container.setHgap(10);
                container.setHgap(10);
                container.add(userItem, column, row++);
            } catch (Exception e) {
                throw new RuntimeException(e);
           }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        addOrderDetail();
    }
}
