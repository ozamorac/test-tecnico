# Instalacion y configuracion 
.- El proyecto esta desarrollado con Java 11 
.- Utiliza una Base de Datos H2
.- Para ejecutar el proyecto realizar los siqguientes pasos:
   - Descargar el prpyecto en una ruta
   - Ejecutar la Aplicacion
   - Se cargaran datos de pruebas, revisar archivo import.sql de la carpeta resources

# Verificacion y puntos resueltos del reto tecnico
.- Una vez ejecutado el proyecto se deben verificar los postman
   - Ejecutar ek End Point auth para obtener el token, este token debe configurarse en la pestaña Authorization (tipo Bearer)
   - Collections Cursos, CRUD de cursos
   - Collections Leccion, CRUD de lecciones
   - Collections Preguntas, CRUD de preguntas
   - Collections Matricula, se tiene las siguientes end points
     - Obtener cursos x Alumno, se obtienes los cursos para aun alumno, segun la carrera a la que pertenece
     - Registrar Matricula, registra una matricula para un alumno
     - Obtener Matricula x Alumno, se obtiene la matricula de un alumno
   - Collections Evaluacion, se tiene las siguientes end points:
     - Obtener Evaluacion Pendiente, se obtiene la evaluaciond e un alumno de acuerdo a las lecciones de los cursos matriculados

# Condiciones resueltas del reto tecnico
.- CRUD Cursos, hecho
.- CRUD Lecciones, hecho
.- CRUD Preguntas, hecho
.- Obtener una lista de todos los cursos, indicando a cuáles puede acceder el estudiante, hecho
.- Obtener lecciones para un curso, indicando a cuáles puede acceder el estudiante, hecho
.- Obtener detalles de la lección para responder sus preguntas Tomar una lección (para evitar varias solicitudes, pidieron enviar todas las respuestas de una sola vez), solo se hizo la obtencion de las preguntas pero o el registro de las respuestas ni la validacion de estas para determinar un curso aprobado