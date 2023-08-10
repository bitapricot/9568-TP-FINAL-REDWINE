<!DOCTYPE html>
<html>
    <head>
        <title>Ejecutar Código Desarrollador</title>
        <%-- Modales & Toasts --%>
        <g:render template="../toasts/desarrolloCompleto-toast" model="[desarrolloId: desarrollo.id, puntajeOtorgado: desarrollo.puntajeOtorgado]" />
        <g:render template="../toasts/desarrolloFallido-toast" model="[desarrolloId: desarrollo.id]" />
        <g:render template="../modales/detalleDesarrollo-modal" model="[detalle: desarrollo, pruebas: pruebasDetalles]" />
        <g:render template="../modales/pista-modal" model="[desarrolloId: desarrollo.id]" />
        <g:render template="../modales/reiniciarProgreso-modal" model="[desarrolloId: desarrollo.id]" />
        <g:render template="../navbar/navbar" model="[desarrollador: desarrolladorId, puntosInvestigacion:puntosInvestigacion, rango: desarrolladorRango]" />

        <style>
            /* Estilo para escalar el contenedor de la animación */
            #animacionContainer {
                width: 100%;
                transform: scale(0.5);
                /* Ajustar el valor de escala según sea necesario */
            }

            .console-style {
                border: 1px solid black;
                height: 150px;
                background-color: black; /* Fondo negro */
                color: #00FF00; /* Texto en blanco */
                font-family: "Courier New", monospace; /* Fuente de estilo de consola */
                padding: 10px; /* Espaciado interior para que no esté demasiado pegado al borde */
            }            

        code[contenteditable]:focus {
            outline: none;
        }
        </style>
    </head>

    <body>
        <div class="container mt-5">
            <div class="row gx-5 justify-content-between">
                <!-- Contenedor para la animación -->
                <%-- TO-DO: Modificar animacion de caida para que no caiga tan abajo del div, que quede inclinado muerto x.x --%>
                <div class="col-4">
                    <div class="input-group">
                        <button class="btn" data-toggle="modal" data-target="#detalleDesarrollador-modal" data-toggle="tooltip" data-placement="bottom" title="Mostrar Información del Desarrollo"><i class="fas fa-info-circle fa-lg"></i></button>
                        <h4>Desarrollo ${desarrollo.nroOrden}: ${desarrollo.nombre}</h4>
                    </div>
                    <div id="animacionContainer">
                        <div id="platform">
                        </div>
                    </div>
                </div>
                <div class="col-5">
                    <!-- Contenedor para el input del desarrollador -->
                    <%-- TO-DO: agregar columna de codigoInicial en Desarrollo para no hardcodearlo --%>
                    <div class="form-group">
                        <h4 class="col">Ejecutar Código</h4>
<pre spellcheck="false">
<code class="language-groovy" id="codigoDesarrollador" contenteditable spellcheck="false">
${codigoInicial}
</code>
</pre>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button class="btn me-2" data-toggle="modal" data-target="#pista-modal" data-toggle="tooltip" data-placement="bottom" title="Mostrar Pista"><i class="fas fa-lightbulb" style="color:gold;"></i></button>
                        <button class="btn me-2" onclick="ejecutarCodigo()" data-toggle="tooltip" data-placement="bottom" title="Ejecutar Código"><i class="fas fa-play" style="color: #17a00d;"></i></button>
                        <button class="btn" data-toggle="modal" data-target="#reiniciarProgreso-modal"><i class="fas fa-undo" data-toggle="tooltip" data-placement="bottom" title="Reiniciar Desarrollo" style="color: #41c1e1;"></i></button> <%-- TO-DO: intentar poner la flecha curva a la izquierda --%>
                    </div>
                </div>
            </div>
            
            <div class="row mt-5">
                <!-- Contenedor para la consola -->
                <div class="col">
                    <h4>Consola</h4>
                    <div id="consola" class="console-style"></div>
                </div>
            </div>

            <%-- TO-DO: cambiar esto a un link que vuelva al listado de desarrollos --%>
            <div class="col input-group mt-2 d-flex align-items-center">
                <a class="btn btn-primary btn-sm" id="volver" href="${createLink(controller: 'proyecto', action: 'show', id: 1)}">Volver</a>
                <a hidden class="btn btn-success btn-lg" id="continuar" href="${createLink(controller: 'proyecto', action: 'show', id: 1)}">
                <i hidden id="continuarIcon" class="mt-1 ms-1 fas fa-forward"></i>
                Continuar</a>
            </div>  
        </div>
    </body>
