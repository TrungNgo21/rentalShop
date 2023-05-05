package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Service.OrderCustomerService;
import Service.OrderDetailCartService;
import Service.UserServices;
import com.example.officialjavafxproj.Controller.Component.CartComponentControllers;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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



    public void setCheckoutButton(){
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        checkoutButton.setDisable(orderDetailCartService.getAll().isEmpty());

    }
    public void onCheckoutButton(ActionEvent event) throws IOException {
        UserServices userServices = new UserServices();
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        Order madeOrder = new Order(new OrderCustomerService(new DataAccess(), new OrderMiddleware()).idCreation(), new UserServices().getCurrentUser().getUserId(), LocalDate.now(), Double.parseDouble(totalPriceDisplay.getText()));
        for(Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
            details.getValue().setCartId("NaN");
            details.getValue().setOrderId(madeOrder.getOrderId());
            madeOrder.addOrderDetailsToOrder(details.getValue());
        }
        new OrderCustomerService(new DataAccess(), new OrderMiddleware()).add(madeOrder);

        if(userServices.getCurrentUser().getBalance() < Double.parseDouble(totalPriceDisplay.getText())){
            ToastBuilder.builder()
                    .withTitle("Insufficient Money")
                    .withMode(Notifications.ERROR)
                    .withMessage("You cannot make this purchase!")
                    .show();
        }else{
            userServices.getCurrentUser().setBalance(userServices.getCurrentUser().getBalance() - Double.parseDouble(totalPriceDisplay.getText()));
            new SceneController().switchScene(event, "../Pages/userOrders.fxml");
            for(Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
                orderDetailCartService.delete(details.getValue());
            }
        }

    }

    public void addNavigationBar() {
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAllCartItems() {
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        cartItemsQuantityDisplay.setText(String.valueOf(orderDetailCartService.getAll().size()));
        double subTotal = 0;
        if(orderDetailCartService.getAll().size() == 0){
            Label messageLabel = new Label();
            messageLabel.setText("You have not added anything yet");
            cartItemsDisplay.getChildren().add(messageLabel);
            subTotalDisplay.setText("0");
            totalPriceDisplay.setText("0");
        }else{
            for (Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()) {
                subTotal += (details.getValue().getBoughtItem().getRentalFee() * details.getValue().getQuantity());
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
            subTotalDisplay.setText(String.valueOf(subTotal));
            totalPriceDisplay.setText(subTotalDisplay.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadAllCartItems();
        setCheckoutButton();
    }
}
