package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.MilitacionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MilitacionManagerImpl implements MilitacionManager {

    private static MySQLConnector connector = new MySQLConnector();

    @Override
    public List<Militacion> findAll() {
        List<Militacion> milit = new ArrayList<>();

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM militacion");
            while (result.next()) {
                milit.add(new Militacion(result));
            }

            con.close();

            return milit;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Militacion> findByTemporada(String temporada) {

        String sql = String.format("SELECT * FROM futbolista WHERE nombre LIKE '%s'", "%" + temporada + "%");

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Militacion> milit = new HashSet<>();

            while (result.next()){
                milit.add(new Militacion(result));
            }

            con.close();

            return milit;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }

    }

}
