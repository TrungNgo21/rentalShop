package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Service.OrderAdminService;
import com.example.officialjavafxproj.Controller.Component.AdminOrderDetailController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AdminViewOrderDetailController implements Initializable {
    @FXML
    private AnchorPane navbar;
    private Order order;
    @FXML
    private ScrollPane container;
    @FXML
    private VBox productList;
    @FXML
    private Label totalPrice;
    @FXML
    private AnchorPane footerPane;

    public void addFooterBar(){
        try {
            footerPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/footer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addNavigationBar() {
        try {
            navbar.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/adminNavbarComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void addOrderDetail() {
        order = new OrderAdminService(new DataAccess()).getSelectedOrder();
        VBox vBox = new VBox();
        for(Map.Entry<String, OrderDetail> orderDetail: new OrderAdminService(new DataAccess()).getAllOrderDetail(order).entrySet()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../Component/adminViewOrderDetailComponent.fxml"));
                VBox userItem = loader.load();
                AdminOrderDetailController adminOrderController = loader.getController();
                adminOrderController.loadDisplayOrder(orderDetail.getValue(), order);
                vBox.getChildren().add(userItem);
                vBox.setSpacing(20);
                container.setContent(vBox);
                container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            } catch (Exception e) {
                throw new RuntimeException(e);
           }
        }
    }

    private void addProductTotalPrice() {
        for(Map.Entry<String, OrderDetail> orderDetail: new OrderAdminService(new DataAccess()).getAllOrderDetail(order).entrySet()) {
            Label product = new Label(orderDetail.getValue().getBoughtItem().getTitle());
            product.setFont(new Font("Arial", 18));
            productList.getChildren().add(product);
        }
        totalPrice.setText((int)order.getTotalPrice()+"");
    }

    public void onUserPageBackButton(ActionEvent actionEvent) throws IOException{
        new SceneController().switchScene(actionEvent, "../Pages/adminViewOrders.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFooterBar();
        addNavigationBar();
        addOrderDetail();
        addProductTotalPrice();
    }
}
