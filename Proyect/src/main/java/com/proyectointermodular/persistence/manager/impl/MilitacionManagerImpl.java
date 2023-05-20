package com.proyectointermodular.persistence.manager.impl;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.persistence.manager.MilitacionManager;
import com.proyectointermodular.popup.PopUp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class MilitacionManagerImpl implements MilitacionManager {

    private final static MySQLConnector connector = new MySQLConnector();

    @Override
    public Set<Militacion> findAll() {

        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            Set<Militacion> milit = new HashSet<>();
            ResultSet result = stmt.executeQuery("SELECT * FROM militacion");
            while (result.next()) {
                milit.add(new Militacion(result));
            }

            con.close();

            return milit;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
            return null;
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Acceso de datos no disponible.");
            return null;
        }
    }

    @Override
    public Militacion findByAllParams(String temp, String nif, Integer id) {
        try {
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            Militacion mili = null;

            String sql = "SELECT * FROM militacion WHERE nif_futbolista = '" + nif + "' AND temporada = '" + temp + "' AND id_club = " + id;

            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                mili = new Militacion(result);
            }
            con.close();

            return mili;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
            return null;
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Acceso de datos no disponible.");
            return null;
        }

    }

    @Override
    public Set<Militacion> findByTemporada(String temporada) {
        try {
            String sql = String.format("SELECT * FROM militacion WHERE temporada LIKE '%s'", "%" + temporada + "%");
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Militacion> milit = new HashSet<>();

            while (result.next()) {
                milit.add(new Militacion(result));
            }

            con.close();

            return milit;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
            return null;
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Acceso de datos no disponible.");
            return null;
        }
    }

    @Override
    public Set<Militacion> findByFutbolista(Futbolista futbolista) {
        try {
            String sql = String.format("SELECT * FROM militacion WHERE nif_futbolista = '%s'", futbolista.getNif());
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Militacion> milit = new HashSet<>();

            while (result.next()) {
                milit.add(new Militacion(result));
            }
            con.close();
            return milit;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
            return null;
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Acceso de datos no disponible.");
            return null;
        }

    }

    @Override
    public Set<Militacion> findByClub(Club club) {
        try {
            String sql = String.format("SELECT * FROM militacion WHERE id_club = %s", club.getId());
            Connection con = connector.getMySQLConnection();
            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(sql);
            Set<Militacion> milit = new HashSet<>();

            while (result.next()) {
                milit.add(new Militacion(result));
            }
            con.close();
            return milit;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.display("[ERROR] Contacte con desarrollador.");
            return null;
        } catch (SQLException e ){
            e.printStackTrace();
            PopUp.display("[WARNING] Acceso de datos no disponible.");
            return null;
        }
    }

}
