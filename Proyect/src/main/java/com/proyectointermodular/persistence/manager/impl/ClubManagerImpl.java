package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.persistence.manager.ClubManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ClubManagerImpl implements ClubManager {

    @Override
    public List<Club> findAll(Connection con){
        List<Club> clubs = new ArrayList<>();

        try (Statement stmt = con.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM club");
            while (result.next()){
                clubs.add(new Club(result));
            }

            return clubs;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Club findById(Connection con, Integer id){
        String sql = "SELECT * FROM club WHERE ID = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            Club club = null;

            while (result.next()){
                club = new Club(result);
            }

            return club;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Club> findAllByIds(Connection con, Set<Integer> ids){

        String sql = String.format("SELECT * FROM club WHERE id IN (%s)",ids.stream().map(data -> "\""+data+"\"").collect(Collectors.joining(", ")));

        try (Statement stmt = con.createStatement()){
            ResultSet result = stmt.executeQuery(sql);

            List<Club> clubs = new ArrayList<>();

            while (result.next()){
                Club club = new Club(result);
                clubs.add(club);
            }

            return clubs;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Club> findByName(Connection con, String name){

        String sql = "SELECT * FROM club WHERE nombre LIKE '?'";

        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, "%"+name+"%");
            ResultSet result = stmt.executeQuery();

            Set<Club> clubs = new HashSet<>();
            while (result.next()){
                Club club = new Club(result);
                clubs.add(club);
            }
            return clubs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
