DROP TABLE IF EXISTS militacion;
DROP TABLE IF EXISTS futbolista;
DROP TABLE IF EXISTS club;
CREATE TABLE futbolista(
    nombre varchar(32),
    apellido varchar(32),
    nacimiento DATE NOT NULL,
    nacionalidad varchar(32) NOT NULL,
    nif varchar(9) PRIMARY KEY NOT NULL
);
CREATE TABLE club(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(32) UNIQUE,
    creacion DATE NOT NULL,
    estadio varchar(32) NOT NULL
);
CREATE TABLE militacion(
    temporada varchar(9) NOT NULL,
    nif_futbolista varchar(9),
    id_club int,
    CONSTRAINT fk_nif_futbolista FOREIGN KEY (nif_futbolista) REFERENCES futbolista(nif),
    CONSTRAINT fk_id_club FOREIGN KEY (id_club) REFERENCES club(id)
);