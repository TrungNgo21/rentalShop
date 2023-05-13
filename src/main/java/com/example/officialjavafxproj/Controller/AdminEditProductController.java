package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import FileLocation.FileLocation;
import Middleware.InputMiddleware;
import Model.Product.Product;
import com.example.officialjavafxproj.Utils.FileController;
import com.example.officialjavafxproj.Utils.SceneController;
import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.notification.Notifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEditProductController implements Initializable {
    @FXML
    private AnchorPane navbarPane;
    @FXML
    private ImageView productImage;
    @FXML
    private Button uploadImageButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button resetButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField copiesTextField;
    @FXML
    private Label nameWarningMessage;
    @FXML
    private Label priceWarningMessage;
    @FXML
    private Label copiesWarningMessage;
    @FXML
    private Label imageMessage;
    @FXML
    private Button backButton;

    private String newImageDir;

    private Product product = DataAccess.getChosenProduct();

    public void onFieldReleased() {
        InputMiddleware middleware = new InputMiddleware();
        String productName = nameTextField.getText();
        String productPrice = priceTextField.getText();
        String numOfCopies = copiesTextField.getText();

        boolean isValid = (!productName.trim().isEmpty() && middleware.isPositve(productPrice) && middleware.isPositve(numOfCopies));

        saveButton.setDisable(!isValid);

        if(productName.trim().isEmpty()){
            nameWarningMessage.setText("You must not leave this field empty");
        }
        else {
            nameWarningMessage.setText("");
        }
        if(productPrice.trim().isEmpty()){
            priceWarningMessage.setText("You must not leave this field empty");
        }
        else if(!middleware.isPositve(productPrice)){
            priceWarningMessage.setText("The price must be positive");
        }
        else {
            priceWarningMessage.setText("");
        }

        System.out.println(productPrice);
        System.out.println(middleware.isPositve(productPrice));
        if(numOfCopies.trim().isEmpty()){
            copiesWarningMessage.setText("You must not leave this field empty");
        }
        else if(!middleware.isPositve(numOfCopies)){
            copiesWarningMessage.setText("Num of copies must be positive");
        }
        else {
            copiesWarningMessage.setText("");
        }


    }
    public void onSaveInformation(ActionEvent actionEvent){
        product.setTitle(nameTextField.getText());
        product.setRentalFee(Double.parseDouble(priceTextField.getText()));
        product.setNumOfCopies(Integer.parseInt(copiesTextField.getText()));
        if(!imageMessage.getText().equals("")){
            if(!imageMessage.getText().equals("No file chosen")){
                File renameFile = new File(new FileLocation().getImageDir() + product.getImageLocation());
                String ext = FileController.getFileExtension(renameFile);
                renameFile = new File(new FileLocation().getImageDir() + "Product/" + product.getId() + "." + ext);
                File file = new File(newImageDir);
                FileController.deleteFile(renameFile);
                product.setImageLocation("Users/" + product.getId() + "." + ext);
                FileController.renameFile(file, renameFile);
            }
        }
        try {
            new SceneController().switchScene(actionEvent, "../Pages/adminViewProduct.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ToastBuilder.builder()
                .withTitle("Changed Successfully")
                .withMessage("You information has changed successfully")
                .withMode(Notifications.SUCCESS)
                .show();

    }
    public void onResetToBegin() {
        FileLocation imageDir = new FileLocation();
        String profileImgUrl = imageDir.getImageDir() + product.getImageLocation();
        try {
            Image currentUserImage = new Image(new FileInputStream(profileImgUrl), 400, 400, false, false);
            productImage.setImage(currentUserImage);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        nameTextField.setText(product.getTitle());
        priceTextField.setText(String.valueOf(product.getRentalFee()));
        copiesTextField.setText(String.valueOf(product.getNumOfCopies()));
    }
    public void loadProductDetail() {
        FileLocation imageDir = new FileLocation();
        String profileImgUrl = imageDir.getImageDir() + product.getImageLocation();
//        try {
//            Image currentUserImage = new Image(new FileInputStream(profileImgUrl), 400, 400, false, false);
//            productImage.setImage(currentUserImage);
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
        nameTextField.setText(product.getTitle());
        priceTextField.setText(String.valueOf(product.getRentalFee()));
        copiesTextField.setText(String.valueOf(product.getNumOfCopies()));
    }
    public void back(ActionEvent actionEvent) throws IOException{
        new SceneController().switchScene(actionEvent, "../Pages/adminViewProduct.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProductDetail();
    }
}
