package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.Set;

import com.proyectointermodular.dto.Furbolista;

public interface FurbolistaManager extends Manager<Furbolista, String> {
    
    /**
     * Busqueda de todos los objetos en la BBDD de un NIF.
     * 
     * @param con Conexion de BBDD.
     * @param nif NIF de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Furbolista}.
     */
    Set<Furbolista> findByNif(Connection con, String nif);

    /**
     * Busqueda de todos los objetos de Furbolistas en la BBDD de un Nombre.
     * 
     * @param con Conexion de BBDD.
     * @param name Nombres de la jugadora para la busqueda.
     * @return Un {@link Set} de {@link Furbolista}.
     */
    Set<Furbolista> findByName(Connection con, String name);

}
