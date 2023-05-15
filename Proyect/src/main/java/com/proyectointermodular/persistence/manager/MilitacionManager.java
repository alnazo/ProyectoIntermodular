package com.proyectointermodular.persistence.manager;

import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;

import java.util.Set;

public interface MilitacionManager extends Manager<Militacion> {
    
    /**
     * Busqueda de todos los objetos de la BBDD de una Temporada.
     *
     * @param temporada Temporada de la militacion para la busqueda.
     * @return Un {@link Set} de {@link Militacion}
     */
    Set<Militacion> findByTemporada(String temporada);

    /**
     *
     * @param futbolista Objeto Futbolista para realizacion de la busqueda.
     * @return Un {@link Set} de {@link Militacion}
     */
    Set<Militacion> findByFutbolista(Futbolista futbolista);

}
