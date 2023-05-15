package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.manager.impl.MilitacionManagerImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.Date;
import java.util.Set;

public class VerMilitacion extends MenuPrincipal {
    @FXML
    private Text text;
    @FXML
    private TableView<Militacion> tabla;
    @FXML
    private TableColumn<Militacion, String> temp;
    /**
     * Informacion Futbolsta Milita
     */
    @FXML
    private TableColumn<Militacion,Futbolista> futbolista;
    @FXML
    private TableColumn<Futbolista, String> nifF;
    @FXML
    private TableColumn<Futbolista, String> nombreF;
    @FXML
    private TableColumn<Futbolista, String> apellidoF;
    @FXML
    private TableColumn<Futbolista, Date> nacimientoF;
    @FXML
    private TableColumn<Futbolista, String> nacionalidadF;
    /**
     * Información Club Milita
     */
    @FXML
    private TableColumn<Militacion,Club> club;
    @FXML
    private TableColumn<Club, String> nombreC;
    @FXML
    private TableColumn<Club, Date> creacionC;
    @FXML
    private TableColumn<Club, String> estadioC;

    @FXML
    public void initialize() {
        temp.setCellValueFactory(new PropertyValueFactory<Militacion, String>("temporada"));

        nombreF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue();
            return new SimpleStringProperty(futbolista.getNombre());
        });
        apellidoF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue();
            return new SimpleStringProperty(futbolista.getApellido());
        });
        nacimientoF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue();
            return new SimpleStringProperty(futbolista.getNacimientoString());
        });
        nacionalidadF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue();
            return new SimpleStringProperty(futbolista.getNacionalidad());
        });
        nifF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue();
            return new SimpleStringProperty(futbolista.getNif());
        });

        nombreC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue();
            return new SimpleStringProperty(club.getNombre());
        });
        creacionC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue();
            return new SimpleStringProperty(club.getCreacionString());
        });
        estadioC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue();
            return new SimpleStringProperty(club.getEstadio());
        });
    }

    @FXML
    public void futbolistaMilita() {
        Futbolista futbolista = (Futbolista) windowsGeneric.getParent().getParent().lookup("#f3").getUserData();
        text.setText("Clubes militados de: " + futbolista.getNombre() + " - " + futbolista.getNif());

        Set<Militacion> list = new MilitacionManagerImpl().findByFutbolista(futbolista);
        System.out.println(list);
        ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
        tabla.setItems(oblist);

        nombreF.visibleProperty().setValue(false);
        apellidoF.visibleProperty().setValue(false);
        nacimientoF.visibleProperty().setValue(false);
        nacionalidadF.visibleProperty().setValue(false);
        nifF.visibleProperty().setValue(false);

    }

    @FXML
    public void clubsMilita() {
        Club club = (Club) windowsGeneric.getParent().getParent().lookup("#c3").getUserData();
        text.setText("Futbolistas que mititan en: " + club.getNombre());

    }


}
