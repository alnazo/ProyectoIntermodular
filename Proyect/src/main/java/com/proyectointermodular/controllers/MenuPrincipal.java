package com.proyectointermodular.controllers;

import com.proyectointermodular.App;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manageDDBB.ChangeData;
import com.proyectointermodular.persistence.manageDDBB.ImportScheme;
import com.proyectointermodular.popup.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;

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

    /**
     * Metodo para el cierre de la aplicación.
     */
    @FXML
    private void closeMenu() {
        System.exit(0);
    }

    /**
     * Metodo para el cambio de visualizacion dentro del cuadro general de la aplicación.
     *
     * @param file Sub-ruta del fichero dentro de la carpeta organizadora de las visualizaciones.
     * @throws IOException Excepción en caso de que el {@param file} no exista el acceso.
     */
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

    /**
     * Cambio de visualizacion a Inicio.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void inicio() throws IOException {
        changeView("inicio");
    }

    /**
     * Cambio de visualizacion a Lista de Futbolistas.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void verFutbolistas() throws IOException {
        changeView("futbolista/verFutbolista");
        if (f3 != null) {
            f3.disableProperty().setValue(false);
            f3.opacityProperty().setValue(1);
        }
    }

    /**
     * Cambio de visualizacion a Lista de Clubs.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void verClubs() throws IOException {
        changeView("club/verClubs");
        c3.disableProperty().setValue(false);
        c3.opacityProperty().setValue(1);
    }

    /**
     * Cambio de visualizacion a Adicion de nueva Futbolista.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void addPlayer() throws IOException {
        changeView("futbolista/addFutbolista");
    }

    /**
     * Cambio de visualizacion a Adicion de nuevo Club.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void addClub() throws IOException {
        changeView("club/addClub");
    }

    /**
     * Cambio de visualizacion a Lista de Militacion desde Futbolista.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void verMilitadosF() throws IOException {
        changeView("militacion/verMilitacion");
        windowsGeneric.lookup("#cdc").disableProperty().setValue(true);
    }

    /**
     * Cambio de visualizacion a Lista de Militacion desde Club.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void verMilitadosC() throws IOException {
        changeView("militacion/verMilitacion");
        windowsGeneric.lookup("#cdf").disableProperty().setValue(true);
    }

    /**
     * Cambio de visualizacion a Adicion de Militacion.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    public void addMilitacion() throws IOException {
        changeView("militacion/addMilitacion");
    }

    /**
     * Apertura del PopUp About.
     *
     * @throws IOException Excepción en caso de que no exista el acceso.
     */
    @FXML
    private void about() {
        PopUp.about();
    }

    /**
     * Metodo de Importacion a Base de Datos del esquema de la base de datos limpia.
     */
    @FXML
    private void loadDB() {
        ImportScheme.inport();
    }

    /**
     * Herramienta de cambio de conexión de la Base de Datos.
     */
    @FXML
    private void changeDB() {
        new ChangeData().inicio();
    }

}
