package com.example.officialjavafxproj.Controller.Component;

import DataAccess.DataAccess;
import FileLocation.FileLocation;
import Middleware.OrderMiddleware;
import Model.Account.Account;
import Model.Account.GuestAccount;
import Model.Account.RegularAccount;
import Model.Account.VIPAccount;
import Model.Order.Order;
import Model.Order.OrderDetail;
import Model.Product.Product;
import Model.User.User;
import Service.OrderCustomerService;
import Service.ProductService;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OrderItemControllers {

    @FXML
    private Label productOrderTitleDisplay;

    @FXML
    private Label productOrderYearDisplay;

    @FXML
    private Label productOrderPriceDisplay;

    @FXML
    private Label productOrderGenreDisplay;

    @FXML
    private Label productOrderRentalDisplay;

    @FXML
    private Label productOrderQuantityDisplay;

    @FXML
    private ImageView productOrderImage;

    private OrderDetail order;

    public void loadAllOrderItemData(OrderDetail detail){
        String imageDir = new FileLocation().getImageDir() + detail.getBoughtItem().getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 200, 175, false, false);
            productOrderImage.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        order = detail;
        productOrderTitleDisplay.setText(detail.getBoughtItem().getTitle());
        productOrderYearDisplay.setText(detail.getBoughtItem().getPublishedYear());
        productOrderRentalDisplay.setText(detail.getBoughtItem().getRentalType());
        productOrderGenreDisplay.setText(detail.getBoughtItem().getGenre());
        productOrderQuantityDisplay.setText(String.valueOf(detail.getQuantity()));
        productOrderPriceDisplay.setText(String.valueOf(detail.getBoughtItem().getRentalFee() * detail.getQuantity()));
    }

    public void onReturnButton(ActionEvent event) throws IOException {
        User currentUser = new UserServices().getCurrentUser();
        order.getBoughtItem().setNumOfCopies(order.getQuantity() + order.getBoughtItem().getNumOfCopies());
        order.getBoughtItem().setStatus("AVAILABLE");
        Order currentOrder = new OrderCustomerService(new DataAccess(), new OrderMiddleware()).getOne(order.getOrderId());
        System.out.println(currentOrder);
        currentUser.getAccount().setNumReturnedItems(currentUser.getAccount().getNumReturnedItems() + 1);
        currentUser.getAccount().setRentalThreshold(currentUser.getAccount().getRentalThreshold() + 1);
        currentOrder.getOrders().remove(order);
        if(currentUser.getAccount().isAllowedToPromoted()){
            if(currentUser.getAccount() instanceof GuestAccount){
                Account currentAccount = currentUser.getAccount();
                RegularAccount regularAccount = new RegularAccount(currentAccount.getAccountId(), "RegularAccount", currentAccount.getPoints(), currentAccount.getNumReturnedItems(), true, 9999, currentAccount.getIsCurrentlyBorrowed());
                currentUser.setAccount(regularAccount);
            }
            else if(currentUser.getAccount() instanceof RegularAccount){
                Account currentAccount = currentUser.getAccount();
                VIPAccount VIPAccount = new VIPAccount(currentAccount.getAccountId(), "VIPAccount", currentAccount.getPoints(), currentAccount.getNumReturnedItems(), currentAccount.getIsAllowed2DaysItems(), 9999, currentAccount.getIsCurrentlyBorrowed());
                currentUser.setAccount(VIPAccount);
            }
        }
        if(currentUser.getAccount() instanceof VIPAccount){
            currentUser.getAccount().setPoints(currentUser.getAccount().getPoints() + 10);
        }


        if(currentOrder.getOrders().size() == 0){
            new OrderCustomerService(new DataAccess(), new OrderMiddleware()).delete(currentOrder);
            new SceneController().switchScene(event, "../Pages/userOrders.fxml");
        }else{
            new SceneController().switchScene(event, "../Pages/userOrderId.fxml");
        }
        ToastBuilder.builder()
                .withTitle("Item Returned Successfully!")
                .withMode(Notifications.SUCCESS)
                .withMessage("You have returned an item")
                .show();

    }
}
