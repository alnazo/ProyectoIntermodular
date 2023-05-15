package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.ClubManager;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ClubManagerImpl implements ClubManager {

    private final static MySQLConnector connector = new MySQLConnector();

    @Override
    public Set<Club> findAll() {
        Set<Club> clubs = new HashSet<>();

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM club");
            while (result.next()) {
                clubs.add(new Club(result));
            }

            con.close();

            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Club findById(Integer id) {
        String sql = "SELECT * FROM club WHERE ID = ?";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            Club club = null;

            while (result.next()) {
                club = new Club(result);
            }

            con.close();

            return club;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Set<Club> findAllByIds(Set<Integer> ids) {

        String sql = String.format("SELECT * FROM club WHERE id IN (%s)", ids.stream().map(data -> "\"" + data + "\"").collect(Collectors.joining(", ")));

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            Set<Club> clubs = new HashSet<>();

            while (result.next()) {
                Club club = new Club(result);
                clubs.add(club);
            }

            con.close();

            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Club> findByName(String name) {

        String sql = "SELECT * FROM club WHERE nombre LIKE ?";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "%" + name + "%");
            ResultSet result = stmt.executeQuery();

            Set<Club> clubs = new HashSet<>();
            while (result.next()) {
                Club club = new Club(result);
                clubs.add(club);
            }

            con.close();

            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Set<Club> findByDate(String date) {

        String sql = "SELECT * FROM club WHERE creacion = ?";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setDate(1, Date.valueOf(date));
            ResultSet result = stmt.executeQuery();

            Set<Club> clubs = new HashSet<>();
            while (result.next()) {
                clubs.add(new Club(result));
            }

            con.close();
            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Set<Club> findByEstadio(String estadio) {

        String sql = "SELECT * FROM club WHERE estadio LIKE ?";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, estadio);
            ResultSet result = stmt.executeQuery();

            Set<Club> clubs = new HashSet<>();
            while (result.next()) {
                clubs.add(new Club(result));
            }

            con.close();
            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Set<Club> findByOptions(Map<String, String> map) {
        StringBuilder sql = new StringBuilder("SELECT * FROM club WHERE ");

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
                if (key.equals("creacion")) {
                    sql.append(String.format(key + " = " + "'" + value + "'"));
                } else if (key.equals("id")) {
                    sql.append(String.format(key + " = " + Integer.parseInt(value)));
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
            Set<Club> clubs = new HashSet<>();

            while (result.next()) {
                clubs.add(new Club(result));
            }

            con.close();
            return clubs;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
