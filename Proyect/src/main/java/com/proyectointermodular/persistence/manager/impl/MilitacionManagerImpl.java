package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Militacion;
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

    @Override
    public List<Militacion> findAll(Connection con) {
        List<Militacion> milit = new ArrayList<>();

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM militacion");
            result.beforeFirst();
            while (result.next()) {
                milit.add(new Militacion(result));
            }

            return milit;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Militacion> findByTemporada(Connection con, String temporada) {

        String sql = String.format("SELECT * FROM futbolista WHERE nombre LIKE %s", "%" + temporada + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Militacion> milit = new HashSet<>();
            result.beforeFirst();

            while (result.next()){
                milit.add(new Militacion(result));
            }

            return milit;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

}
