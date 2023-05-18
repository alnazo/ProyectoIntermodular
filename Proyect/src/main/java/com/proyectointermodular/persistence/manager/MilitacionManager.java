package com.proyectointermodular.persistence.manager;

import com.proyectointermodular.dto.Club;
import com.proyectointermodular.dto.Futbolista;
import com.proyectointermodular.dto.Militacion;

import java.util.Set;

public interface MilitacionManager extends Manager<Militacion> {

    /**
     * Busqueda de todos los objetos de la BBDD de una Temporada.
     *
     * @param temporada Temporada de la militacion para la busqueda.
     * @return Un {@link Set} de {@link Militacion}.
     */
    Set<Militacion> findByTemporada(String temporada);

    /**
     * Metodo para obtener militaciones desde un Futbolista.
     *
     * @param futbolista Objeto Futbolista para realizacion de la busqueda mediante su NIF.
     * @return Un {@link Set} de {@link Militacion}.
     */
    Set<Militacion> findByFutbolista(Futbolista futbolista);

    /**
     * Metodo para obtener Futbolistas que han militado en un Club concreto.
     *
     * @param club Objeto Club para realizar la busqueda mediante su ID.
     * @return Un {@link Set} de {@link Militacion}.
     */
    Set<Militacion> findByClub(Club club);

    /**
     * Metodo para obtener una militacion en concreta, mediante todos los campos.
     *
     * @param temp Temporada de la militacion.
     * @param nif  Identificador de la Futbolista.
     * @param id   Identificador del Club.
     * @return {@link Militacion}.
     */
    Militacion findByAllParams(String temp, String nif, Integer id);

}
