package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import Middleware.OrderMiddleware;
import Model.Account.GuestAccount;
import Model.Account.VIPAccount;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Service.OrderCustomerService;
import Service.OrderDetailCartService;
import Service.ProductService;
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

    @FXML
    private Label vipBenefitDisplay;



    public void setCheckoutButton(){
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        checkoutButton.setDisable(orderDetailCartService.getAll().isEmpty());

    }
    public void onCheckoutButton(ActionEvent event) throws IOException {
        UserServices userServices = new UserServices();
        boolean isOutOfStock = false;
        boolean isTwoDayItems = false;
        OrderDetailCartService orderDetailCartService = new OrderDetailCartService();
        Order madeOrder = new Order(new OrderCustomerService(new DataAccess(), new OrderMiddleware()).idCreation(), new UserServices().getCurrentUser().getUserId(), LocalDate.now(), Double.parseDouble(totalPriceDisplay.getText()));
        for(Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
            if(details.getValue().getQuantity() > details.getValue().getBoughtItem().getNumOfCopies()){
                isOutOfStock = true;
                break;
            }
            if(details.getValue().getBoughtItem().getLoanType().equals("2-DAY")){
                isTwoDayItems = true;
                break;
            }
        }

        if(userServices.getCurrentUser().getAccount() instanceof GuestAccount){
            if(userServices.getCurrentUser().getAccount().getRentalThreshold() < orderDetailCartService.getAll().size()){
                ToastBuilder.builder()
                        .withTitle("Exceeded Number of Items!")
                        .withMessage("Your account can only order up to 2 items")
                        .withMode(Notifications.ERROR)
                        .show();
                return;
            }
            if(isTwoDayItems){
                ToastBuilder.builder()
                        .withTitle("Not Eligible!")
                        .withMessage("Your account can not order 2-days items!")
                        .withMode(Notifications.ERROR)
                        .show();
                return;
            }
        }

        if(isOutOfStock){
            ToastBuilder.builder()
                    .withTitle("Out Of Stock")
                    .withMessage("Your order quantity is too many!!!")
                    .withMode(Notifications.ERROR)
                    .show();
        }else{
            if(userServices.getCurrentUser().getBalance() < Double.parseDouble(totalPriceDisplay.getText())){
                ToastBuilder.builder()
                        .withTitle("Insufficient Money")
                        .withMode(Notifications.ERROR)
                        .withMessage("You cannot make this purchase!")
                        .show();
            }else{
                ToastBuilder.builder()
                        .withTitle("Order Successfully")
                        .withMode(Notifications.SUCCESS)
                        .withMessage("Your purchase is proceeded")
                        .show();
                for(Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
                    details.getValue().setCartId("NaN");
                    details.getValue().setOrderDetailId(new OrderDetailCartService().idCreation());
                    details.getValue().setOrderId(madeOrder.getOrderId());
                    madeOrder.addOrderDetailsToOrder(details.getValue());
                }
                new OrderCustomerService(new DataAccess(), new OrderMiddleware()).add(madeOrder);
                for(OrderDetail detail : madeOrder.getOrders()){
                    detail.getBoughtItem().setNumOfCopies(detail.getBoughtItem().getNumOfCopies() - detail.getQuantity());
                    if(detail.getBoughtItem().getNumOfCopies() == 0){
                        detail.getBoughtItem().setStatus("BORROWED");
                    }
                }
                userServices.getCurrentUser().setBalance(userServices.getCurrentUser().getBalance() - Double.parseDouble(totalPriceDisplay.getText()));
                userServices.getCurrentUser().getAccount().setRentalThreshold(userServices.getCurrentUser().getAccount().getRentalThreshold() - orderDetailCartService.getAll().size());
                new SceneController().switchScene(event, "../Pages/userOrders.fxml");
                for(Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
                    orderDetailCartService.delete(details.getValue());
                }
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
            if(new UserServices().getCurrentUser().getAccount() instanceof VIPAccount){
                if (new UserServices().getCurrentUser().getAccount().isFreeToBorrowOne()){
                    double price = 0;
                    for (Map.Entry<String, OrderDetail> details : orderDetailCartService.getAll().entrySet()){
                        if(details.getValue().getBoughtItem().getRentalFee() * details.getValue().getQuantity() > price){
                            price = details.getValue().getBoughtItem().getRentalFee() * details.getValue().getQuantity();
                        }
                    }
                    vipBenefitDisplay.setText("- " + "$ " + price);
                    subTotalDisplay.setText(String.valueOf(subTotal));
                    totalPriceDisplay.setText(String.valueOf(Double.parseDouble(subTotalDisplay.getText()) - price));
                }else{
                    vipBenefitDisplay.setText("None");
                    subTotalDisplay.setText(String.valueOf(subTotal));
                    totalPriceDisplay.setText(subTotalDisplay.getText());
                }
            }else{
                vipBenefitDisplay.setText("None");
                subTotalDisplay.setText(String.valueOf(subTotal));
                totalPriceDisplay.setText(subTotalDisplay.getText());
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadAllCartItems();
        setCheckoutButton();
    }
}
