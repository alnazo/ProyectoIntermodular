package com.proyectointermodular.controllers.militacion;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dao.MilitacionDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import com.proyectointermodular.persistence.manager.impl.MilitacionManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.Set;

public class AddMilitacion extends MenuPrincipal {

    @FXML
    private TextField fechas;
    /* Tabla de futbolistas */
    @FXML
    private TableView<Futbolista> futbolistas;
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
    /* Tabla de clubs */
    @FXML
    private TableView<Club> clubs;
    @FXML
    private TableColumn<Club, Integer> idC;
    @FXML
    private TableColumn<Club, String> nombreC;
    @FXML
    private TableColumn<Club, Date> creacionC;
    @FXML
    private TableColumn<Club, String> estadioC;

    private Futbolista futbolistaEnlace = null;
    private Club clubEnlace = null;

    @FXML
    private void initialize() {
        nombreF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nombre"));
        apellidoF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("apellido"));
        nacimientoF.setCellValueFactory(new PropertyValueFactory<Futbolista, Date>("nacimiento"));
        nacionalidadF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nacionalidad"));
        nifF.setCellValueFactory(new PropertyValueFactory<Futbolista, String>("nif"));

        idC.setCellValueFactory(new PropertyValueFactory<Club, Integer>("id"));
        nombreC.setCellValueFactory(new PropertyValueFactory<Club, String>("nombre"));
        creacionC.setCellValueFactory(new PropertyValueFactory<Club, Date>("creacion"));
        estadioC.setCellValueFactory(new PropertyValueFactory<Club, String>("estadio"));

        Set<Futbolista> fut = new FutbolistaManagerImpl().findAll();
        ObservableList<Futbolista> obFut = FXCollections.observableArrayList(fut);
        Set<Club> clu = new ClubManagerImpl().findAll();
        ObservableList<Club> obClu = FXCollections.observableArrayList(clu);

        futbolistas.setItems(obFut);
        clubs.setItems(obClu);

        futbolistas.setOnMouseClicked(event -> {
            futbolistaEnlace = futbolistas.getSelectionModel().getSelectedItem();
        });

        clubs.setOnMouseClicked(event -> {
            clubEnlace = clubs.getSelectionModel().getSelectedItem();
        });
        
    }

    @FXML
    private void send() {
        String s_fechas = fechas.getText();

        if (verificarFechas(s_fechas)) {
            if (futbolistaEnlace != null && clubEnlace != null) {
                Militacion mil = new MilitacionManagerImpl().findByAllParams(s_fechas, futbolistaEnlace.getNif(), clubEnlace.getId());
                if (mil == null) {
                    Militacion newmili = new Militacion(s_fechas, futbolistaEnlace, clubEnlace);
                    MilitacionDAO.create(newmili);
                    PopUp.display("Militacion creada correctamente, si desea crear otra militacion,\nvuelva a seleccionar Futbolista y Club, ademas de revisar la Temporada");
                    futbolistaEnlace = null;
                    clubEnlace = null;
                } else {
                    PopUp.display("Ya existe una militacion para esta Futbolista, este Club y esta Temporada");
                }
            } else {
                PopUp.display("No se ha seleccionado ninguna Futbolista ni Club");
            }
        } else {
            PopUp.display("Las fechas indicadas no corresponde el formato yyyy/yyyy (Año/Año)\ny estos deben ser consecutivos.");
        }
    }


    private boolean verificarFechas(String fecha) {
        boolean res = false;
        if (fecha.contains("/")) {
            String[] par = fecha.split("/");
            if (par[0].length() == 4 && par[1].length() == 4) {
                int ye1 = Integer.parseInt(par[0]);
                int ye2 = Integer.parseInt(par[1]);

                res = ye2 == ye1 + 1;
            }

        }
        return res;
    }

}
