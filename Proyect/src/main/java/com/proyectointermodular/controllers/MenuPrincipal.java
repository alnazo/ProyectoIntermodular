package com.proyectointermodular.controllers;

import com.proyectointermodular.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuPrincipal {
    @FXML
    private Button addPlayer;
    @FXML
    private Button addClub;
    @FXML
    private Button verJugadoras;
    @FXML
    private Button verClubs;

    @FXML
    private void addPlayer() throws IOException {
        App.setRoot("addPlayer");
    }

    @FXML
    private void addClub() throws IOException{
        App.setRoot("addClub");
    }

    @FXML
    private void verJugadoras() throws IOException {
        App.setRoot("verJugadoras");
    }

    @FXML
    private void verClubs() throws IOException {
        App.setRoot("verClubs");
    }


}
