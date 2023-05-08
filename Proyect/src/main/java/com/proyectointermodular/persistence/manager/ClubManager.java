package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.proyectointermodular.dto.Club;

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
     * @return Una {@link List} de {@link Integer}.
     */
    List<Club> findAllByIds(Set<Integer> ids);


    /**
     * Busqueda de todos los objetos de Club en la BBDD de un Nombre.
     *
     * @param name Nombre del equipo para la busqueda.
     * @return Un {@link Set} de {@link Club}
     */
    Set<Club> findByName(String name);



}
