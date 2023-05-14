package com.proyectointermodular.controllers;

import com.proyectointermodular.App;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import javafx.event.ActionEvent;
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
    private Button f3;
    @FXML
    private Button c3;
    @FXML
    public MenuItem closeMenu;

    @FXML
    private void closeMenu() {
        System.exit(0);
    }

    private void changeView(String file) throws IOException {
        if (!c3.disableProperty().getValue()) {
            c3.disableProperty().setValue(true);
            c3.opacityProperty().setValue(0);
        }
        if (!f3.disableProperty().getValue()) {
            f3.disableProperty().setValue(true);
            f3.opacityProperty().setValue(0);
        }
        windowsGeneric.getChildren().clear();
        windowsGeneric.getChildren().add(FXMLLoader.load(App.class.getResource("view/" + file + ".fxml")));
    }

    public void inicio() throws IOException {
        changeView("inicio");
    }

    @FXML
    public void verFutbolistas() throws IOException {
        changeView("futbolista/verFutbolista");
        f3.disableProperty().setValue(false);
        f3.opacityProperty().setValue(1);
    }

    @FXML
    public void verClubs() throws IOException {
        changeView("club/verClubs");
        c3.disableProperty().setValue(false);
        c3.opacityProperty().setValue(1);
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
    public void verMilitados() throws IOException {
        Club c = (Club) c3.getUserData();
        Futbolista f = (Futbolista) f3.getUserData();
        System.out.println(c);
        System.out.println(f);
        //changeView("militacion/verMilitacion");
    }


}
