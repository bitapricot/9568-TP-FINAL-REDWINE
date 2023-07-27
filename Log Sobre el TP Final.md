[TOC]



# Sobre el TP Final

## Behavior-Driven Development

Es una metodología de desarrollo de software que se centra en la colaboración entre los desarrolladores, los analistas de negocio y los equipos de pruebas. Se basa en la idea de que el software debe ser desarrollado para cumplir con los requisitos y comportamientos deseados por los usuarios y las partes interesadas. Se utilizan descripciones de comportamiento escritas en un lenguaje natural comprensible tanto por los desarrolladores como por los no técnicos. Estas descripciones se llaman "escenarios" y se utilizan como base para las pruebas y la validación del software. También se apoya en el uso de pruebas automatizadas, que se ejecutan regularmente para verificar que el software cumpla con los comportamientos definidos.

## Historia de Usuario vs Test

Una historia de usuario es una descripción corta y concisa de una funcionalidad deseada desde la perspectiva del usuario o cliente del software. Se centra en el valor que esa funcionalidad aportará al usuario final. Las historias de usuario son escritas en un lenguaje natural y suelen seguir una estructura simple como "Como [rol], quiero [funcionalidad] para [beneficio]".

Por otro lado, un test en BDD (también conocido como escenario) es una especificación más detallada de cómo debería comportarse el sistema para cumplir con una historia de usuario específica. Un test en BDD describe los pasos o acciones que se deben tomar y los resultados esperados para verificar que el software cumpla con el comportamiento definido. Estos tests están escritos en un lenguaje más técnico.

En BDD, es común tener varios tests (escenarios) para una historia de usuario. Cada test puede representar un caso de uso diferente o un aspecto específico del comportamiento deseado. Los tests en BDD son modulares y se centran en un solo aspecto o funcionalidad a la vez, lo que permite una mayor claridad y mantenibilidad en las pruebas.

Una historia de usuario es una descripción de alto nivel de una funcionalidad deseada, mientras que un test en BDD es una especificación más detallada de cómo debería comportarse el sistema para cumplir con esa historia de usuario

---

# Modelo ideas

### Proyecto

* Id
* Descripción

*Pensamos no agregar un campo Puntaje a esta entidad ya que sería un campo calculable, cada Desarrollo tiene un Puntaje.*

*Pensamos en una tabla de relación Desarrollador - Rango.*

### Rango

* Id
* Descripción
* PuntajeMaximo

*Los puntos de experiencia no se resetean al pasar de Rango, tienen un 0 relativo al Rango. Esto es, por ejemplo, el 0 de rango Trainee son 1000 puntos de experiencia, uno empieza siendo Trainee con 1000 puntos de experiencia y no 0. Entonces de esta forma los puntos de experiencia de un Desarrollador son calculables, son el resultado de la sumatoria de los puntajes de cada Proyecto completado o, lo que es lo mismo, la sumatoria de los puntajes de progreso de cada Desarrollo.*

### Desarrollo

* Id
* ProyectoId
* PuntajeOtorgado
* NroOrden / Nivel

*Una prueba automatizada forma parte de la capa de Aplicación, por lo tanto no formará parte del modelo de dominio.*

*Los elementos de un escenario idealmente estarían serializados de tal manera que sean genéricos y parametrizables. Consideramos que no es necesario para el prototipo del proyecto, pensamos en hardcodear el escenario con un Servicio.*

### Relación Proyecto - Desarrollador (Progreso)

* Id
* DesarrolladorId
* DesarrolloId
* Completado (bit)
* PistaUsada (bit)

*No agregamos puntaje parcial en esta relación porque sigue siendo calculable al acceder al puntaje individual de cada Desarrollo*.

*Si uno quiere conocer la completitud de un Proyecto, se debe buscar el Progreso tal que el Desarrollo pertenezca al Proyecto buscado, y así consultar su estado. Podríamos normalizar el campo Completado a una tabla tipo ProgresoEstado.*

*Notar que para saber si se puede iniciar un Desarrollo, siempre y cuando no sea el primero, hay que consultar por la completitud del NroOrden / Nivel anterior.*

*En cuanto a las pistas, por el momento consideramos que cada desarrollo tendrá una única pista. Por lo que agregamos la columna booleana PistaUsada para indicar si la misma se utilizó o si aún puede ser utilizada.*

### Investigación

* Id
* ProyectoId
* PuntajeOtorgado
* RutaPreguntas

*Notar que la Investigación no aporta al Progreso del Proyecto, sino que sirve para los Puntos de Investigación del Desarrollador.*

*En principio, pensamos almacenar las preguntas de la Investigación en un Json. También se nos ocurre tener una tabla InvestigacionPreguntas que tenga el InvestigacionId, Pregunta, Respuesta, o algo que sepamos serializar y deserializar desde la aplicación. En última instancia, pensamos replicar el caso del Escenario y tener también un Servicio que tome la ruta desde un archivo de configuración.*

### Desarrollador

* Id
* PuntosInvestigacion
* RangoId

*Los Puntos de Investigación son la única "moneda" del Desarrollador. Es decir, son intercambiables y no dependen de nada. Los otros Puntos, en cambio, dependen de los Desarrollos y del Progreso general en el juego, por lo tanto son calculables y no formarán parte del modelo de dominio.*

