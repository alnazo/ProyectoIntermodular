package com.proyectointermodular.dao;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.popup.PopUp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FutbolistaDAO {

    private final static MySQLConnector connector = new MySQLConnector();

    /**
     * Metodo de creacion de {@link Futbolista}.
     *
     * @param futbolista {@link Futbolista}.
     */
    public static void create(Futbolista futbolista) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "INSERT INTO futbolista VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, futbolista.getNombre());
            stmt.setString(2, futbolista.getApellido());
            stmt.setDate(3, futbolista.getNacimiento());
            stmt.setString(4, futbolista.getNacionalidad());
            stmt.setString(5, futbolista.getNif());

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
     * Metodo de actualizacion de {@link Futbolista}.
     *
     * @param futbolista {@link Futbolista}.
     */
    public void update(Futbolista futbolista) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "UPDATE futbolista SET nombre = ?, apellido = ?, nacimiento = ?, nacionalidad = ? WHERE nif = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, futbolista.getNombre());
            stmt.setString(2, futbolista.getApellido());
            stmt.setDate(3, futbolista.getNacimiento());
            stmt.setString(4, futbolista.getNacionalidad());
            stmt.setString(5, futbolista.getNif());

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
     * Metodo de eliminacion de {@link Futbolista}
     *
     * @param nif NIF/DNI de {@link Futbolista}.
     */
    public void delete(String nif) {
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "DELETE FROM futbolista WHERE (nif = ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nif);

            stmt.executeUpdate();
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
