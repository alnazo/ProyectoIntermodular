package com.proyectointermodular.controllers;

import com.proyectointermodular.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class MenuPrincipal {
    @FXML
    public GridPane windowsGeneric;
    @FXML
    public Button addPlayer;
    @FXML
    public Button addClub;
    @FXML
    public Button verJugadoras;
    @FXML
    public Button verClubs;
    @FXML
    public MenuItem closeMenu;

    @FXML
    public MenuItem f3 = new MenuItem("Clubs militados");
    @FXML
    public MenuItem c3 = new MenuItem("Jugadoras militan");

    @FXML
    private void closeMenu() {
        System.exit(0);
    }

    private void changeView(String file) throws IOException {
        windowsGeneric.getChildren().clear();
        windowsGeneric.getChildren().add(FXMLLoader.load(App.class.getResource("view/"+ file + ".fxml")));
    }

    public void inicio() throws IOException {
        changeView("inicio");
    }

    @FXML
    public void verJugadoras() throws IOException{
        changeView("futbolista/verFutbolista");
    }

    @FXML
    public void verClubs() throws IOException {
        changeView("club/verClubs");
    }

    @FXML
    public void addPlayer() throws IOException {
        changeView("futbolista/addFutbolista");
    }

    @FXML
    public void addClub() throws IOException {
        changeView("club/addClub");
    }

    @FXML
    public void verMilitacion() throws IOException {
        changeView("militacion/verMilitacion");
    }


}
