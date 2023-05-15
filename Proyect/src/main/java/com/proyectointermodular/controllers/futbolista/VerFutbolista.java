package com.proyectointermodular.controllers.futbolista;

import com.proyectointermodular.App;
import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dao.FutbolistaDAO;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class VerFutbolista extends MenuPrincipal {

    @FXML
    private TableView<Futbolista> tabla;
    @FXML
    private TableColumn<Futbolista, String> t_nif;
    @FXML
    private TableColumn<Futbolista, String> t_nombre;
    @FXML
    private TableColumn<Futbolista, String> t_apellido;
    @FXML
    private TableColumn<Futbolista, Date> t_nacimiento;
    @FXML
    private TableColumn<Futbolista, String> t_nacionalidad;

    @FXML
    private TextField nif;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField nacimiento;
    @FXML
    private TextField nacionalidad;

    MenuItem o1 = new MenuItem("Editar");
    MenuItem o2 = new MenuItem("Eliminar");

    @FXML
    public void initialize() {
        t_nombre.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
        t_apellido.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
        t_nacimiento.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
        t_nacionalidad.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));
        t_nif.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));

        FutbolistaManagerImpl search = new FutbolistaManagerImpl();
        Set<Futbolista> list = search.findAll();
        ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);
        tabla.setItems(oblist);

        tabla.setContextMenu(new ContextMenu(o1, o2));

        tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            o1.setOnAction(e -> {
                edit(newValue);
            });
            o2.setOnAction(e -> {
                eliminar(newValue);
            });
        });

        tabla.setOnMouseClicked(event -> {
            Futbolista selectFutbolista = tabla.getSelectionModel().getSelectedItem();
            windowsGeneric.getParent().getParent().lookup("#f3").setUserData(selectFutbolista);
        });

    }

    @FXML
    private void search() {
        String s_nif = this.nif.getText();
        String s_nombre = this.nombre.getText();
        String s_apellido = this.apellido.getText();
        String s_nacimiento = this.nacimiento.getText();
        String s_nacionalidad = this.nacionalidad.getText();

        if (!s_nacimiento.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_nacimiento.equals("")) {
            PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
        } else {
            t_nombre.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
            t_apellido.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
            t_nacimiento.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
            t_nacionalidad.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));
            t_nif.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));

            if (s_nif.equals("") && s_nombre.equals("") && s_apellido.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {
                initialize();
            } else if (s_nombre.equals("") && s_apellido.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {

                Set<Futbolista> list = new FutbolistaManagerImpl().findByNif(s_nif);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            } else if (s_nif.equals("") && s_apellido.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {
                Set<Futbolista> list = new FutbolistaManagerImpl().findByName(s_nombre);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            } else if (s_nif.equals("") && s_nombre.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {
                Set<Futbolista> list = new FutbolistaManagerImpl().findBySurname(s_apellido);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            } else if (s_nif.equals("") && s_nombre.equals("") && s_apellido.equals("") && s_nacionalidad.equals("")) {
                Set<Futbolista> list = new FutbolistaManagerImpl().findByDate(s_nacimiento);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            } else if (s_nif.equals("") && s_nombre.equals("") && s_apellido.equals("") && s_nacimiento.equals("")) {
                Set<Futbolista> list = new FutbolistaManagerImpl().findByNacionalidad(s_nacionalidad);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            } else {
                Map<String, String> valores = new HashMap<>() {
                    {
                        put("nombre", s_nombre);
                        put("apellido", s_apellido);
                        put("nacimiento", s_nacimiento);
                        put("nacionalidad", s_nacionalidad);
                        put("nif", s_nif);
                    }
                };

                Set<Futbolista> list = new FutbolistaManagerImpl().findByOptions(valores);
                ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);
            }
        }
    }

    public void edit(Futbolista f) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Editar Futbolista");

        Label Lnom = new Label("Nombre");
        Label Lape = new Label("Apellido");
        Label Lnac = new Label("Nacimiento");
        Label Lna = new Label("Nacionalidad");
        Label Lnif = new Label("NIF");
        TextField nom = new TextField();
        TextField ape = new TextField();
        TextField nac = new TextField();
        TextField na = new TextField();
        TextField nif = new TextField();
        Button send = new Button("Enviar");

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        VBox v1 = new VBox(10);
        VBox v2 = new VBox(10);
        VBox v3 = new VBox(10);
        VBox v4 = new VBox(10);
        VBox v5 = new VBox(10);

        nif.disableProperty().setValue(true);
        layout.getChildren().addAll(layout2, send);
        layout2.getChildren().addAll(v1, v2, v3, v4, v5);
        v1.getChildren().addAll(Lnom, nom);
        v2.getChildren().addAll(Lape, ape);
        v3.getChildren().addAll(Lnac, nac);
        v4.getChildren().addAll(Lna, na);
        v5.getChildren().addAll(Lnif, nif);

        nom.setText(f.getNombre());
        ape.setText(f.getApellido());
        nac.setText(f.getNacimiento().toString());
        na.setText(f.getNacionalidad());
        nif.setText(f.getNif());

        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        v1.setAlignment(Pos.CENTER);
        v2.setAlignment(Pos.CENTER);
        v3.setAlignment(Pos.CENTER);
        v4.setAlignment(Pos.CENTER);
        v5.setAlignment(Pos.CENTER);

        send.setOnAction(event -> {
            String s_nom = nom.getText();
            String s_ape = ape.getText();
            String s_nac = nac.getText();
            String s_na = na.getText();

            if (!s_nom.equals("") || s_nom.equals(f.getNombre())
                    && !s_ape.equals("") || s_ape.equals(f.getApellido())
                    && !s_nac.equals("") || s_nac.equals(f.getNacimiento().toString())
                    && !s_na.equals("") || s_na.equals(f.getNacionalidad())) {
                if (!s_nac.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_nac.equals("")) {
                    PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
                } else {
                    Futbolista fut = new Futbolista(s_nom, s_ape, Date.valueOf(s_nac), s_na, f.getNif());
                    new FutbolistaDAO().update(fut);
                    popupwindow.close();
                }
            } else {
                PopUp.display("No pueden haber campos vacios");
            }
        });

        Scene scene1 = new Scene(layout, 800, 250);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

        initialize();
    }

    public void eliminar(Futbolista f) {
        PopUp.delete();
        if (PopUp.delete) {
            new FutbolistaDAO().delete(f.getNif());
            initialize();
            PopUp.delete = false;
        }
    }

}
