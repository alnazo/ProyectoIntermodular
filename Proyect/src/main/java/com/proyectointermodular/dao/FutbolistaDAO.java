package com.proyectointermodular.dao;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.connector.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FutbolistaDAO {

    private static MySQLConnector connector = new MySQLConnector();

    public static boolean create(Futbolista futbolista){
        try {
            Connection con = connector.getMySQLConnection();

            String sql = "INSERT INTO futbolista VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, futbolista.getNombre());
            stmt.setString(2, futbolista.getApellido());
            stmt.setDate(3, futbolista.getNacimiento());
            stmt.setString(4, futbolista.getNacionalidad());
            stmt.setString(5, futbolista.getNif());

            boolean res = stmt.execute();

            con.close();

            return res;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static void update(Futbolista futbolista){

    }
    public static void delete(Futbolista futbolista){

    }
}
