package com.proyectointermodular.persistence.manager;

import com.proyectointermodular.dto.Club;

import java.util.Set;

public interface ClubManager extends Manager<Club> {

    /**
     * Busca todas los objetos en la BBDD de una lista de ID.
     *
     * @param id ID de club especifico para buscar.
     * @return Un/a {@link Integer}.
     */
    Club findById(Integer id);

    /**
     * Busca todas los objetos en la BBDD de una lista de IDs.
     *
     * @param ids ID de clubs para mostrar.
     * @return Una {@link Set} de {@link Integer}.
     */
    Set<Club> findAllByIds(Set<Integer> ids);


    /**
     * Busqueda de todos los objetos de Club en la BBDD de un Nombre.
     *
     * @param name Nombre del equipo para la busqueda.
     * @return Un {@link Set} de {@link Club}.
     */
    Set<Club> findByName(String name);

    /**
     * Busqueda de todos los objetos dependiendo de una fecha introducida.
     *
     * @param date Fecha en formato YYYY-MM-DD (yyyy-MM-dd).
     * @return Un {@link Set} de {@link Club}.
     */
    Set<Club> findByDate(String date);


    /**
     * Busqueda de todos los objetos dependiendo del nombre del estadio.
     *
     * @param estadio Nombre del estadio de futbol.
     * @return Un {@link Set} de {@link Club}.
     */
    Set<Club> findByEstadio(String estadio);

}
