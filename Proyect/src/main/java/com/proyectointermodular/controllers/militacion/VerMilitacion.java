package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.Date;

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
    private TableColumn<Club, String> nombreC;
    @FXML
    private TableColumn<Club, Date> creacionC;
    @FXML
    private TableColumn<Club, String> estadioC;

    @FXML
    public void initialize(){
        nombreF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
        apellidoF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
        nacimientoF.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
        nacionalidadF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));
        nifF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));
        nombreC.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        creacionC.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        estadioC.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        if (super.club){
            nifF.setVisible(false);
            nombreF.setVisible(false);
            apellidoF.setVisible(false);
            nacimientoF.setVisible(false);
            nacionalidadF.setVisible(false);
            super.club = false;
        } else {
            nombreC.setVisible(false);
            creacionC.setVisible(false);
            estadioC.setVisible(false);
        }


    }
    @FXML
    public void futbolistaMilita(Club c){
        System.out.println(c);



    }

    @FXML
    public void clubsMilita(Futbolista f){
        System.out.println(f);


    }




}
