package com.example.officialjavafxproj;

import DataAccess.DataAccess;
import com.example.officialjavafxproj.Controller.AdminViewProductController;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private SceneController sceneSwitcher = new SceneController();

    @Override
    public void start(Stage stage) throws IOException {
        DataAccess.loadAllData();
        boolean isLoadedAll = true;
        stage.initStyle(StageStyle.UNDECORATED);
        sceneSwitcher.setCurrentScene(stage,"../Pages/adminViewProduct.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}