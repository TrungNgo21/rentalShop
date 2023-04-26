package com.example.officialjavafxproj.Controller;

import DataAccess.DataAccess;
import FileLocation.FileLocation;
import Middleware.InputMiddleware;
import Middleware.UserMiddleware;
import Model.Account.Account;
import Model.Account.GuestAccount;
import Model.User.Customer;
import Model.User.User;
import Service.UserServices;
import com.example.officialjavafxproj.Threads.UploadImageThread;
import com.example.officialjavafxproj.Utils.FileController;
import com.example.officialjavafxproj.Utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class RegisterControllers {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button registerButton;

    @FXML
    private Button toLoginButton;

    @FXML
    private TextField fullnameTextField;

    @FXML
    private TextField addressTextField;

    @FXML TextField phoneNumTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPassTextField;

    @FXML
    private Label registerMessage;

    @FXML
    private Label imageMessage;

    @FXML
    private Label fullNameErrorMessage;

    @FXML
    private Label addressErrorMessage;

    @FXML
    private Label phoneNumErrorMessage;

    @FXML
    private Label userNameErrorMessage;

    @FXML
    private Label passwordErrorMessage;

    @FXML
    private Label rePasswordErrorMessage;

    public void FieldOnReleased(){
        InputMiddleware middleware = new InputMiddleware();
        String userName = usernameTextField.getText();
        String fullName = fullnameTextField.getText();
        String password = passwordTextField.getText();
        String rePass = confirmPassTextField.getText();
        String phoneNum = phoneNumTextField.getText();
        String address = addressTextField.getText();
        boolean isDisabled = (userName.isEmpty() || userName.trim().isEmpty() ||
                fullName.isEmpty() || fullName.trim().isEmpty() ||
                password.isEmpty() || rePass.isEmpty() ||
                phoneNum.isEmpty() || phoneNum.trim().isEmpty() ||
                address.isEmpty() || address.trim().isEmpty());

        boolean isValid = (middleware.isValidUsername(userName) ||
                middleware.isValidPassword(password) ||
                password.equals(rePass) ||
                middleware.isValidIString(20, address) ||
                middleware.isValidIString(15, fullName) ||
                middleware.isValidPhoneNum(phoneNum));

        if(!middleware.isValidIString(15, fullName)){
            fullNameErrorMessage.setText("Your full name must have 15 characters");
        }else {
            fullNameErrorMessage.setText("");
        }

        if(!middleware.isValidIString(20, address)){
            addressErrorMessage.setText("Your address must have 20 characters");
        }else {
            addressErrorMessage.setText("");
        }

        if(!middleware.isValidPhoneNum(phoneNum)){
            phoneNumErrorMessage.setText("Your phone number must have 10 DIGITS");
        }else {
            phoneNumErrorMessage.setText("");
        }

        if(!middleware.isValidUsername(userName)){
            userNameErrorMessage.setText("Your username must have 12 characters and no whitespaces");
        }else {
            userNameErrorMessage.setText("");
        }

        if(!middleware.isValidPassword(password)){
            passwordErrorMessage.setText("Your password must have at least one uppercase letter, one lowercase letter, one number, one special character and 10 characters");
        }else {
            passwordErrorMessage.setText("");
        }

        if(!password.equals(rePass)){
            rePasswordErrorMessage.setText("Your confirmation password must be the same");
        }else{
            rePasswordErrorMessage.setText("");
        }
        registerButton.setDisable(isDisabled || !isValid);
    }

    public void onToLoginButton(ActionEvent event) throws IOException {
        SceneSwitcher sceneSwitcher = new SceneSwitcher();
        sceneSwitcher.switchScene(event, "../Pages/login.fxml");
    }

    public void onImageUploadButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.gif", "*.jpg"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            imageMessage.setText(file.getAbsolutePath());
        }else{
            imageMessage.setText("No file chosen");
        }
    }

    public void onExitButton(){
        DataAccess.transferAllData();
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

    public void onRegisterButton() throws InterruptedException {
        String userName = usernameTextField.getText();
        String fullName = fullnameTextField.getText();
        String password = passwordTextField.getText();
        String rePass = confirmPassTextField.getText();
        String phoneNum = phoneNumTextField.getText();
        String address = addressTextField.getText();

        String targetFileDir = "";
        String ext = FileController.getFileExtension(new File(imageMessage.getText()));
        File targetFile = new File( new FileLocation().getImageDir() + "Users/" + new UserServices().idCreation() + "." + ext);

        if(imageMessage.getText().equals("No file chosen")){
            targetFileDir = "Users/default.png";
        }else{
            targetFileDir = "Users/" + new UserServices().idCreation() + "." + ext;
        }
        UploadImageThread uploadThread = new UploadImageThread(targetFile, new File(imageMessage.getText()), 400, 400);
        Thread imageThread = new Thread(uploadThread);

        if(!rePass.equals(password)){
            registerMessage.setText("Not the same password!!!");
        }else{
            try{
                imageThread.start();
                UserServices userServices = new UserServices();
                userServices.register(new Customer(userServices.idCreation(), userName, password, fullName, address, phoneNum, 0, new GuestAccount(), targetFileDir));

                for(Map.Entry<String, User> user : userServices.getAll().entrySet()){
                    System.out.println(user);
                }
            }catch (Error err){
                registerMessage.setText(err.getMessage());
                imageThread.join();
                FileController.deleteFile(targetFile);
            }
        }
    }
}
