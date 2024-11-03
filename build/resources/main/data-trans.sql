DROP TABLE IF EXISTS curso;
DROP TABLE IF EXISTS usuario;

CREATE TABLE curso  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR NOT NULL,
  ciclo INT NOT NULL,
  idCarrera LONG NOT NULL,
  anio INT NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE leccion  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idCurso INT NOT NULL,
  nombre VARCHAR NOT NULL,
  umbral LONG NOT NULL,
  statusLeccion VARCHAR NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE pregunta  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idLeccion INT NOT NULL,
  descripcion VARCHAR NOT NULL,
  tipo VARCHAR NOT NULL,
  puntaje LONG NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE options  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idPregunta INT NOT NULL,
  descripcion VARCHAR NOT NULL,
  tipo VARCHAR NOT NULL,
  score LONG NOT NULL,
  isCorrectAnswer BOOLEAN NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE matricula  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idAlumno INT NOT NULL,
  ciclo INT NOT NULL,
  descripcion VARCHAR NOT NULL,
  anio INT NOT NULL,
  idCarrera INT NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE detalle_matricula  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  idMatricula INT NOT NULL,
  idCurso INT NOT NULL,
  estadoCurso VARCHAR NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE alumno  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR NOT NULL,
  apellido VARCHAR NOT NULL,
  idCarrera INT NOT NULL,
  estado VARCHAR NOT NULL
);

CREATE TABLE carrera  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nomCarrera VARCHAR NOT NULL,
  estado VARCHAR NOT NULL
);


CREATE TABLE usuario  (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);