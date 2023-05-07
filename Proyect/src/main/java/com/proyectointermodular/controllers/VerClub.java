package com.proyectointermodular.controllers;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

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

    @FXML
    public void initialize() {
        t_id.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        t_nombre.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        t_creacion.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        t_estadio.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        try {
            MySQLConnector connector = new MySQLConnector();
            Connection con = connector.getMySQLConnection();

            ClubManagerImpl search = new ClubManagerImpl();
            List<Club> list = search.findAll(con);
            ObservableList<Club> oblist = FXCollections.observableArrayList(list);

            tabla.setItems(oblist);

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void search() {
        String s_id = this.id.getText();
        String s_nombre = this.nombre.getText();
        String s_creacion = this.creacion.getText();
        String s_estadio = this.estadio.getText();

        if(!s_creacion.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_creacion.equals("")){
            PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
        } else {
            if(s_id.equals("") && s_nombre.equals("") && s_creacion.equals("") && s_estadio.equals("")) {
                initialize();
            }
            //TODO -- Casos de campos vacios
        }

    }


}
