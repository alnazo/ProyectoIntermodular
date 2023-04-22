DROP DATABASE IF EXISTS ligafutbolfemenino;
CREATE DATABASE ligafutbolfemenino;

USE ligafutbolfemenino;

DROP TABLE IF EXISTS furbolista;
CREATE TABLE furbolista(
    nombre varchar(32),
    apellido varchar(32),
    nacimiento DATE,
    nacionalidad varchar(32),
    nif varchar(9) PRIMARY KEY NOT NULL
);

DROP TABLE IF EXISTS club;
CREATE TABLE club(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(32),
    creacion DATE,
    estadio varchar(32) 
);

DROP TABLE IF EXISTS militacion;
CREATE TABLE militacion(
    temporada varchar(9) NOT NULL,
    nif_furbolista varchar(9),
    id_club int,
    CONSTRAINT fk_nif_furbolista FOREIGN KEY (nif_furbolista) REFERENCES furbolista(nif),
    CONSTRAINT fk_id_club FOREIGN KEY (id_club) REFERENCES club(id)
);