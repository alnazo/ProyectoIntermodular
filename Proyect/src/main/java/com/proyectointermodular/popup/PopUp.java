package com.proyectointermodular.popup;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
}
