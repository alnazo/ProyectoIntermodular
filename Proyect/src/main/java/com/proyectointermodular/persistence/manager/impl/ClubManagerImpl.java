package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.ClubManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ClubManagerImpl implements ClubManager {

    private final static MySQLConnector connector = new MySQLConnector();

    @Override
    public List<Club> findAll(){
        List<Club> clubs = new ArrayList<>();

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM club");
            while (result.next()){
                clubs.add(new Club(result));
            }

            con.close();

            return clubs;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Club findById(Integer id){
        String sql = "SELECT * FROM club WHERE ID = ?";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            Club club = null;

            while (result.next()){
                club = new Club(result);
            }

            con.close();

            return club;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Club> findAllByIds(Set<Integer> ids){

        String sql = String.format("SELECT * FROM club WHERE id IN (%s)",ids.stream().map(data -> "\""+data+"\"").collect(Collectors.joining(", ")));

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);

            List<Club> clubs = new ArrayList<>();

            while (result.next()){
                Club club = new Club(result);
                clubs.add(club);
            }

            con.close();

            return clubs;

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Club> findByName(String name){

        String sql = "SELECT * FROM club WHERE nombre LIKE '?'";

        try {
            Connection con = connector.getMySQLConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, "%"+name+"%");
            ResultSet result = stmt.executeQuery();

            Set<Club> clubs = new HashSet<>();
            while (result.next()){
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


}
