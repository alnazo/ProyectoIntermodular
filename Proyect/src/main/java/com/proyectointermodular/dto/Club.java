package com.proyectointermodular.dto;

import com.proyectointermodular.popup.PopUp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private int id;
    private String nombre;
    private Date creacion;
    private String estadio;

    /**
     * Constructor de {@link Club} recibiendo resultados de la Base de Datos.
     *
     * @param result {@link Object} de tipo {@link ResultSet} que contiene la información de la Base de Datos.
     */
    public Club(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.creacion = result.getDate("creacion");
            this.estadio = result.getString("estadio");
        } catch (SQLException e) {
            e.printStackTrace();
            PopUp.display("[WARNING] Obtención de Club no disponible");
        }
    }

}
