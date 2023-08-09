<!DOCTYPE html>
<html>
    <head>
        <title>Ejecutar Código Desarrollador</title>
        <%-- Modales & Toasts --%>
        <g:render template="../navbar/navbar" model="[desarrollador: desarrolladorId, puntosInvestigacion: puntosInvestigacion, rango: desarrolladorRango]" />
        <g:render template="../toasts/investigacionCompleta-toast" model="[investigacionId: investigacion.id, puntajeOtorgado: puntajeOtorgado]" />
        <g:render template="../toasts/investigacionFallida-toast" model="[investigacionId: investigacion.id]" />
    </head>

    <body>
        <div class="container mt-5">
            <div class="row gx-5 justify-content-between">
                <div>
                    <div class="input-group">
                        <h4>Investigación: ${investigacion.nombre}</h4>
                    </div>
                        <div class="card">
                            <div class="card-body">
                                <div id="carouselExampleControls" class="carousel carousel-dark slide">
                                    <div class="carousel-inner text-center">
                                        <g:each in="${preguntas}" var="pregunta" status="i">
                                            <div class="carousel-item ${i == 0 ? 'active' : ''}">
                                            <h3>${pregunta.descripcion}</h3>
                                                <form>
                                                    <ul class="list-group">
                                                        <g:each in="${pregunta.respuestas}" var="respuesta">
                                                            <li class="list-group-item list-group-item-action">
                                                                <input type="radio" name="respuesta-${respuesta.preguntaId}" value="${respuesta.id}">
                                                                ${respuesta.descripcion}
                                                            </li>
                                                        </g:each>
                                                    </ul>
                                                </form>
                                            </div>
                                        </g:each>
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
            
            <div class="col input-group mt-2 d-flex align-items-center">
                <a class="btn btn-primary btn-sm" id="volver" href="${createLink(controller: 'proyecto', action: 'show', id: 1)}">Volver</a>
                <button class="ms-2 btn btn-primary btn-sm" id="confirmar">Confirmar</button>
                <%-- <a hidden class="btn btn-success btn-lg" id="continuar" href="${createLink(controller: 'proyecto', action: 'show', id: 1)}">
                <i hidden id="continuarIcon" class="mt-1 ms-1 fas fa-forward"></i>
                Continuar</a> --%>
            </div>  
        </div>



<script>
    document.getElementById('confirmar').addEventListener('click', function() {
        const respuestas = [];

        // Obtén todos los elementos de tipo radio seleccionados
        const radioButtons = document.querySelectorAll('input[type="radio"]:checked');

        // Itera sobre los radio buttons seleccionados
        radioButtons.forEach(radioButton => {
            // Obtiene el nombre del radio button (formato "respuesta-{preguntaId}")
            const nameParts = radioButton.name.split('-');
            if (nameParts.length === 2 && nameParts[0] === 'respuesta') {
                const preguntaId = nameParts[1];
                const respuestaId = radioButton.value;
                respuestas.push({ preguntaId, respuestaId });
            }
        });

        // Crea un objeto con los datos a enviar
        const data = { respuestas, investigacionId: ${investigacion.id} };

        // Envía los datos al controlador utilizando fetch
        fetch('${createLink(controller: "investigacion", action: "procesarRespuestas")}', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            // Puedes manejar la respuesta del controlador aquí si es necesario
            console.log(data);
            if (data.investigacionOk) $("#investigacionCompleta-toast").addClass("show");
            else $("#investigacionFallida-toast").addClass("show");

        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
</script>
    </body>
</html>

<%-- <script>
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
    });
</script> --%>