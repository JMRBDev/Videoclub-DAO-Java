# Videoclub DAO - HLC 2º DAW B
## Jose María Rosendo Bienvenido

Aplicación Full-stack para la gestión de un Videoclub ficticio.

## Tecnologías
* [Java](https://docs.oracle.com/en/java/)
* [HTML](https://developer.mozilla.org/es/docs/Web/HTML) / [CSS](https://developer.mozilla.org/es/docs/Web/CSS) ([Bootstrap](https://getbootstrap.com/docs/4.1/getting-started/introduction/)) / [JavaScript](https://developer.mozilla.org/es/docs/Web/JavaScript)
* [Tomcat](http://tomcat.apache.org/tomcat-8.0-doc/)
* [MariaDB](https://mariadb.com/kb/en/documentation/) ([MariaDB Connector J](https://mariadb.com/kb/en/about-mariadb-connector-j/))
* [Gson](https://sites.google.com/site/gson/gson-user-guide)

## Versiones de Java y Tomcat
* Java JDK 11.0.10 ([AdoptOpenJDK 11.0.10 for MacOS](https://adoptopenjdk.net/))
* [Tomcat 8.5](https://tomcat.apache.org/download-80.cgi)

## Script SQL
~~~~sql
# Jose María Rosendo Bienvenido
CREATE DATABASE IF NOT EXISTS videoclub_jose;

USE videoclub_jose;

CREATE TABLE IF NOT EXISTS usuarios(
	username varchar(100),
	contrasena varchar(100) NOT NULL,
	nombre varchar(100) NOT NULL,
	apellidos varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	saldo double NOT NULL,
	premium tinyint,
	PRIMARY KEY (username)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS peliculas(
	id int AUTO_INCREMENT,
	titulo varchar(100) NOT NULL,
	genero varchar(100) NOT NULL,
	director varchar(100) NOT NULL,
	actor_principal varchar(100) NOT NULL,
	copias int NOT NULL,
	estreno tinyint,
	PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS alquilar(
	id int,
	username varchar(100),
	fecha date,
	CONSTRAINT fk_alquilar_peliculas
		FOREIGN KEY (id)
		REFERENCES peliculas (id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT fk_alquilar_usuarios
		FOREIGN KEY (username)
		REFERENCES usuarios (username)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE = InnoDB;

INSERT INTO usuarios
    VALUES('test1', 'pass1', 'Test 1', 'Test Apellidos 1', 'test1@jmrbdev.com', 10, 1);
INSERT INTO usuarios
    VALUES('test2', 'pass2', 'Test 2', 'Test Apellidos 2', 'test2@jmrbdev.com', 20, 0);
INSERT INTO usuarios
    VALUES('test3', 'pass3', 'Test 3', 'Test Apellidos 3', 'test3@jmrbdev.com', 30, 1);
INSERT INTO usuarios
    VALUES('test4', 'pass4', 'Test 4', 'Test Apellidos 4', 'test4@jmrbdev.com', 40, 0);
INSERT INTO usuarios
    VALUES('test5', 'pass5', 'Test 5', 'Test Apellidos 5', 'test5@jmrbdev.com', 50, 0);
INSERT INTO usuarios
    VALUES('test6', 'pass6', 'Test 6', 'Test Apellidos 6', 'test6@jmrbdev.com', 60, 1);

INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('Malcolm & Marie', 'Drama', 'Sam Levinson', 'John David Washington', 10, 1);
INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('The Dig', 'Biografía', 'Simon Stone', 'Carey Mulligan', 7, 0);
INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('The Little Things', 'Crimen', 'John Lee Hancock', 'Denzel Washington', 9, 0);
INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('Old', 'Drama', 'M. Night Shyamalan', 'Thomasin McKenzie', 12, 1);
INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('News of the World', 'Acción', 'Paul Greengrass', 'Tom Hanks', 8, 0);
INSERT INTO peliculas (titulo, genero, director, actor_principal, copias, estreno)
    VALUES('Greenland', 'Acción', 'Ric Roman Waugh', 'Gerard Butler', 6, 1);
~~~~


`With 💚 by JMRBDev`
