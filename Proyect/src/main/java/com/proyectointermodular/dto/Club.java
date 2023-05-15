package com.proyectointermodular.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private int id;
    private String nombre;
    private Date creacion;
    private String estadio;

    public Club(ResultSet result){
        try {
            this.id = result.getInt("id");
            this.nombre = result.getString("nombre");
            this.creacion = result.getDate("creacion");
            this.estadio = result.getString("estadio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCreacionString(){
        return this.creacion.toString();
    }

}
