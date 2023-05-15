package com.proyectointermodular.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.proyectointermodular.persistence.manager.impl.ClubManagerImpl;
import com.proyectointermodular.persistence.manager.impl.FutbolistaManagerImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Militacion {
    
    private String temporada; // YYYY/YYYYY
    private Futbolista nif_Furbolista;
    private Club id_Club;

    public Militacion(ResultSet result){
        try {
            Set<Futbolista> li = new FutbolistaManagerImpl().findByNif(result.getString("nif_futbolista"));
            Futbolista fut = li.iterator().next();
            this.temporada = result.getString("temporada");
            this.nif_Furbolista = fut;
            this.id_Club = new ClubManagerImpl().findById(result.getInt("id_club"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
