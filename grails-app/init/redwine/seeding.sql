-- RANGOS
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (1, 0, 1000, 'Sin Rango')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (2, 0, 25000, 'Trainee')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (3, 0, 100000, 'Junior')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (4, 0, 500000, 'Semi Senior')
INSERT INTO RANGO (ID, VERSION, PUNTAJE_MAXIMO, DESCRIPCION) VALUES (5, 0, 1000000, 'Senior')

-- DESARROLLADOR (INICIALIZADO CON 400 PUNTOS DE INVESTIGACION PARA HISTORIA DE USUARIO SOLICITADA)
INSERT INTO DESARROLLADOR (ID, VERSION, RANGO_ID, PUNTOS_INVESTIGACION) VALUES (1, 0, 1, 400)

-- PROYECTO
INSERT INTO PROYECTO (ID, VERSION, DESCRIPCION) VALUES (1, 0, 'The Tales of Lezda')

-- DESARROLLO
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT, CODIGO_INICIAL) VALUES (1, 0, 1, 200, 1, 'Saltar Obstáculo', 'URL se encuentra con un vacío en su camino. Sabiendo que ese camino está representado como un vector, donde cada posición del mismo está representada por un número entero, se pide completar la función saltarObstaculo. De esta manera, URL podrá cambiar su posición actual 2 posiciones más adelante, pudiendo así saltar el obstáculo.', ' ', ' ', ' ')
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT, CODIGO_INICIAL) VALUES (2, 0, 1, 200, 2, 'Saltar Doble', 'URL se encuentra con un vacío, el doble de grande que el anterior, en su camino. Sabiendo que ese camino está representado como un vector, donde cada posición del mismo está representada por un número entero, se pide completar la función saltarObstaculo. De esta manera, URL podrá efectuar un salto más grande y avanzar 3 posiciones, pudiendo así saltar los dos obstáculos contiguos.', ' ', ' ', ' ')
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT, CODIGO_INICIAL) VALUES (3, 0, 1, 200, 3, 'Atacar', 'URL se encuentra con un enemigo en su camino.', ' ', ' ', ' ')
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT, CODIGO_INICIAL) VALUES (4, 0, 1, 200, 4, 'Atacar a Distancia', 'URL se encuentra con un enemigo volador en su camino.', ' ', ' ', ' ')
INSERT INTO DESARROLLO (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION, ANIMACION_HTML, ANIMACION_SCRIPT, CODIGO_INICIAL) VALUES (5, 0, 1, 200, 5, 'Defender Horda', 'URL se encuentra con varios enemigos en su camino.', ' ', ' ', ' ')

-- PRUEBA_AUTOMATIZADA
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (1, 0, 1, '', 'URL está en la posición 1 y el obstáculo está en la posición 2 entonces al saltar, URL queda en la posición 3', 1) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (2, 0, 1, '', 'URL está en la posición 2 y el obstáculo está en la posición 3 entonces al saltar, URL queda en la posición 4', 2) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (3, 0, 1, '', 'URL está en la posición 10 y el obstáculo está en la posición 11 entonces al saltar, URL queda en la posición 12', 3) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (4, 0, 2, '', 'URL está en la posición 1 y hay 2 obstáculos posición 2 y 3 entonces al saltar, URL queda en la posición 4', 1) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (5, 0, 2, '', 'URL está en la posición 2 y hay 2 obstáculos posición 3 y 4 entonces al saltar, URL queda en la posición 5', 2) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (6, 0, 2, '', 'URL está en la posición 10 y hay 2 obstáculos posición 11 y 12 entonces al saltar, URL queda en la posición 13', 3) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (7, 0, 3, '', 'Atacar Prueba 1', 1) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (8, 0, 3, '', 'Atacar Prueba 2', 2) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (9, 0, 3, '', 'Atacar Prueba 3', 3) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (10, 0, 4, '', 'Atacar a Distancia Prueba 1', 1) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (11, 0, 4, '', 'Atacar a Distancia Prueba 2', 2) 
INSERT INTO PRUEBA_AUTOMATIZADA (ID, VERSION, DESARROLLO_ID, CODIGO_INICIAL, DESCRIPCION, NRO_ORDEN) VALUES (12, 0, 4, '', 'Atacar a Distancia Prueba 3', 3) 

-- TO-DO: INSERTAR PISTA POST MODELADO
INSERT INTO PISTA (ID, VERSION, DESARROLLO_ID, COSTO, DESCRIPCION) VALUES (1, 0, 1, 250, 'En un vector, cada posición es un número entero por lo cual para que URL llegue a la posición deseada es cuestión de avanzar sobre el mismo la cantidad de posiciones necesarias.')
INSERT INTO PISTA (ID, VERSION, DESARROLLO_ID, COSTO, DESCRIPCION) VALUES (2, 0, 4, 250, 'Para atacar a un enemigo volador, URL debería lanzar el ataque de tal forma que impacte en la misma posición del enemigo.')

-- INVESTIGACIONES, PREGUNTAS, RESPUESTAS
INSERT INTO INVESTIGACION (ID, VERSION, PROYECTO_ID, PUNTAJE_OTORGADO, NRO_ORDEN, NOMBRE, DESCRIPCION) VALUES (1, 0, 1, 150, 4, 'Investigación 1', 'Acá poner una descripción para la Investigación')

INSERT INTO PREGUNTA (ID, VERSION, DESCRIPCION, INVESTIGACION_ID) VALUES (1, 0, '¿Esto es una Pregunta de ejemplo?', 1)
INSERT INTO PREGUNTA (ID, VERSION, DESCRIPCION, INVESTIGACION_ID) VALUES (2, 0, '¿Esto es otra Pregunta de ejemplo?', 1)

INSERT INTO RESPUESTA (ID, VERSION, DESCRIPCION, PREGUNTA_ID, ES_CORRECTA) VALUES (1, 0, 'Respuesta A (OK) Pregunta 1', 1, 1)
INSERT INTO RESPUESTA (ID, VERSION, DESCRIPCION, PREGUNTA_ID, ES_CORRECTA) VALUES (2, 0, 'Respuesta B Pregunta 1', 1, 0)
INSERT INTO RESPUESTA (ID, VERSION, DESCRIPCION, PREGUNTA_ID, ES_CORRECTA) VALUES (3, 0, 'Respuesta A (OK) Pregunta 2', 2, 1)
INSERT INTO RESPUESTA (ID, VERSION, DESCRIPCION, PREGUNTA_ID, ES_CORRECTA) VALUES (4, 0, 'Respuesta B Pregunta 2', 2, 0)