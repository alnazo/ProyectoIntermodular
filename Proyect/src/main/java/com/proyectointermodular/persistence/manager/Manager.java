package com.proyectointermodular.persistence.manager;

import java.util.Set;

public interface Manager<T> {
    /**
     * Busqueda de todas los objetos en la BBDD.
     *
     * @return a {@link Set} of {@link T}.
     */
    Set<T> findAll();

}