*El Progreso del Desarrollador respecto de su Rango es calculable. Se debe hacer la operación $\sum {D_i} - MaxR_{anterior}$ (donde $D_i$ es el puntaje otorgado por un Desarrollo y $MaxR_{anterior}$ es el puntaje máximo del Rango anterior) para obtener el progreso o el puntaje alcanzado para el Rango actual.*

### Pista

* Id
* DesarrolloId
* RutaPistas

---

# Cosas importantes

* Para ver la Db en memoria hay que entrar a http://localhost:8080/h2-console y poner las credenciales según la línea 113 de `application.yml`
* Para seedear datos hay que usar un objeto Sql abriendo el archivo correspondiente. TODO: hacer stored procedure para seedear todos los datos iniciales

---

# Ideas para empezar el desarrollo

1. Completitud del primer desarrollo del proyecto The Tales of Lezda:
   - Crea el modelo `Desarrollo` con los campos necesarios, como `id`, `proyectoId`, `puntajeOtorgado`, etc.
   - Establece la relación entre `Proyecto` y `Desarrollo`, asegurándote de tener la clave foránea adecuada.
   - Implementa las pruebas automatizadas para el desarrollo `saltar_obstaculo()`. Estas pruebas verificarán si el código implementado por el desarrollador cumple con los requisitos y produce los resultados esperados.
   - Implementa la funcionalidad `saltar_obstaculo()` en el desarrollo correspondiente. Asegúrate de que al ejecutar este código, el personaje URL avance en el escenario y salte el obstáculo.
   - Actualiza la relación `Progreso` para registrar la completitud del primer desarrollo y otorgar 200 puntos de progreso al desarrollador.
   - Asegúrate de que el desarrollador pueda iniciar el siguiente desarrollo solo si el primer desarrollo está completado.
2. Reinicio del tercer desarrollo del proyecto The Tales of Lezda:
   - Implementa la funcionalidad que permita reiniciar un desarrollo específico. Esto implica restaurar el código inicial del desarrollo y restablecer el escenario al punto de inicio correspondiente.
   - Actualiza el modelo `Progreso` para reflejar el reinicio del tercer desarrollo. Esto puede implicar cambiar el estado de completitud y restablecer cualquier otra información relacionada con el desarrollo reiniciado.
3. Uso de puntos de investigación:
   - Implementa la funcionalidad que permita al desarrollador usar sus puntos de investigación para obtener pistas en los escenarios de desarrollo.
   - Asegúrate de que el desarrollador pueda solicitar una pista y, si tiene suficientes puntos de investigación, mostrar la pista correspondiente.
   - Actualiza el modelo `Desarrollador` para reflejar la disminución de los puntos de investigación después de usar una pista.

1. Completitud del primer desarrollo del proyecto The Tales of Lezda:
   - Crea las vistas necesarias en tu aplicación para que los desarrolladores puedan ver el escenario y el personaje URL.
   - Cuando el desarrollador inicie el primer desarrollo, muestra el escenario correspondiente y el punto de inicio del personaje URL.
   - Después de que el desarrollador escriba y ejecute el código necesario para implementar `saltar_obstaculo()`, actualiza la vista para mostrar el avance del personaje URL y el salto del obstáculo.
   - Asegúrate de que los cambios en el escenario y el avance del personaje URL se reflejen en tiempo real mientras el desarrollador interactúa con la aplicación.
2. Reinicio del tercer desarrollo del proyecto The Tales of Lezda:
   - Agrega una opción en la interfaz de usuario que permita al desarrollador reiniciar el tercer desarrollo.
   - Cuando el desarrollador seleccione reiniciar, restablece el código del desarrollo a su estado inicial y vuelve a mostrar el escenario y el punto de inicio correspondiente.
   - Asegúrate de que los cambios en el código y el escenario se reflejen en tiempo real para el desarrollador.
3. Uso de puntos de investigación:
   - Agrega una opción en la interfaz de usuario que permita al desarrollador utilizar sus puntos de investigación para obtener pistas.
   - Cuando el desarrollador seleccione obtener una pista, muestra la pista correspondiente en la interfaz de usuario.
   - Actualiza la cantidad de puntos de investigación del desarrollador y asegúrate de que se refleje en la interfaz de usuario.



#### 20230724

Vamos a crear un value object (o no?? en principio una clase) que se llama PruebaAutomatizada, el constructor recibe el id de Desarrollo y el input del usuario, busca el template asociado a ese id y hace el replace del input del usuario. Puede tener un método ejecutar que llama al servicio CodigoDesarrolladorService o que directo haga el Eval.me.

Si pasan las pruebas del service, hay que avanzar en el progreso: llamar al ProgresoDesarrolladorService y hacer la logica de creacion del siguiente ProgresoDesarrollo y marcar como completado el actual, contabilizar puntajes y cualquier logica que surge de consecuencia de completar un desarrollo. 



#### 20230726

Ahora PruebaAutomatizada es una entidad más del dominio, con los campos

* Id
* DesarrolloId
* CodigoInicial
* NroOrden

No será una clase anémica porque va a ser la responsable de hacer lo indicado en 20230724 (evaluación de código / llamada al service CodigoDesarrollador).

Agregamos la columna NroOrden por si hay más de una y se requiere correr en un cierto orden.

