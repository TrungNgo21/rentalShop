package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import Middleware.InputMiddleware;
import Model.Account.Account;
import Model.User.User;
import Service.UserServices;
import com.example.officialjavafxproj.Threads.UploadImageThread;
import com.example.officialjavafxproj.Utils.FileController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileControllers implements Initializable {

    @FXML
    private AnchorPane navbarPane;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button uploadImageButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button resetToBeginButton;
    @FXML
    private TextField fullNameEditTextField;
    @FXML
    private TextField addressEditTextField;
    @FXML
    private TextField phoneNumEditTextField;

    @FXML
    private Label fullNameWarningMessage;

    @FXML
    private Label addressWarningMessage;

    @FXML
    private Label phoneNumWarningMessage;

    @FXML
    private Label imageMessage;

    private String newImageDir;

    private User currentUser = new UserServices().getCurrentUser();

    public void onFieldReleased() {
        InputMiddleware middleware = new InputMiddleware();
        String fullName = fullNameEditTextField.getText();
        String phoneNum = phoneNumEditTextField.getText();
        String address = addressEditTextField.getText();

        boolean isValid = (middleware.isValidIString(20, address) &&
                middleware.isValidIString(15, fullName) &&
                middleware.isValidPhoneNum(phoneNum));

        saveButton.setDisable(!isValid);

        if(!middleware.isValidIString(15, fullName)){
            fullNameWarningMessage.setText("Your full name must have 15 characters");
        }else {
            fullNameWarningMessage.setText("");
        }

        if(!middleware.isValidIString(20, address)){
            addressWarningMessage.setText("Your address must have 20 characters");
        }else {
            addressWarningMessage.setText("");
        }

        if(!middleware.isValidPhoneNum(phoneNum)){
            phoneNumWarningMessage.setText("Your phone number must have 10 DIGITS");
        }else {
            phoneNumWarningMessage.setText("");
        }
    }

    public void onSaveProfile(ActionEvent event) {
        System.out.println(fullNameEditTextField.getText());
        currentUser.setFullName(fullNameEditTextField.getText());
        currentUser.setAddress(addressEditTextField.getText());
        currentUser.setPhoneNum(phoneNumEditTextField.getText());
        System.out.println(imageMessage.getText());
        if(!imageMessage.getText().equals("")){
            System.out.println("this is reached");
            File renameFile = new File(new FileLocation().getImageDir() + currentUser.getImageLocation());
            File file = new File(newImageDir);
            FileController.deleteFile(renameFile);
            FileController.renameFile(file, renameFile);
        }
        try {
            new SceneController().switchScene(event, "../Pages/userProfile.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onResetToBegin() {
        FileLocation imageDir = new FileLocation();
        String profileImgUrl = imageDir.getImageDir() + currentUser.getImageLocation();
        try {
            Image currentUserImage = new Image(new FileInputStream(profileImgUrl), 400, 400, false, false);
            profileImage.setImage(currentUserImage);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        fullNameEditTextField.setText(currentUser.getFullName());
        addressEditTextField.setText(currentUser.getAddress());
        phoneNumEditTextField.setText(currentUser.getPhoneNum());

    }

    public void onUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.gif", "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            imageMessage.setText(file.getAbsolutePath());
            String ext = FileController.getFileExtension(new File(imageMessage.getText()));
            newImageDir = new FileLocation().getImageDir() + currentUser.getImageLocation() + "Backup" + "." + ext;
            File targetFile = new File( newImageDir);
            UploadImageThread uploadThread = UploadImageThread
                    .builder()
                    .targetFile(targetFile)
                    .uploadedFile(new File(imageMessage.getText()))
                    .finalHeight(400)
                    .finalWidth(400)
                    .build();

            Thread imageThread = new Thread(uploadThread);
            imageThread.start();
            try {
                imageThread.join();
                try {
                    Image uploadUserImage = new Image(new FileInputStream(newImageDir), 400, 400, false, false);
                    profileImage.setImage(uploadUserImage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
            imageMessage.setText("No file chosen");
        }
    }

    public void addNavigationBar(){
        try {
            navbarPane.getChildren().add(new SceneController().getComponentScene(new AnchorPane(), "../Component/navbarComponent.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUserProfile(){
        FileLocation imageDir = new FileLocation();
        String profileImgUrl = imageDir.getImageDir() + currentUser.getImageLocation();
        try {
            Image currentUserImage = new Image(new FileInputStream(profileImgUrl), 400, 400, false, false);
            profileImage.setImage(currentUserImage);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        fullNameEditTextField.setText(currentUser.getFullName());
        addressEditTextField.setText(currentUser.getAddress());
        phoneNumEditTextField.setText(currentUser.getPhoneNum());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNavigationBar();
        loadUserProfile();
    }
}
