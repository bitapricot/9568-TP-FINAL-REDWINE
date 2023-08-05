package redwine
import groovy.sql.Sql

class BootStrap {
    def dataSource 

    def init = { servletContext ->
        Sql sql = new Sql( dataSource )
        new File( "grails-app/init/redwine/seeding.sql" ).eachLine{ sql.executeInsert it }
        sql.commit()


        def animacionHtmlDesarrollo1 = new File("grails-app/init/redwine/animacionHtmlDesarrollo1.txt").text
        def animacionScriptDesarrollo1 = new File("grails-app/init/redwine/animacionScriptDesarrollo1.txt").text
        def codigoInicialDesarrollo1 = new File("grails-app/init/redwine/codigoInicialDesarrollo1.txt").text.replace("\n", "")
        // TO-DO: refactorizar la lectura del archivo de la prueba y moverlo a una RUTINA
        def contenidoArchivo = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo.txt").text
        sql = new Sql(dataSource)
        String updateQueryPruebaAutomatizada1 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQueryPruebaAutomatizada1, [contenidoArchivo])

        String updateQueryHtmlDesarrollo1 = "UPDATE DESARROLLO SET ANIMACION_HTML = ? WHERE ID = 1"
        sql.execute(updateQueryHtmlDesarrollo1, [animacionHtmlDesarrollo1])
        
        String updateQueryScriptDesarrollo1 = "UPDATE DESARROLLO SET ANIMACION_SCRIPT = ? WHERE ID = 1"
        sql.execute(updateQueryScriptDesarrollo1, [animacionScriptDesarrollo1])
        
        String updateQueryCodigoInicialDesarrollo1 = "UPDATE DESARROLLO SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQueryCodigoInicialDesarrollo1, [codigoInicialDesarrollo1])

        sql.commit()
        sql.close()
    }
}