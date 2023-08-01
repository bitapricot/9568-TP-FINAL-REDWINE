-- RANGOS
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (1, 0, 1000, 'Sin Rango')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (2, 0, 25000, 'Trainee')

-- VER SI SON NECESARIOS PARA LAS HISTORIAS DE USUARIO SOLICITADAS
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (3, 0, 100000, 'Junior')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (4, 0, 500000, 'Semi Senior')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (5, 0, 1000000, 'Senior')

-- DESARROLLADOR (INICIALIZADO CON 400 PUNTOS DE INVESTIGACION PARA HISTORIA DE USUARIO SOLICITADA)
INSERT INTO DESARROLLADOR (ID, VERSION, RANGO_ID, PUNTOS_INVESTIGACION) VALUES (1, 0, 1, 400)

-- PROYECTO
INSERT INTO PROYECTO (ID, VERSION, DESCRIPCION) VALUES (1, 0, 'The Tales of Lezda')

-- DESARROLLO
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT) VALUES (1, 0, 1, 200, 1, 'Saltar Obstáculo', 'Agregar acá un texto que explique lo que se tiene que desarrollar.', ' ', ' ')

-- PROGRESO DESARROLLADOR (TEMPORAL)
INSERT INTO PROGRESO_DESARROLLADOR (ID, VERSION, DESARROLLADOR_ID, PISTA_USADA, COMPLETADO, DESARROLLO_ID) VALUES (1, 0, 1, 0, 0, 1)
-- PRUEBA_AUTOMATIZADA
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (1, 0, 1, '', 'Prueba 1', 1) 