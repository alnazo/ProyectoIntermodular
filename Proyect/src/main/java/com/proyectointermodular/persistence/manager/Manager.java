package com.proyectointermodular.persistence.manager;

import com.proyectointermodular.persistence.connector.MySQLConnector;

import java.sql.Connection;
import java.util.List;

public interface Manager<T> {
    /**
     * Busqueda de todas los objetos en la BBDD.
     *
     * @return a {@link List} of {@link T}.
     */
    List<T> findAll();

}
