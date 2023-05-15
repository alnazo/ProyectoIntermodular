package com.proyectointermodular.persistence.manager;

import com.proyectointermodular.dto.Futbolista;

import java.util.Map;
import java.util.Set;

public interface FutbolistaManager extends Manager<Futbolista> {

    /**
     * Busqueda de todos los objetos en la BBDD de un NIF.
     *
     * @param nif NIF de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Futbolista}.
     */
    Set<Futbolista> findByNif(String nif);

    /**
     * Busqueda de todos los objetos de Furbolistas en la BBDD de un Nombre.
     *
     * @param name Nombres de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Futbolista}.
     */
    Set<Futbolista> findByName(String name);

    /**
     * Busqueda de jugadoras dependiendo de su nacionalidad
     *
     * @param nacionalidad Nacionalidad de las jugadoras para su busqueda
     * @return Un {@link Set} de {@link Futbolista}
     */
    Set<Futbolista> findByNacionalidad(String nacionalidad);

    /**
     * Busqueda de jugadoras dependiendo su fecha de nacimiento
     *
     * @param date Fecha en formato texto para la busqueda
     * @return Un {@link Set} de {@link Futbolista}
     */
    Set<Futbolista> findByDate(String date);

    /**
     * Busqueda de jugadoras dependiendo del apellido
     *
     * @param surname Apellido de las jugadoras para su busqueda
     * @return Un {@link Set} de {@link Futbolista}
     */
    Set<Futbolista> findBySurname(String surname);

    /**
     * Busqueda de jugadoras mediante todos sus parametros si existen o no
     *
     * @param map Un mapeado con todos los datos para su busqueda
     * @return Un {@link Set} de {@link Futbolista}
     */
    Set<Futbolista> findByOptions(Map<String, String> map);

}
