package com.proyectointermodular.controllers;

import com.proyectointermodular.App;
import com.proyectointermodular.popup.PopUp;
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
    public Button verFutbolistas;
    @FXML
    public Button verClubs;
    @FXML
    public Button f3;
    @FXML
    public Button c3;
    @FXML
    public MenuItem closeMenu;

    @FXML
    private void initialize() {

    }

    @FXML
    private void closeMenu() {
        System.exit(0);
    }

    private void changeView(String file) throws IOException {
        if (f3 != null && c3 != null) {
            if (!c3.disableProperty().getValue()) {
                c3.disableProperty().setValue(true);
                c3.opacityProperty().setValue(0);
            }

            if (!f3.disableProperty().getValue()) {
                f3.disableProperty().setValue(true);
                f3.opacityProperty().setValue(0);
            }
        }

        windowsGeneric.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/" + file + ".fxml"));
        GridPane newPanel = loader.load();
        windowsGeneric.getChildren().add(newPanel);
    }

    @FXML
    public void inicio() throws IOException {
        changeView("inicio");
    }

    @FXML
    public void verFutbolistas() throws IOException {
        changeView("futbolista/verFutbolista");
        if(f3 != null) {
            f3.disableProperty().setValue(false);
            f3.opacityProperty().setValue(1);
        }
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
    public void verMilitadosF() throws IOException {
        changeView("militacion/verMilitacion");
        windowsGeneric.lookup("#cdc").disableProperty().setValue(true);
    }

    @FXML
    public void verMilitadosC() throws IOException {
        changeView("militacion/verMilitacion");
        windowsGeneric.lookup("#cdf").disableProperty().setValue(true);
    }

    @FXML
    public void addMilitacion() throws IOException {
        changeView("militacion/addMilitacion");
    }

    @FXML
    private void about(){
        PopUp.about();
    }

}
