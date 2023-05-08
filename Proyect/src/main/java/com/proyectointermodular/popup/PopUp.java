package com.proyectointermodular.popup;

import com.proyectointermodular.controllers.MenuPrincipal;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {
    public static void display(String msg) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Aviso!");

        Label label1 = new Label(msg);

        Button button1 = new Button("Cerrar mensaje");
        button1.setOnAction(e -> popupwindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static void extra(String tipo) {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Aviso!");

        String msg = "¿Quieres añadir otro club?";
        if(tipo.equals("jugadora")){
            msg = "¿Quieres añadir otra jugadora?";
        }

        Label label1 = new Label(msg);

        Button button1 = new Button("Si");
        Button button2 = new Button("No");

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);

        layout.getChildren().addAll(label1, layout2);
        layout2.getChildren().addAll(button1, button2);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
}
