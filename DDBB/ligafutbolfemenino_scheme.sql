-- Si es necesario borrar la base de dato existente
DROP DATABASE IF EXISTS ligafutbolfemenino;

CREATE DATABASE ligafutbolfemenino;

USE ligafutbolfemenino;

-- Si es necesario borrar la tabla futbolista
DROP TABLE IF EXISTS futbolista;

CREATE TABLE futbolista(
    nombre varchar(32),
    apellido varchar(32),
    nacimiento DATE NOT NULL,
    nacionalidad varchar(32) NOT NULL,
    nif varchar(9) PRIMARY KEY NOT NULL
);

-- Si es necesario borrar la tabla club
DROP TABLE IF EXISTS club;

CREATE TABLE club(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(32) UNIQUE,
    creacion DATE NOT NULL,
    estadio varchar(32) NOT NULL
);

-- Si es necesario borrar la tabla militacion
DROP TABLE IF EXISTS militacion;

CREATE TABLE militacion(
    temporada varchar(9) NOT NULL,
    nif_futbolista varchar(9),
    id_club int,
    CONSTRAINT fk_nif_futbolista FOREIGN KEY (nif_futbolista) REFERENCES futbolista(nif),
    CONSTRAINT fk_id_club FOREIGN KEY (id_club) REFERENCES club(id)
);


-- APARTADO C

ALTER TABLE futbolista ADD escalafones char(1) CHECK (escalafones IN ('S', 'N'));

ALTER TABLE futbolista ADD email varchar(255) UNIQUE CHECK (email LIKE '%@%');

ALTER TABLE futbolista DROP COLUMN nacionalidad;

ALTER TABLE club ADD espectadores_estadio INTEGER CHECK (espectadores_estadio > 0 AND espectadores_estadio < 99999);

CREATE INDEX idx_nombre_apellidos ON futbolista(nombre, apellido);

CREATE INDEX idx_fecha_ingreso ON militacion(temporada);

RENAME TABLE futbolista TO jugadoras;

DROP TABLE jugadoras;


-- APARTADO D

DELIMITER $$
CREATE PROCEDURE agregar_jugadora(
    IN nombre varchar(32),
    IN apellido varchar(32),
    IN nacimiento DATE,
    IN nacionalidad varchar(32),
    IN nif varchar(9)
)
BEGIN
    INSERT INTO futbolista VALUES (nombre, apellido, nacimiento, nacionalidad, nif);
END $$
DELIMITER ;


--
DELIMITER $$
CREATE PROCEDURE agregar_club(
    IN nombre varchar(32),
    IN creacion DATE,
    IN estadio varchar(32)
)
BEGIN
    INSERT INTO club VALUES (nombre, creacion, estadio);
END $$
DELIMITER ;


--
DELIMITER $$
CREATE PROCEDURE modificar_jugadora(
    IN p_nif varchar(9),
    IN p_nombre varchar(32),
    IN p_apellido varchar(32),
    IN p_nacimiento DATE,
    IN p_nacionalidad varchar(32)
)
BEGIN
    UPDATE futbolista
    SET nombre = p_nombre, apellido = p_apellido, nacimiento = p_nacimiento, nacionalidad = p_nacionalidad
    WHERE nif = p_nif;
END $$

DELIMITER ;

--
DELIMITER $$
CREATE PROCEDURE jugadoras_termporada_club (
    IN p_id_club int,
    IN p_temp varchar(9)
)
BEGIN
    SELECT p_temp AS Temporada, c.nombre AS Equipo, (f.nombre, f.apellido) AS Jugadora
    FROM militacion m
    INNER JOIN futbolista f ON m.nif_futbolista = f.nif
    INNER JOIN club c ON m.id_club = c.id
    WHERE m.temporada = p_temp AND m.id_club = p_id_club;
END $$

DELIMITER ;


--
DELIMITER $$
CREATE PROCEDURE listar_equipos_por_jugadora(
    IN p_nif_jugadora varchar(9)
)
BEGIN
    SELECT c.nombre AS Equipo, m.temporada
    FROM militacion m
    INNER JOIN club c ON m.id_club = c.id
    WHERE m.nif_futbolista = p_nif_jugadora;
END $$

DELIMITER ;


--
DELIMITER $$

CREATE FUNCTION contar_equipos_por_jugadora(
    p_nif_jugadora varchar(9)
)
RETURNS INT
BEGIN
    DECLARE contador INT;
    SELECT COUNT(DISTINCT id_club)
    INTO contador
    FROM militacion
    WHERE nif_futbolista = p_nif_jugadora;
    RETURN contador;
END $$

DELIMITER ;


--
CREATE VIEW v_jugadoras_por_equipo AS
SELECT m.temporada, c.nombre AS nombre_equipo, f.nombre, f.apellido
FROM militacion m
INNER JOIN club c ON m.id_club = c.id
INNER JOIN futbolista f ON m.nif_futbolista = f.nif
ORDER BY m.temporada, c.nombre, f.apellido;


--
CREATE VIEW v_equipos_por_jugadora AS
SELECT f.nombre, f.apellido, GROUP_CONCAT(DISTINCT c.nombre ORDER BY c.nombre SEPARATOR ', ') AS equipos
FROM futbolista f
LEFT JOIN militacion m ON f.nif = m.nif_futbolista
LEFT JOIN club c ON m.id_club = c.id
GROUP BY f.nif;

