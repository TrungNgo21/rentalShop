package com.example.officialjavafxproj;

import DataAccess.DataAccess;
import com.example.officialjavafxproj.Utils.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private SceneController sceneSwitcher = new SceneController();

    @Override
    public void start(Stage stage) throws IOException {
        DataAccess.loadAllData();
        stage.initStyle(StageStyle.UNDECORATED);
        sceneSwitcher.setCurrentScene(stage,"../Pages/homepageAdmin.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}