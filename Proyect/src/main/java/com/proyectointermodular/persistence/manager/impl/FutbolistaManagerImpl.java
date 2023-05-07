package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.manager.FutbolistaManager;

import java.sql.*;
import java.util.*;

public class FutbolistaManagerImpl implements FutbolistaManager {

    @Override
    public List<Futbolista> findAll(Connection con) {

        List<Futbolista> furbolista = new ArrayList<>();

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM futbolista");
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

        String sql = String.format("SELECT * FROM futbolista WHERE nif LIKE '%s'", "%" + nif + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

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

        String sql = String.format("SELECT * FROM futbolista WHERE nombre LIKE '%s'", "%" + name + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

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

    public Set<Futbolista> findBySurname(Connection con, String surname) {
        String sql = String.format("SELECT * FROM futbolista WHERE apellido LIKE '%s'", "%" + surname + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

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

        String sql = String.format("SELECT * FROM futbolista WHERE nacionalidad LIKE '%s'", "%" + nacionalidad + "%");

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

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

    public Set<Futbolista> findByDate(Connection con, String date) {
        String sql = String.format("SELECT * FROM futbolista WHERE nacimiento = '%s'", date);

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

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

    public Set<Futbolista> findByOptions(Connection con, Map<String, String> map) {
        StringBuilder sql = new StringBuilder("SELECT * FROM futbolista WHERE ");

        int contador = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!value.equals("")) {
                contador++;
            }
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.length() > 0) {
                if (key.equals("nacimiento")) {
                    sql.append(String.format(key + " = " + "'" + value + "'"));
                } else {
                    sql.append(String.format(key + " LIKE '%s'", "%" + value + "%"));
                }
                contador--;
            }
            if (contador > 0 && value.length() > 0) {
                sql.append(" AND ");
            }
        }

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery(sql.toString());
            Set<Futbolista> futbolistas = new HashSet<>();

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

    ;


}