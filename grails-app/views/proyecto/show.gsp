<%@ page import="redwine.Desarrollo" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Desarrollos e Investigaciones</title>
    <!-- Agrega aquí tus enlaces a estilos o scripts si es necesario -->
</head>
<body>
    <h1>Proyecto: ${proyecto.descripcion}</h1>
    <h2>Desarrollos e Investigaciones:</h2>
    <ul>
        <g:each in="${items}" var="item">
            <li>
                <a href="${item instanceof Desarrollo ? createLink(controller: 'desarrollo', action: 'show', id: item.id) : createLink(controller: 'investigacion', action: 'show', id: item.id)}">${item.nombre}</a>
            </li>
        </g:each>
    </ul>
</body>
</html>
