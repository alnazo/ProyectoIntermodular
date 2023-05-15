package com.proyectointermodular.dto;

import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Militacion {

    private String temporada; // YYYY/YYYYY
    private Futbolista nif_Futbolista;
    private Club id_Club;

    public Militacion(ResultSet result) {
        try {
            Set<Futbolista> li = new FutbolistaManagerImpl().findByNif(result.getString("nif_futbolista"));
            Futbolista fut = li.iterator().next();
            this.temporada = result.getString("temporada");
            this.nif_Futbolista = fut;
            this.id_Club = new ClubManagerImpl().findById(result.getInt("id_club"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