</html>

<script>
    document.addEventListener('pistaOk', function() {
        obtenerPista();
    });
    document.addEventListener('reiniciarOk', function() {
        reiniciarProgreso();
    });
document.addEventListener('finished', function(event) {
  if(event.detail == 'ok') {
    $("#desarrolloCompleto-toast").addClass("show");
    document.getElementById('continuar').removeAttribute('hidden')
    document.getElementById('volver').setAttribute('hidden', '')
    document.getElementById('continuarIcon').removeAttribute('hidden')
    setTimeout(() => {
        $("#desarrolloCompleto-toast").toast("hide");
    }, 4000);
  }
  else {
    $("#desarrolloFallido-toast").addClass("show");
    setTimeout(() => {
        $("#desarrolloFallido-toast").toast("hide");
    }, 4000);
  }
  
});
document.addEventListener("DOMContentLoaded", function () {
  // Realizar el resaltado de sintaxis en el bloque de código inicial
  Prism.highlightElement(document.getElementById('codigoDesarrollador'));
  reiniciarEscenario()
  
  // Añadir un evento input para resaltar el código a medida que el usuario ingresa nuevo contenido
});

  document.getElementById('codigoDesarrollador').addEventListener('input', function () {
    Prism.highlightElement(this);
  });

    function obtenerPista() {
        // TO-DO: no hardcodear
        const data = {
                desarrolloId: ${desarrollo.id}
            };
        // Realizar la solicitud HTTP POST al endpoint del controlador
            fetch('http://localhost:8080/progresoDesarrollador/obtenerPista', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
                })
                .then(response => { return response.json() })
                .then(res => {
                    // TO-DO: hacer que el button de Cancelar diga "Cerrar" cuando se muestra pista y sea color primary. Cambiar titulo del modal segun lo que se muestre
                    document.getElementById("puntosInvestigacion").innerHTML = res.puntosInvestigacion
                    document.getElementById("pista-modalBody").innerHTML = res.descripcion
                    document.getElementById("pista-modalAceptarButton").setAttribute("hidden","")
                })
    }

    function reiniciarEscenario() {
        var divContenedor = document.getElementById("animacionContainer");
        var contenidoHTML = unescapeHtml(`${animacionHtml}`);
        divContenedor.innerHTML = contenidoHTML;
    }
    function reiniciarProgreso() {
        reiniciarEscenario()
        var codigoDesarrollador = document.getElementById("codigoDesarrollador");
        var codigoInicial = `${codigoInicial}`;
        codigoDesarrollador.textContent = codigoInicial;

        document.getElementById("consola").innerHTML = "";
    }


    function ejecutarCodigo() {
            reiniciarEscenario()
            
            // Obtener el código del usuario desde el textarea
            const codigo = document.getElementById('codigoDesarrollador').innerText;

            // Construir el objeto con el código a enviar
            const data = {
                codigo: codigo,
                desarrolloId: ${desarrollo.id}
            };

            // Realizar la solicitud HTTP POST al endpoint del controlador
            fetch('http://localhost:8080/progresoDesarrollador/ejecutarPruebasAutomatizadas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(resultadoDesarrollo => {
                    if (resultadoDesarrollo.desarrolloOk) {
                        animaciones.ok()
                    } else {
                        animaciones.failed()
                    }

                    mostrarOutput(resultadoDesarrollo.resultadosPruebas)
                })
                .catch(error => {
                    // Manejar cualquier error que ocurra durante la solicitud
                    console.error('Error al ejecutar el código:', error);
                });
        }
    
    const animaciones = eval(unescapeHtml(`${animacionScript}`))

    function unescapeHtml(unsafeHtml) {
        return new DOMParser().parseFromString(unsafeHtml, "text/html").documentElement.textContent;
    }
</script>

<script>
    function mostrarOutput(output) {
        document.getElementById("consola").innerHTML = "";
        let index = 0;

        function mostrarSiguienteOutput() {
            if (index < output.length) {
                const resultado = output[index];
                if (resultado) {
                    var outputActual =
                        "(" +
                        resultado.estado +
                        ") " +
                        resultado.prueba +
                        ": " +
                        resultado.output;
                    document.getElementById("consola").innerHTML +=
                        unescapeHtml(outputActual) + "<br>";
                }
                index++;
                setTimeout(mostrarSiguienteOutput, 2000); // 2 segundos de retraso entre cada output
            }
        }

        mostrarSiguienteOutput();
    }
</script>