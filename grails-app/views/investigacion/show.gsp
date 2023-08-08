<!DOCTYPE html>
<html>
    <head>
        <title>Ejecutar Código Desarrollador</title>
        <%-- Modales & Toasts --%>
        <g:render template="../navbar/navbar" model="[desarrollador: desarrolladorId, puntosInvestigacion:puntosInvestigacion, rango: desarrolladorRango]" />
        <g:render template="../toasts/investigacionCompleta-toast" model="[investigacionId: investigacion.id]" />
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
                                                            <input type="radio" name="respuesta-${pregunta.id}" value="${respuesta.id}">
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
                <a hidden class="btn btn-success btn-lg" id="continuar" href="${createLink(controller: 'proyecto', action: 'show', id: 1)}">
                <i hidden id="continuarIcon" class="mt-1 ms-1 fas fa-forward"></i>
                Continuar</a>
            </div>  
        </div>

        <script>
            $(document).ready(function() {
                // Desactivar el movimiento automático del carrusel
                $('#carouselExampleControls').carousel({
                    interval: false
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