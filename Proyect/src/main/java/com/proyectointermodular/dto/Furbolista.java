package com.proyectointermodular.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Data;

@Data
public class Furbolista {
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private String nacionalidad;
    private String nif;


    public Furbolista(){}
    public Furbolista(ResultSet result){
        try {
            this.nombre = result.getString("nombre");
            this.apellido = result.getString("apellido");
            this.nacimiento = result.getDate("nacimiento");
            this.nacionalidad = result.getString("nacionalidad");
            this.nif = result.getString("nacionalidad");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
