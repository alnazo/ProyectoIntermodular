package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Club;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    public void futbolistaMilita(Club c){
        text.setText("Jugadoras que milita");
        nombreC.setVisible(false);
        creacionC.setVisible(false);
        estadioC.setVisible(false);




    }

    @FXML
    public void clubsMilita(Futbolista f){
        text.setText("Clubs que milita");
        nifF.setVisible(false);
        nombreF.setVisible(false);
        apellidoF.setVisible(false);
        nacimientoF.setVisible(false);
        nacionalidadF.setVisible(false);




    }




}
