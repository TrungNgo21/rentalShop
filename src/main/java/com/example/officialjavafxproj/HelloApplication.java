package com.example.officialjavafxproj;

import DataAccess.DataAccess;
import com.example.officialjavafxproj.Controller.AdminViewProductController;
import com.example.officialjavafxproj.Utils.SceneController;

import com.example.officialjavafxproj.Utils.ToastBuilder;
import com.github.plushaze.traynotification.animations.Animations;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private SceneController sceneSwitcher = new SceneController();

    @Override
    public void start(Stage stage) throws IOException {
        DataAccess.loadAllData();
        boolean isLoadedAll = true;
        stage.initStyle(StageStyle.UNDECORATED);
        sceneSwitcher.setCurrentScene(stage,"../hello-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}