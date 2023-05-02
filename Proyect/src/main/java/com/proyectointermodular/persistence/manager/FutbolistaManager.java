package com.proyectointermodular.persistence.manager;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.Set;

import com.proyectointermodular.dto.Futbolista;

public interface FutbolistaManager extends Manager<Futbolista> {
    
    /**
     * Busqueda de todos los objetos en la BBDD de un NIF.
     * 
     * @param con Conexion de BBDD.
     * @param nif NIF de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Futbolista}.
     */
    Set<Futbolista> findByNif(Connection con, String nif);

    /**
     * Busqueda de todos los objetos de Furbolistas en la BBDD de un Nombre.
     * 
     * @param con Conexion de BBDD.
     * @param name Nombres de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Futbolista}.
     */
    Set<Futbolista> findByName(Connection con, String name);

    /**
     * Busqueda de jugadoras dependiendo de su nacionalidad
     *
     * @param con Conexion de BBDD
     * @param nacionalidad Nacionalidad de las jugadoras para su busqueda
     * @return Un {@link Set} de {@link Futbolista}
     */
    Set<Futbolista> findByNacionalidad(Connection con, String nacionalidad);

}
