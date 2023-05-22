package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Model.Form.Feedback;
import Model.Product.Product;
import Service.FeedbackService;
import Service.ProductService;
import Service.UserServices;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserRatingControllers implements Initializable {

    @FXML
    private TextField commentsTextField;

    @FXML
    private VBox reviewBoxDisplay;

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private Label productDetailGenreDisplay;

    @FXML
    private ImageView productDetailImage;

    @FXML
    private Label productDetailLoanTypeDisplay;


    @FXML
    private Label productDetailStatusDisplay;

    @FXML
    private Label productDetailStockDisplay;

    @FXML
    private Label productDetailTitleDisplay;

    @FXML
    private Label productDetailTypeDisplay;

    @FXML
    private Label productDetailYearDisplay;

    @FXML
    private ChoiceBox<Integer> ratingSelection;

    @FXML
    private AnchorPane ratingChartDisplay;

    private Integer[] stars = {1,2,3,4,5};

    @FXML
    public void onBackToDetailButton(ActionEvent event) throws IOException {
        new SceneController().switchScene(event, "../Pages/productDetails.fxml");
    }

    @FXML
    public void onPostButton(ActionEvent event) throws IOException{
        Feedback feedback = Feedback.builder()
                .withCustomerId(new UserServices().getCurrentUser().getUserId())
                .withProductId(new ProductService().getTargetProduct().getId())
                .withRating(ratingSelection.getValue())
                .withFeedbackContent(commentsTextField.getText())
                .withReviewDate(LocalDate.now())
                .build();
        new ProductService().getTargetProduct().addFeedback(feedback);
        new UserServices().getCurrentUser().addReview(feedback);
        new FeedbackService().addFeedbackToDb(feedback);
        new SceneController().switchScene(event, "../Pages/userRatingItem.fxml");
        ToastBuilder.builder()
                .withMode(Notifications.SUCCESS)
                .withTitle("Review Message")
                .withMessage("You have posted successfully!")
                .show();

    }

    public void setUpRatingSelection(){
        ratingSelection.getItems().addAll(stars);
    }
    public void loadProductDetail(){
        Product currentProduct = new ProductService().getTargetProduct();
        String imageDir = new FileLocation().getImageDir() + currentProduct.getImageLocation();
        try {
            Image productImage = new Image(new FileInputStream(imageDir), 350, 200, false, false);
            productDetailImage.setImage(productImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        productDetailTitleDisplay.setText(currentProduct.getTitle());
        productDetailYearDisplay.setText(currentProduct.getPublishedYear());
        productDetailStatusDisplay.setText(currentProduct.getStatus());
        productDetailTypeDisplay.setText(currentProduct.getRentalType());
        productDetailGenreDisplay.setText(currentProduct.getGenre());
        productDetailLoanTypeDisplay.setText(currentProduct.getLoanType());
        productDetailStockDisplay.setText(String.valueOf(currentProduct.getNumOfCopies()));
    }

    public void addReviewBox(){
        try {
            reviewBoxDisplay.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/reviewBoxComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addRatingChart(){
        try {
            ratingChartDisplay.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/starChartComponent.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductDetail();
        setUpRatingSelection();
        addNavigationBar();
        addReviewBox();
        addRatingChart();
    }
}
