package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.App;
import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dao.MilitacionDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import com.proyectointermodular.persistence.manager.impl.MilitacionManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
                if (futboli != null) {
                    edit(newValue, futboli);
                }
                if (club != null) {
                    edit(newValue, club);
                }
            });
            o2.setOnAction(e -> {
                if (futboli != null) {
                    eliminar(newValue, futboli);
                }
                if (club != null) {
                    eliminar(newValue, club);
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
        } else if (futboli != null) {
            text.setText("Clubes militados de:\n(" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByFutbolista(futboli);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Clubs en los que haya militado: (" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());
            }
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
        } else if (club != null) {
            text.setText("Futbolistas que mititan en:\n" + club.getNombre());
            Set<Militacion> list = new MilitacionManagerImpl().findByClub(club);
            ObservableList<Militacion> oblist = FXCollections.observableArrayList(list);
            if (list.size() > 0) {
                tabla.setItems(oblist);
            } else {
                PopUp.display("No hay Futbolistas que hayan militado en el " + club.getNombre());
            }
        } else {
            PopUp.display("No se ha seleccionado ningun Club en el listado");
        }
        nombreC.visibleProperty().setValue(false);
        creacionC.visibleProperty().setValue(false);
        estadioC.visibleProperty().setValue(false);
    }

    private void edit(Militacion mil, Futbolista fut) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Editar Militaciones de: (" + futboli.getNif() + ") " + futboli.getApellido() + ", " + futboli.getNombre());

        Label Ltemp = new Label("Temporada");
        TextField Ttemp = new TextField();

        TableView<Club> tab = new TableView<>(FXCollections.observableArrayList(new ClubManagerImpl().findAll()));

        TableColumn<Club, String> t_club = new TableColumn<>("Club");
        TableColumn<Club, Date> t_creacion = new TableColumn<>("Fecha de creacion");
        TableColumn<Club, String> t_estadio = new TableColumn<>("Estadio");
        t_club.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        t_creacion.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        t_estadio.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        tab.getColumns().setAll(t_club, t_creacion, t_estadio);

        Button send = new Button("Enviar");

        tab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            send.setOnAction(event -> {
                String s_temp = Ttemp.getText();
                if (AddMilitacion.verificarFechas(s_temp)) {
                    Militacion newmilit = new Militacion(s_temp, mil.getNif_Futbolista(), newValue);
                    Militacion search = new MilitacionManagerImpl().findByAllParams(newmilit.getTemporada(), newmilit.getNif_Futbolista().getNif(), newmilit.getId_Club().getId());
                    if (!newmilit.equals(mil) && search == null) {
                        new MilitacionDAO().updateF(newmilit);
                        PopUp.display("Militacion editada correctamente.");
                        popupwindow.close();
                    } else {
                        PopUp.display("La militacion anterior y la nueva coinciden, por favor, revise bien los datos.");
                    }

                } else {
                    PopUp.display("Las fechas indicadas no corresponde el formato yyyy/yyyy (Año/Año)\ny estos deben ser consecutivos.");
                }

            });
        });

        VBox layout = new VBox(10);
        HBox layout1 = new HBox(10);

        layout1.getChildren().addAll(Ltemp, Ttemp, send);
        layout.getChildren().addAll(layout1, tab);

        Scene scene1 = new Scene(layout, 800, 400);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    private void edit(Militacion mil, Club clu) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Editar Militaciones del club: " + clu.getNombre());

        Label Ltemp = new Label("Temporada");
        TextField Ttemp = new TextField();

        TableView<Futbolista> tab = new TableView<>(FXCollections.observableArrayList(new FutbolistaManagerImpl().findAll()));

        TableColumn<Futbolista, String> t_nif = new TableColumn<>("NIF");
        TableColumn<Futbolista, String> t_nombre = new TableColumn<>("Nombre");
        TableColumn<Futbolista, String> t_apellido = new TableColumn<>("Apellido");
        TableColumn<Futbolista, Date> t_nacimiento = new TableColumn<>("Fecha de Nacimiento");
        TableColumn<Futbolista, String> t_nacionalidad = new TableColumn<>("Nacionalidad");
        t_nif.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));
        t_nombre.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
        t_apellido.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
        t_nacimiento.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
        t_nacionalidad.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));

        tab.getColumns().setAll(t_nif, t_nombre, t_apellido, t_nacimiento, t_nacionalidad);

        Button send = new Button("Enviar");

        tab.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            send.setOnAction(event -> {
                String s_temp = Ttemp.getText();
                if (AddMilitacion.verificarFechas(s_temp)) {
                    Militacion newmilit = new Militacion(s_temp, newValue, mil.getId_Club());
                    Militacion search = new MilitacionManagerImpl().findByAllParams(newmilit.getTemporada(), newmilit.getNif_Futbolista().getNif(), newmilit.getId_Club().getId());
                    if (!newmilit.equals(mil) && search == null) {
                        new MilitacionDAO().updateC(newmilit);
                        PopUp.display("Militacion editada correctamente.");
                        popupwindow.close();
                    } else {
                        PopUp.display("La militacion anterior y la nueva coinciden, por favor, revise bien los datos.");
                    }

                } else {
                    PopUp.display("Las fechas indicadas no corresponde el formato yyyy/yyyy (Año/Año)\ny estos deben ser consecutivos.");
                }

            });
        });

        VBox layout = new VBox(10);
        HBox layout1 = new HBox(10);

        layout1.getChildren().addAll(Ltemp, Ttemp, send);
        layout.getChildren().addAll(layout1, tab);

        Scene scene1 = new Scene(layout, 800, 400);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    private void eliminar(Militacion mil, Futbolista fut) {
        PopUp.delete();
        if (PopUp.delete) {
            new MilitacionDAO().delete(mil);
            futboli = fut;
            PopUp.delete = false;
            futbolistaMilita();
        }
    }

    private void eliminar(Militacion mil, Club clu) {
        PopUp.delete();
        if (PopUp.delete) {
            new MilitacionDAO().delete(mil);
            club = clu;
            PopUp.delete = false;
            clubsMilita();
        }
    }

}
