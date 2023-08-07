<%@ page import="redwine.Desarrollo" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Desarrollos e Investigaciones</title>
    <!-- Agrega aquí tus enlaces a estilos o scripts si es necesario -->
    <g:render template="../navbar/navbar" />
    <style>
        /* Agrega el estilo para el cursor de clicable */
        .proyecto-item {
            cursor: pointer;
        }
    </style>
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
            <li class="${i == 0 || items[i - 1].completado ? 'list-group-item list-group-item-action proyecto-item' : 'list-group-item list-group-item-action proyecto-item disabled'}" data-link="${item instanceof DesarrolloDetalle ? createLink(controller: 'desarrollo', action: 'show', id: item.id) : createLink(controller: 'investigacion', action: 'show', id: item.id)}">
                ${item.nombre}: ${item.descripcion} 
            </li>
        </g:each>
    </ul>
</div>

<!-- Agrega el script al final del cuerpo del documento -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const proyectoItems = document.querySelectorAll('.proyecto-item');
        proyectoItems.forEach(item => {
            item.addEventListener('click', function() {
                const link = this.dataset.link;
                window.location.href = link;
            });
        });
    });
</script>
</body>
</html>
