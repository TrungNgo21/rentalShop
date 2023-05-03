
package com.example.officialjavafxproj.Utils;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Parent root;
    private Scene scene;
    private Stage stage;

    private double xOffset;
    private double yOffset;

    private void draggable(Parent root, Stage stage){
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() - xOffset);
                stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });
    }

    public void switchScene(Event event, String pathToView) throws IOException {
        root = FXMLLoader.load(getClass().getResource(pathToView));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        draggable(root, stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Parent getComponentScene(Parent container, String pathToComponent) throws IOException {
        FXMLLoader componentLoader = new FXMLLoader();
        componentLoader.setLocation(getClass().getResource(pathToComponent));
        container = componentLoader.load();
        return container;
    }

    public Object getComponentController(String pathToComponent){
        FXMLLoader componentLoader = new FXMLLoader();
        componentLoader.setLocation(getClass().getResource(pathToComponent));
        return componentLoader.getController();
    }

    public void setCurrentScene(Stage stage, String pathToView) throws IOException{
        root = FXMLLoader.load(getClass().getResource(pathToView));
        draggable(root, stage);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

