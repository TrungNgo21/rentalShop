package com.example.officialjavafxproj.Controller;

import FileLocation.FileLocation;
import com.example.officialjavafxproj.Utils.FileController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;

public class TestingController{
    @FXML
    private Label messageLabel;

    @FXML
    private Button dialogButton;

    public void onDialogButton(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String ext = FileController.getFileExtension(file);
        File targetFile = new File( FileLocation.getImageDir() + "Public/" + "1." + ext);
        if(file != null){
            FileController.uploadFile(targetFile, file, 400, 400);
            messageLabel.setText(file.getAbsolutePath() + " selected");
        }else{
            messageLabel.setText("No file Choosen");
        }

    }
}
