<!doctype html>
<html>
<head>
<%-- TO-DO: hacer pantalla de inicio con lista de proyectos y eliminar las vistas que no se usan --%>
    <meta name="layout" content="main"/>
    <title>Redwine</title>
    <g:render template="../navbar/navbar" model="[desarrollador: desarrollador.id, puntosInvestigacion: desarrollador.puntosInvestigacion, rango: desarrollador.rango]" />

</head>
<body>
<%-- <content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.getProperty('grails.profile')}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu dropdown-menu-right">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content> --%>

<div class="svg" role="presentation">
    <%-- <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div> --%>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
        <div class="d-flex justify-content-center">
            <i class="fas fa-wine-glass" style="color: #930b0b; font-size: 8em;"></i>
        </div>

            <h1>¡Bienvenido/a a Redwine!</h1>

            <p>
                El proyecto Redwine es un videojuego que tiene como objetivo poner a prueba las
                habilidades de programación del jugador bajo el rol de Desarrollador. Esto se hará a partir
                de diferentes Proyectos, estando cada uno compuesto por uno o más Desarrollos, y una o
                más Investigaciones. Los proyectos siguen una cierta temática y no son progresivos, por lo
                que podrán ser elegidos por el desarrollador.
            </p>

            <hr>

            <div id="controllers" role="navigation">
                <h3>Proyectos Disponibles:</h3>
                <ul class="list-group">
                    <g:each var="c" in="${proyectos}">
                        <li class="list-group-item list-group-item-warning controller">
                         <a class="btn btn-lg list-group-item-action" href="${createLink(controller: 'proyecto', action: 'show', id: c.id)}">
                            <b>• ${c.descripcion} •</b>
                        </a>
                        </li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>
</div>

</body>
</html>
