package com.proyectointermodular.persistence.manager;

import java.sql.Connection;
import java.util.List;

public interface Manager<T> {
    /**
     * Busqueda de todas los objetos en la BBDD.
     * 
     * @param con Conexion de BBDD.
     * @return a {@link List} of {@link T}.
     */
    List<T> findAll(Connection con);

}
