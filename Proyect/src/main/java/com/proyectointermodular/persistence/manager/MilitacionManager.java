package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.Set;

import com.proyectointermodular.dto.Militacion;

public interface MilitacionManager extends Manager<Militacion, String> {
    
    /**
     * Busqueda de todos los objetos de la BBDD de una Temporada.
     * 
     * @param con Conexion de BBDD.
     * @param temporada Temporada de la militacion para la busqueda.
     * @return Un {@link Set} de {@link Militacion}
     */
    Set<Militacion> findByTemporada(Connection con, String temporada);

}
