package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.Set;

import com.proyectointermodular.dto.Club;

public interface ClubManager extends Manager<Club, String> {
    
    /**
     * Busqueda de todos los objetos de Club en la BBDD de un Nombre.
     * 
     * @param con Conexion de BBDD
     * @param name Nombre del equipo para la busqueda.
     * @return Un {@link Set} de {@link Club}
     */
    Set<Club> findByName(Connection con, String name);



}
