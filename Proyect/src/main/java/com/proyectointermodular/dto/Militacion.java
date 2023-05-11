package com.proyectointermodular.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Militacion {
    
    private String temporada; // YYYY/YYYYY
    private String nif_Furbolista;
    private int id_Club;

    public Militacion(ResultSet result){    
        try {
            this.temporada = result.getString("temporada");
            this.nif_Furbolista = result.getString("nif_furbolista");
            this.id_Club = result.getInt("id_club");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
