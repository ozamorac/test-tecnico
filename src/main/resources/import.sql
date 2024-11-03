INSERT INTO usuario (username, password) VALUES ('admin', '$2a$10$wndPaH6iC2vFg4CxoguLFeSldb8tf72Ce9kFcocjd/Jq1UaxO1Vda');

--CURSO
INSERT INTO curso(nombre, ciclo, id_carrera, anio, estado) VALUES ('FILOSOFIA I', 1, 1, 2023, 'ACTIVO');
INSERT INTO curso(nombre, ciclo, id_carrera, anio, estado) VALUES ('LITERATURA BASICA', 3, 1, 2023, 'ACTIVO');
INSERT INTO curso(nombre, ciclo, id_carrera, anio, estado) VALUES ('MATEMATICA BASICA', 1, 2, 2024, 'ACTIVO');
INSERT INTO curso(nombre, ciclo, id_carrera, anio, estado) VALUES ('FISICA 2', 3, 2, 2024, 'ACTIVO');

--LECCION
INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (1, 'HISTORIA DE LA FILOSOFIA', 11, null, 'ACTIVO');
INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (1, 'LA FILOSFIA EN LA ACTUALIDAD', 11, null, 'ACTIVO');
INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (1, 'PARADIGMAS DE LA FILOSOFIA', 11, null, 'ACTIVO');

INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (2, 'HISTORIA DE LA LITERATURA', 11, null, 'ACTIVO');
INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (2, 'LA LITERATURA Y SOCIEDAD', 11, null, 'ACTIVO');

INSERT INTO leccion(id_curso, nombre, umbral, status_leccion, estado) VALUES (3, 'ECUACIONES DE PRIMER GRADO', 15, null, 'ACTIVO');

--PREGUNTA
INSERT INTO pregunta(id_leccion, descripcion, tipo, puntaje, estado) VALUES (1, 'MARCAR VERDADERO O FALSO', 'BOOL', 10, 'ACTIVO');
INSERT INTO pregunta(id_leccion, descripcion, tipo, puntaje, estado) VALUES (1, 'QUE PARTE DE FILOSFIA IMPLICA EL PENSAMIENTO FILOSOFICO. MARCAR LA RESPUESTA CORRECTA', 'OM1ANSWER', 10, 'ACTIVO');
INSERT INTO pregunta(id_leccion, descripcion, tipo, puntaje, estado) VALUES (2, 'SE DEFINE COMO EL PENSAMIENTO HOLISTICO. MARCAR LAS RESPUESTAS CORRECTAS', 'OMMANSWER', 10, 'ACTIVO');
INSERT INTO pregunta(id_leccion, descripcion, tipo, puntaje, estado) VALUES (2, 'LA FILOSFIA APLICA A VARIAS SOCIEDADES. MARCAR LAS RESPUESTAS CORRECTA', 'OMMALLNSWER', 10, 'ACTIVO');

--OPCIONES
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (1, 'Opcion 1', 'BOOL', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (1, 'Opcion 2', 'BOOL', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (2, 'Opcion 1', 'OM1ANSWER', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (2, 'Opcion 2', 'OM1ANSWER', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (3, 'Opcion 1', 'OMMANSWER', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (3, 'Opcion 2', 'OMMANSWER', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (4, 'Opcion 1', 'OMMALLNSWER', 5, null, 'ACTIVO');
INSERT INTO options(id_pregunta, descripcion, tipo, score, is_correct_answer, estado) VALUES (4, 'Opcion 2', 'OMMALLNSWER', 5, null, 'ACTIVO');

INSERT INTO alumno(nombre, apellido, id_carrera, estado) VALUES ('JOSE CARLOS', 'PEREZ GANOZA', 1, 'ACTIVO');
INSERT INTO alumno(nombre, apellido, id_carrera, estado) VALUES ('ARTURO EZEQUIEL', 'SANCHEZ PRIETO', 2, 'ACTIVO');

INSERT INTO carrera(nom_carrera, estado) VALUES ('FILOSOFIA', 'ACTIVO');
INSERT INTO carrera(nom_carrera, estado) VALUES ('INGENIERIA INDUSTRIAL', 'ACTIVO');
