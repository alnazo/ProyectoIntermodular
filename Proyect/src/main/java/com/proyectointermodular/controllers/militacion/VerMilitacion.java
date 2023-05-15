package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dao.FutbolistaDAO;
import com.proyectointermodular.dao.MilitacionDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.manager.impl.MilitacionManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
    private TableColumn<Militacion, String> nifF;
    @FXML
    private TableColumn<Militacion, String> nombreF;
    @FXML
    private TableColumn<Militacion, String> apellidoF;
    @FXML
    private TableColumn<Militacion, Date> nacimientoF;
    @FXML
    private TableColumn<Militacion, String> nacionalidadF;
    /**
     * Información Club Milita
     */
    @FXML
    private TableColumn<Militacion, String> nombreC;
    @FXML
    private TableColumn<Militacion, Date> creacionC;
    @FXML
    private TableColumn<Militacion, String> estadioC;
    private Futbolista futboli;
    private Club club;
    MenuItem o1 = new MenuItem("Editar");
    MenuItem o2 = new MenuItem("Eliminar");

    @FXML
    public void initialize() {
        temp.setCellValueFactory(new PropertyValueFactory<Militacion, String>("temporada"));
        /* Asignacion de valor a columnas de Futbolista */
        nombreF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue().getNif_Futbolista();
            return new SimpleStringProperty(futbolista.getNombre());
        });
        apellidoF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue().getNif_Futbolista();
            return new SimpleStringProperty(futbolista.getApellido());
        });
        nacimientoF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue().getNif_Futbolista();
            return new SimpleObjectProperty<>(futbolista.getNacimiento());
        });
        nacionalidadF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue().getNif_Futbolista();
            return new SimpleStringProperty(futbolista.getNacionalidad());
        });
        nifF.setCellValueFactory(cellData -> {
            Futbolista futbolista = cellData.getValue().getNif_Futbolista();
            return new SimpleStringProperty(futbolista.getNif());
        });
        /* Asignacion de valor a columnas de Club */
        nombreC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue().getId_Club();
            return new SimpleStringProperty(club.getNombre());
        });
        creacionC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue().getId_Club();
            return new SimpleObjectProperty<>(club.getCreacion());
        });
        estadioC.setCellValueFactory(cellData -> {
            Club club = cellData.getValue().getId_Club();
            return new SimpleStringProperty(club.getEstadio());
        });

        tabla.setContextMenu(new ContextMenu(o1, o2));
        tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            o1.setOnAction(e -> {
                if(futboli != null){
                    edit(newValue, futboli);
                    futboli = null;
                }
                if(club != null){
                    edit(newValue, club);
                    futboli = null;
                }
            });
            o2.setOnAction(e -> {
                if(futboli != null){
                    eliminar(newValue, futboli);
                    futboli = null;
                }
                if(club != null){
                    eliminar(newValue, club);
                    futboli = null;
                }
            });
        });

    }

    @FXML
    public void futbolistaMilita() {
        if (windowsGeneric.getParent().getParent().lookup("#f3").getUserData() != null) {
            Futbolista futboli = (Futbolista) windowsGeneric.getParent().getParent().lookup("#f3").getUserData();
            this.futboli = futboli;
            text.setText("Clubes militados de:\n(" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByFutbolista(futboli);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Clubs en los que haya militado: (" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            }
            windowsGeneric.getParent().getParent().lookup("#f3").setUserData(null);
        } else if(futboli != null) {
            text.setText("Clubes militados de:\n(" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByFutbolista(futboli);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Clubs en los que haya militado: (" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            }
            futboli = null;
        } else {
            PopUp.display("No se ha seleccionado ninguna Futbolista en el listado");
        }
        nombreF.visibleProperty().setValue(false);
        apellidoF.visibleProperty().setValue(false);
        nacimientoF.visibleProperty().setValue(false);
        nacionalidadF.visibleProperty().setValue(false);
        nifF.visibleProperty().setValue(false);

    }

    @FXML
    public void clubsMilita() {
        if (windowsGeneric.getParent().getParent().lookup("#c3").getUserData() != null) {
            Club club = (Club) windowsGeneric.getParent().getParent().lookup("#c3").getUserData();
            this.club = club;
            text.setText("Futbolistas que mititan en:\n" + club.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByClub(club);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Futbolistas que hayan militado en el " + club.getNombre());
            }
            windowsGeneric.getParent().getParent().lookup("#c3").setUserData(null);
        } else if(club != null){
            text.setText("Futbolistas que mititan en:\n" + club.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByClub(club);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Futbolistas que hayan militado en el " + club.getNombre());
            }
            club = null;
        } else {
            PopUp.display("No se ha seleccionado ningun Club en el listado");
        }
        nombreC.visibleProperty().setValue(false);
        creacionC.visibleProperty().setValue(false);
        estadioC.visibleProperty().setValue(false);
    }

    private void edit(Militacion mil, Futbolista fut){
        System.out.println(mil);
        System.out.println(fut);
    }

    private void edit(Militacion mil, Club clu){
        System.out.println(mil);
        System.out.println(clu);
    }

    private void eliminar(Militacion mil, Futbolista fut){
        PopUp.delete();
        if (PopUp.delete) {
            new MilitacionDAO().delete(mil);
            futboli = fut;
            PopUp.delete = false;
            futbolistaMilita();
        }
    }

    private void eliminar(Militacion mil, Club clu){
        PopUp.delete();
        if (PopUp.delete) {
            new MilitacionDAO().delete(mil);
            club = clu;
            PopUp.delete = false;
            clubsMilita();
        }
    }

}
