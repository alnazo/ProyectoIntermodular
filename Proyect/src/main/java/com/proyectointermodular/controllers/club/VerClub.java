package com.proyectointermodular.controllers.club;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.controllers.militacion.VerMilitacion;
import com.proyectointermodular.dao.ClubDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
    SeparatorMenuItem sep = new SeparatorMenuItem();

    @FXML
    public void initialize() {
        t_id.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        t_nombre.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        t_creacion.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        t_estadio.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        Set<Club> list = new ClubManagerImpl().findAll();
        ObservableList<Club> oblist = FXCollections.observableArrayList(list);

        tabla.setItems(oblist);

        tabla.setContextMenu(new ContextMenu(c3, sep, o1, o2));

        tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            o1.setOnAction(e -> {
                edit(newValue);
            });
            o2.setOnAction(e -> {
                eliminar(newValue);
            });
            c3.setOnAction(e -> {
                verMilitados(newValue);
            });
        });

    }

    private void verMilitados(Club e){
        try {
            verMilitacion();
            new VerMilitacion().futbolistaMilita(e);
        } catch (IOException ex){
            ex.printStackTrace();
        }
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

        tabla.refresh();
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
