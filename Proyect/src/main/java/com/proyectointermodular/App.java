package com.proyectointermodular;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(FXMLLoader.load(App.class.getResource("view/menuprincipal.fxml")), 800, 600);
        scene.getStylesheets().add("com/proyectointermodular/css/stiles.css");

        stage.setMaximized(true);
        stage.setTitle("Control de Futbolistas y Clubes Femenilo Liga");
        stage.setScene(scene);
        stage.show();
    }
    public static void closeProgram(){
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }

}