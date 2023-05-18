package com.proyectointermodular.controllers.club;

import com.proyectointermodular.controllers.MenuPrincipal;
import com.proyectointermodular.dao.ClubDAO;
import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.popup.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.Set;

public class AddClub extends MenuPrincipal {

    @FXML
    private TextField nombre;
    @FXML
    private TextField creacion;
    @FXML
    private TextField estadio;

    /**
     * Inicializacion de la visualización.
     */
    @FXML
    private void initialize() {
        nombre.setText("");
        creacion.setText("");
        estadio.setText("");
    }

    /**
     * Envio de informacion para crear un {@link Club}
     */
    @FXML
    private void enviar() {
        String s_nombre = this.nombre.getText();
        String s_creacion = this.creacion.getText();
        String s_estadio = this.estadio.getText();

        if (!s_creacion.matches("((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])") && !s_creacion.equals("")) {
            PopUp.display("La fecha que se ha introducido no corresponde al formato yyyy-mm-dd (Año-Mes-Día)");
        } else if (s_nombre.equals("") && s_creacion.equals("") & s_estadio.equals("")) {
            PopUp.display("No ha introducido ningun campo o faltan campos por rellenar");
        } else {
            Date crea = Date.valueOf(s_creacion);
            Club club = new Club(1, s_nombre, crea, s_estadio);

            Set<Club> list = new ClubManagerImpl().findByName(s_nombre);
            if (list.size() > 0) {
                PopUp.display("El Club que esta introduciendo ya esxiste.");
            } else {
                PopUp.add("club");
                ClubDAO.create(club);
                if (PopUp.resultado) {
                    Button b = (Button) windowsGeneric.getParent().getParent().lookup("#addClub");
                    b.fire();
                    PopUp.resultado = false;
                } else {
                    Button b = (Button) windowsGeneric.getParent().getParent().lookup("#verClubs");
                    b.fire();
                }
            }

        }

    }


}
