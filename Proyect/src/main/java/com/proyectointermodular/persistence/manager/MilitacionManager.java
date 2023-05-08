package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.Set;

import com.proyectointermodular.dto.Militacion;

public interface MilitacionManager extends Manager<Militacion> {
    
    /**
     * Busqueda de todos los objetos de la BBDD de una Temporada.
     *
     * @param temporada Temporada de la militacion para la busqueda.
     * @return Un {@link Set} de {@link Militacion}
     */
    Set<Militacion> findByTemporada(String temporada);

}
