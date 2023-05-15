package com.proyectointermodular.dao;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.connector.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubDAO {

    private final static MySQLConnector connector = new MySQLConnector();

    public static void create(Club club) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "INSERT INTO club (nombre, creacion, estadio) VALUES (?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, club.getNombre());
            stmt.setDate(2, club.getCreacion());
            stmt.setString(3, club.getEstadio());

            stmt.execute();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Club club) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "UPDATE club SET nombre = ?, creacion = ?, estadio = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, club.getNombre());
            stmt.setDate(2, club.getCreacion());
            stmt.setString(3, club.getEstadio());
            stmt.setInt(4, club.getId());

            stmt.execute();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "DELETE FROM club WHERE (id = ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
