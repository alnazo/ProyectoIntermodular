package com.proyectointermodular.controllers;

import com.proyectointermodular.dao.FutbolistaDAO;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


public class AddJugadora extends MenuPrincipal{

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField nacimiento;
    @FXML
    private TextField nacionalidad;
    @FXML
    private TextField nif;

    @FXML
    private void enviar(){
        String s_nif = this.nif.getText();
        String s_nombre = this.nombre.getText();
        String s_apellido = this.apellido.getText();
        String s_nacimiento = this.nacimiento.getText();
        String s_nacionalidad = this.nacionalidad.getText();

        if(!s_nacimiento.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_nacimiento.equals("")) {
            PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");

        } else if(!s_nombre.equals("") && !s_apellido.equals("") && !s_nacimiento.equals("") && !s_nacionalidad.equals("") && !s_nif.equals("")){
            if(!comporbar_nif(s_nif)) {
                PopUp.display("El NIF que ha introducio no es correcto.");
            } else {
                Date nac = Date.valueOf(s_nacimiento);
                Futbolista jugadora = new Futbolista(s_nombre, s_apellido, nac, s_nacionalidad, s_nif);

                FutbolistaManagerImpl search = new FutbolistaManagerImpl();
                Set<Futbolista> list = search.findByNif(s_nif);
                

                if (FutbolistaDAO.create(jugadora)) {
                    PopUp.extra("jugadora");
                }
            }
        } else {
            PopUp.display("No ha introducido ningun campo o faltan campos por rellenar");
        }



    }

    private boolean comporbar_nif(String dni){
        final String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";
        String numdni = dni.trim().substring(0, 8);
        char ltrDNI = dni.charAt(8);
        int valNumDni = Integer.parseInt(numdni) % 23;

        return dniChars.charAt(valNumDni) == ltrDNI;
    }


}
