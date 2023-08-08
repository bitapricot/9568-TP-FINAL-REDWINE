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
        
        def animacionHtmlDesarrollo2 = new File("grails-app/init/redwine/animacionHtmlDesarrollo2.txt").text
        def animacionScriptDesarrollo2 = new File("grails-app/init/redwine/animacionScriptDesarrollo2.txt").text
        
        def codigoInicialDesarrollo2 = new File("grails-app/init/redwine/codigoInicialDesarrollo2.txt").text.replace("\n", "")
        def codigoInicialDesarrollo3 = new File("grails-app/init/redwine/codigoInicialDesarrollo3.txt").text.replace("\n", "")
        // TO-DO: refactorizar la lectura del archivo de la prueba y moverlo a una RUTINA
        def pruebaAutomatizada1 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo1.txt").text
        def pruebaAutomatizada2 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo2.txt").text
        def pruebaAutomatizada3 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo3.txt").text
        def pruebaAutomatizada4 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarDoble1.txt").text
        def pruebaAutomatizada5 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarDoble2.txt").text
        def pruebaAutomatizada6 = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarDoble3.txt").text
        def pruebaAutomatizada7 = new File("grails-app/init/redwine/pruebaAutomatizadaAtacar1.txt").text
        def pruebaAutomatizada8 = new File("grails-app/init/redwine/pruebaAutomatizadaAtacar2.txt").text
        def pruebaAutomatizada9 = new File("grails-app/init/redwine/pruebaAutomatizadaAtacar3.txt").text
        sql = new Sql(dataSource)

        String updateQueryPruebaAutomatizada1 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQueryPruebaAutomatizada1, [pruebaAutomatizada1])
        String updateQueryPruebaAutomatizada2 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 2"
        sql.execute(updateQueryPruebaAutomatizada2, [pruebaAutomatizada2])
        String updateQueryPruebaAutomatizada3 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 3"
        sql.execute(updateQueryPruebaAutomatizada3, [pruebaAutomatizada3])
        String updateQueryPruebaAutomatizada4 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 4"
        sql.execute(updateQueryPruebaAutomatizada4, [pruebaAutomatizada4])
        String updateQueryPruebaAutomatizada5 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 5"
        sql.execute(updateQueryPruebaAutomatizada5, [pruebaAutomatizada5])
        String updateQueryPruebaAutomatizada6 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 6"
        sql.execute(updateQueryPruebaAutomatizada6, [pruebaAutomatizada6])
        String updateQueryPruebaAutomatizada7 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 7"
        sql.execute(updateQueryPruebaAutomatizada7, [pruebaAutomatizada7])
        String updateQueryPruebaAutomatizada8 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 8"
        sql.execute(updateQueryPruebaAutomatizada8, [pruebaAutomatizada8])
        String updateQueryPruebaAutomatizada9 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 9"
        sql.execute(updateQueryPruebaAutomatizada9, [pruebaAutomatizada9])

        String updateQueryHtmlDesarrollo1 = "UPDATE DESARROLLO SET ANIMACION_HTML = ? WHERE ID = 1"
        sql.execute(updateQueryHtmlDesarrollo1, [animacionHtmlDesarrollo1])
        
        String updateQueryScriptDesarrollo1 = "UPDATE DESARROLLO SET ANIMACION_SCRIPT = ? WHERE ID = 1"
        sql.execute(updateQueryScriptDesarrollo1, [animacionScriptDesarrollo1])
        
        String updateQueryHtmlDesarrollo2 = "UPDATE DESARROLLO SET ANIMACION_HTML = ? WHERE ID = 2"
        sql.execute(updateQueryHtmlDesarrollo2, [animacionHtmlDesarrollo2])
        
        String updateQueryScriptDesarrollo2 = "UPDATE DESARROLLO SET ANIMACION_SCRIPT = ? WHERE ID = 2"
        sql.execute(updateQueryScriptDesarrollo2, [animacionScriptDesarrollo2])
        
        String updateQueryCodigoInicialDesarrollo1 = "UPDATE DESARROLLO SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQueryCodigoInicialDesarrollo1, [codigoInicialDesarrollo1])

        String updateQueryCodigoInicialDesarrollo2 = "UPDATE DESARROLLO SET CODIGO_INICIAL = ? WHERE ID = 2"
        sql.execute(updateQueryCodigoInicialDesarrollo2, [codigoInicialDesarrollo2])

        String updateQueryCodigoInicialDesarrollo3 = "UPDATE DESARROLLO SET CODIGO_INICIAL = ? WHERE ID = 3"
        sql.execute(updateQueryCodigoInicialDesarrollo3, [codigoInicialDesarrollo3])

        sql.commit()
        sql.close()
    }
}