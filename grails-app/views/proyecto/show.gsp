<%@ page import="redwine.Desarrollo" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Desarrollos e Investigaciones</title>
    <!-- Agrega aquí tus enlaces a estilos o scripts si es necesario -->
    <g:render template="../navbar/navbar" model="[desarrollador: desarrolladorId, puntosInvestigacion:puntosInvestigacion, rango: desarrolladorRango]" />
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-center">
        <h2>• Proyecto: ${proyecto.descripcion} •</h2>
    </div>
        <hr>
    <h3>Desarrollos e Investigaciones</h3>
    <ul class="list-group">
    <g:each in="${items}" var="item" status="i">
    <%-- Resto 2 a NroOrden para acceder al item anterior. Si NroOrden = 4 el NroOrden anterior es 3 y su posicion en la lista de items es 2 --%>
        <li class="${i == 0 || items[item.nroOrden - 2]?.completado ? 'list-group-item list-group-item-action proyecto-item' : 'list-group-item list-group-item-action proyecto-item disabled-item'}">
            <div class="item-content d-flex justify-content-between">
                ${item.nombre}: ${item.descripcion}
                <g:if test="${item instanceof DesarrolloDetalle}">
                    <g:if test="${item.iniciado && item.completado}">
                        <div>
                            <h6 style="color: green;">
                            <i class="fas fa-check" style="color: green;"></i>
                            Desarrollo Completado</h6>
                        </div>
                    </g:if>
                    <g:else>
                        <a class="btn btn-sm" href="${createLink(controller: 'desarrollo', action: 'show', id: item.id)}" ${i == 0 || items[item.nroOrden - 1]?.completado ? '' : 'disabled'} style="${items[i]?.iniciado ? 'color: black; background-color: #f0ad4e;' : 'color:white; background-color:  #930b0b;'}">
                            <i class="fas fa-play" style="color: green;"></i>
                            ${item.iniciado ? 'Continuar Desarrollo' : 'Iniciar Desarrollo'}
                        </a>
                    </g:else>
                </g:if>
                <g:else>
                    <g:if test="${item.iniciado && item.completado}">
                        <div>
                            <h6 style="color: green;">
                            <i class="fas fa-check" style="color: green;"></i>
                            Investigación Completada</h6>
                        </div>
                    </g:if>
                    <g:else>
                    <a class="btn btn-sm" href="${createLink(controller: 'investigacion', action: 'show', id: item.id)}" ${i == 0 || items[item.nroOrden - 1]?.completado ? '' : 'disabled'} style="${items[i]?.iniciado ? 'color: black; background-color: #f0ad4e;' : 'color:white; background-color:  #930b0b;'}">
                        <i class="fas fa-play" style="color: green;"></i>
                        ${item.iniciado ? 'Continuar Investigación' : 'Iniciar Investigación'}
                    </a>
                    </g:else>
                </g:else>
            </div>
        </li>
    </g:each>
</ul>

<div>
    <a class="btn btn-primary btn-sm" id="volver" href="${createLink(controller: 'proyecto', action: 'index')}">Volver</a>
</div>
</div>

<style>
    /* Cambiar el cursor a predeterminado para elementos deshabilitados */
    .proyecto-item.disabled {
        cursor: default;
    }
    .disabled-item {
        opacity: 0.6; /* Puedes ajustar la opacidad según prefieras */
        pointer-events: none;
    }
</style>

<script>
    // Desactivar el enlace en los elementos deshabilitados
    const items = document.querySelectorAll(".proyecto-item");
    items.forEach((item) => {
        if (item.classList.contains("disabled")) {
            item.querySelector("a").addEventListener("click", (event) => {
                event.preventDefault();
            });
        }
    });
</script>

</body>
</html>
