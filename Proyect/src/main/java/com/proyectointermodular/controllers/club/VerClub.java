package com.proyectointermodular.controllers.club;

import com.proyectointermodular.App;
import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.controllers.militacion.VerMilitacion;
import com.proyectointermodular.dao.ClubDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VerClub extends MenuPrincipal {
    @FXML
    private TableView<Club> tabla;

    @FXML
    private TableColumn<Club, Integer> t_id;

    @FXML
    private TableColumn<Club, String> t_nombre;

    @FXML
    private TableColumn<Club, Date> t_creacion;

    @FXML
    private TableColumn<Club, String> t_estadio;

    @FXML
    private TextField id;

    @FXML
    private TextField nombre;

    @FXML
    private TextField creacion;

    @FXML
    private TextField estadio;

    MenuItem o1 = new MenuItem("Editar");
    MenuItem o2 = new MenuItem("Eliminar");

    @FXML
    public void initialize() {
        t_id.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        t_nombre.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        t_creacion.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        t_estadio.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        Set<Club> list = new ClubManagerImpl().findAll();
        ObservableList<Club> oblist = FXCollections.observableArrayList(list);

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
            Club selectClub = tabla.getSelectionModel().getSelectedItem();
            super.club = true;
            windowsGeneric.getParent().getParent().lookup("#c3").setOnMouseClicked(mouseEvent -> {
                new VerMilitacion().futbolistaMilita(selectClub);
            });
        });
    }

    @FXML
    private void search() {
        String s_id = this.id.getText();
        String s_nombre = this.nombre.getText();
        String s_creacion = this.creacion.getText();
        String s_estadio = this.estadio.getText();

        if (!s_creacion.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_creacion.equals("")) {
            PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
        } else {
            t_id.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
            t_nombre.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
            t_creacion.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
            t_estadio.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

            if (s_id.equals("") && s_nombre.equals("") && s_creacion.equals("") && s_estadio.equals("")) {
                initialize();
            } else if (s_nombre.equals("") && s_creacion.equals("") && s_estadio.equals("")) {

                Set<Club> list = new HashSet<>();
                list.add(new ClubManagerImpl().findById(Integer.parseInt(s_id)));
                ObservableList<Club> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);

            } else if (s_id.equals("") && s_creacion.equals("") && s_estadio.equals("")) {

                Set<Club> list = new ClubManagerImpl().findByName(s_nombre);
                ObservableList<Club> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);

            } else if (s_id.equals("") && s_nombre.equals("") && s_estadio.equals("")) {

                Set<Club> list = new ClubManagerImpl().findByDate(s_creacion);
                ObservableList<Club> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);

            } else if (s_id.equals("") && s_nombre.equals("") && s_creacion.equals("")) {

                Set<Club> list = new ClubManagerImpl().findByEstadio(s_estadio);
                ObservableList<Club> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);

            } else {
                Map<String, String> val = new HashMap<>() {
                    {
                        put("id", s_id);
                        put("nombre", s_nombre);
                        put("creacion", s_creacion);
                        put("estadio", s_estadio);
                    }
                };
                Set<Club> list = new ClubManagerImpl().findByOptions(val);
                ObservableList<Club> oblist = FXCollections.observableArrayList(list);

                tabla.setItems(oblist);

            }
        }

    }

    public void edit(Club c) {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Editar Club");

        Label Lnom = new Label("Nombre");
        Label Lcre = new Label("Año fundacion");
        Label Lest = new Label("Estadio");
        TextField nom = new TextField();
        TextField cre = new TextField();
        TextField est = new TextField();
        Button send = new Button("Enviar");

        nom.setText(c.getNombre());
        cre.setText(c.getCreacion().toString());
        est.setText(c.getEstadio());

        VBox layout = new VBox(10);
        HBox layout2 = new HBox(10);
        VBox v1 = new VBox(10);
        VBox v2 = new VBox(10);
        VBox v3 = new VBox(10);

        layout.getChildren().addAll(layout2, send);
        layout2.getChildren().addAll(v1, v2, v3);
        v1.getChildren().addAll(Lnom, nom);
        v2.getChildren().addAll(Lcre, cre);
        v3.getChildren().addAll(Lest, est);

        layout.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);
        v1.setAlignment(Pos.CENTER);
        v2.setAlignment(Pos.CENTER);
        v3.setAlignment(Pos.CENTER);

        send.setOnAction(event -> {
            String s_nom = nom.getText();
            String s_cre = cre.getText();
            String s_est = est.getText();
            if (!s_nom.equals("") || s_nom.equals(c.getNombre())
                    && !s_cre.equals("") || s_cre.equals(c.getCreacion().toString())
                    && !s_est.equals("") || s_est.equals(c.getEstadio())) {
                if (!s_cre.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_cre.equals("")) {
                    PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
                } else {
                    Club cl = new Club(c.getId(), s_nom, Date.valueOf(s_cre), s_est);
                    new ClubDAO().update(cl);
                    popupwindow.close();
                }
            } else {
                PopUp.display("No pueden haber campos vacios");
            }
        });

        Scene scene1 = new Scene(layout, 600, 250);
        popupwindow.getIcons().add(App.getIcon());
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

        initialize();

    }

    public void eliminar(Club c) {
        PopUp.delete();
        if (PopUp.delete) {
            new ClubDAO().delete(c.getId());
            initialize();
            PopUp.delete = false;
        }
    }

}
