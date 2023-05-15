package com.proyectointermodular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Futbolista {
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private String nacionalidad;
    private String nif;


    public Futbolista(ResultSet result) {
        try {
            this.nombre = result.getString("nombre");
            this.apellido = result.getString("apellido");
            this.nacimiento = result.getDate("nacimiento");
            this.nacionalidad = result.getString("nacionalidad");
            this.nif = result.getString("nif");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
