package com.proyectointermodular.controllers;

import java.io.IOException;

import com.proyectointermodular.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("view/secondary");
    }
}
