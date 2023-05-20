package com.proyectointermodular.dao;

import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.popup.PopUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MilitacionDAO {

    private final static MySQLConnector connector = new MySQLConnector();

    /**
     * Creacion de una entrada en la Base de Datos.
     *
     * @param mili {@link Militacion}.
     */
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Inserción a base de datos no disponible.");
        }
    }

    /**
     * Actualizacion de una entrada concreta en la Base de Datos desde el punto de Futbolista.
     *
     * @param militacion {@link Militacion}.
     */
    public void updateF(Militacion militacion) {
        try {
            Connection con = connector.getMySQLConnection();
            String sql = "UPDATE militacion SET temporada = ?, id_club = ? WHERE nif_futbolista = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, militacion.getTemporada());
            stmt.setInt(2, militacion.getId_Club().getId());
            stmt.setString(3, militacion.getNif_Futbolista().getNif());

            stmt.execute();
            con.close();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Actualización a base de datos no disponible.");
        }
    }

    /**
     * Actualizacion de una entrada concreta en la Base de Datos desde el punto de Clubs.
     *
     * @param militacion {@link Militacion}.
     */
    public void updateC(Militacion militacion) {
        try {
            Connection con = connector.getMySQLConnection();
            String sql = "UPDATE militacion SET temporada = ?, nif_futbolista = ? WHERE id_club = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, militacion.getTemporada());
            stmt.setString(2, militacion.getNif_Futbolista().getNif());
            stmt.setInt(3, militacion.getId_Club().getId());

            stmt.execute();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Actualización a base de datos no disponible.");
        }
    }

    /**
     * Eliminacion de una entrada concreta en la Base de Datos.
     *
     * @param militacion {@link Militacion}.
     */
    public void delete(Militacion militacion) {
        try {
            Connection con = connector.getMySQLConnection();
            String sql = "DELETE FROM militacion WHERE (nif_futbolista = ? AND temporada = ? AND id_club = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, militacion.getNif_Futbolista().getNif());
            stmt.setString(2, militacion.getTemporada());
            stmt.setInt(3, militacion.getId_Club().getId());

            stmt.execute();
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Eliminación de datos no disponible.");
        }
    }

}
