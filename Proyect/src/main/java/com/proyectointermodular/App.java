package com.proyectointermodular;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(App.class.getResource("view/menuprincipal.fxml")), 800, 600);
        scene.getStylesheets().add("com/proyectointermodular/css/stiles.css");
        stage.setMinHeight(600.0);
        stage.setMinWidth(800.0);

        stage.getIcons().add(getIcon());
        stage.setTitle("Control de Futbolistas y Clubes Femenilo Liga");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Icono de la aplicación.
     *
     * @return {@link Image}
     */
    public static Image getIcon() {
        return new Image("com/proyectointermodular/app/icon.png");
    }

    public static void main(String[] args) {
        launch();
    }

}