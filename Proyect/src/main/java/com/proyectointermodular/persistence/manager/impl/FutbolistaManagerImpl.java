package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.FutbolistaManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FutbolistaManagerImpl implements FutbolistaManager {
    private final static MySQLConnector connector = new MySQLConnector();

    @Override
    public Set<Futbolista> findAll() {

        Set<Futbolista> furbolista = new HashSet<>();
        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM futbolista");
            while (result.next()) {
                furbolista.add(new Futbolista(result));
            }
            con.close();
            return furbolista;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Futbolista> findByNif(String nif) {
        String sql;
        if (nif.length() < 9) {
            sql = String.format("SELECT * FROM futbolista WHERE nif LIKE '%s'", "%" + nif + "%");
        } else {
            sql = String.format("SELECT * FROM futbolista WHERE nif = '%s'", nif);
        }
        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Futbolista> findByName(String name) {

        String sql = String.format("SELECT * FROM futbolista WHERE nombre LIKE '%s'", "%" + name + "%");

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Set<Futbolista> findBySurname(String surname) {
        String sql = String.format("SELECT * FROM futbolista WHERE apellido LIKE '%s'", "%" + surname + "%");

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Futbolista> findByNacionalidad(String nacionalidad) {

        String sql = String.format("SELECT * FROM futbolista WHERE nacionalidad LIKE '%s'", "%" + nacionalidad + "%");

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Futbolista> findByDate(String date) {
        String sql = String.format("SELECT * FROM futbolista WHERE nacimiento = '%s'", date);

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<Futbolista> findByOptions(Map<String, String> map) {
        StringBuilder sql = new StringBuilder("SELECT * FROM futbolista WHERE ");

        int contador = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
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

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql.toString());
            Set<Futbolista> futbolistas = new HashSet<>();

            while (result.next()) {
                futbolistas.add(new Futbolista(result));
            }
            con.close();
            return futbolistas;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    ;


}