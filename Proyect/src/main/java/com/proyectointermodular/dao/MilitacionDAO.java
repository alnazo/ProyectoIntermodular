package com.proyectointermodular.dao;

import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.connector.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MilitacionDAO {

    private final static MySQLConnector connector = new MySQLConnector();

    public static void create(Militacion mili) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "INSERT INTO militacion VALUES (?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, mili.getTemporada());
            stmt.setString(2, mili.getNif_Futbolista().getNif());
            stmt.setInt(3, mili.getId_Club().getId());

            stmt.execute();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {

    }

    public void delete(Militacion militacion) {
        try {
            Connection con = connector.getMySQLConnection();
            String sql = "DELETE FROM militacion WHERE (nif_futbolista = '" + militacion.getNif_Futbolista().getNif() + "' AND temporada = '" + militacion.getTemporada() + "' AND id_club = " + militacion.getId_Club().getId() + ")";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

}
