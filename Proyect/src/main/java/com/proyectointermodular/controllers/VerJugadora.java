package com.proyectointermodular.controllers;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class VerJugadora extends MenuPrincipal {

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

    @FXML
    public void initialize() {
        t_nombre.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
        t_apellido.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
        t_nacimiento.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
        t_nacionalidad.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));
        t_nif.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));

        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getMySQLConnection();

            FutbolistaManagerImpl search = new FutbolistaManagerImpl();
            List<Futbolista> list = search.findAll(con);
            ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

            tabla.setItems(oblist);

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void search() {
        String s_nif = this.nif.getText();
        String s_nombre = this.nombre.getText();
        String s_apellido = this.apellido.getText();
        String s_nacimiento = this.nacimiento.getText();
        String s_nacionalidad = this.nacionalidad.getText();

        if(!s_nacimiento.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_nacimiento.equals("")) {
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
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findByNif(con, s_nif);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else if (s_nif.equals("") && s_apellido.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findByName(con, s_nombre);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else if (s_nif.equals("") && s_nombre.equals("") && s_nacimiento.equals("") && s_nacionalidad.equals("")) {
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findBySurname(con, s_apellido);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else if (s_nif.equals("") && s_nombre.equals("") && s_apellido.equals("") && s_nacionalidad.equals("")) {
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findByDate(con, s_nacimiento);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else if (s_nif.equals("") && s_nombre.equals("") && s_apellido.equals("") && s_nacimiento.equals("")) {
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findByNacionalidad(con, s_nacionalidad);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MySQLConnector connector = new MySQLConnector();
                    Connection con = connector.getMySQLConnection();

                    Map<String, String> valores = new HashMap<>() {
                        {
                            put("nombre", s_nombre);
                            put("apellido", s_apellido);
                            put("nacimiento", s_nacimiento);
                            put("nacionalidad", s_nacionalidad);
                            put("nif", s_nif);
                        }
                    };

                    FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                    Set<Futbolista> list = search.findByOptions(con, valores);
                    ObservableList<Futbolista> oblist = FXCollections.observableArrayList(list);

                    tabla.setItems(oblist);

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
