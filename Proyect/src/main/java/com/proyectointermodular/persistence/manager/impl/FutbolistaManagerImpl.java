package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.manager.FutbolistaManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FutbolistaManagerImpl implements FutbolistaManager {

    @Override
    public List<Futbolista> findAll(Connection con) {

        List<Futbolista> furbolista = new ArrayList<>();

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM futbolista");
            result.beforeFirst();
            while (result.next()) {
                furbolista.add(new Futbolista(result));
            }

            return furbolista;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Futbolista> findByNif(Connection con, String nif) {

        String sql = String.format("SELECT * FROM futbolista WHERE nif LIKE %s", "%" + nif + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();
            result.beforeFirst();

            while (result.next()) {
                Futbolista futbolista = new Futbolista(result);
                futbolistas.add(futbolista);
            }

            return futbolistas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Futbolista> findByName(Connection con, String name) {

        String sql = String.format("SELECT * FROM futbolista WHERE nombre LIKE %s", "%" + name + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();
            result.beforeFirst();

            while (result.next()) {
                Futbolista futbolista = new Futbolista(result);
                futbolistas.add(futbolista);
            }

            return futbolistas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Set<Futbolista> findByNacionalidad(Connection con, String nacionalidad) {

        String sql = String.format("SELECT * FROM futbolista WHERE nacionalidad LIKE %s", "%" + nacionalidad + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();
            result.beforeFirst();

            while (result.next()) {
                Futbolista futbolista = new Futbolista(result);
                futbolistas.add(futbolista);
            }

            return futbolistas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
