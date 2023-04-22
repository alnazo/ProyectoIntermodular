package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface Manager<T, U> {
    /**
     * Busqueda de todas los objetos en la BBDD.
     * 
     * @param con Conexion de BBDD.
     * @return a {@link List} of {@link T}.
     */
    List<T> findAll(Connection con);

    /**
     * Busca todas los objetos en la BBDD de una lista de ID.
     * 
     * @param con Conexion de BBDD.
     * @param id Lista de busqueda de entidades por ID.
     * @return Un/a {@link T}.
     */
    T findById(Connection con, U id);

    /**
     * Busca todas los objetos en la BBDD de una lista de IDs.
     * 
     * @param con Conexion de BBDD.
     * @param ids ID de entidades configuradas para buscar.
     * @return Una {@link List} de {@link T}.
     */
    List<T> findAllByIds(Connection con, Set<U> ids);
}
