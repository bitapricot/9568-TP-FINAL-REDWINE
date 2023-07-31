package redwine
import groovy.sql.Sql

class BootStrap {
    def dataSource 

    def init = { servletContext ->
        Sql sql = new Sql( dataSource )
        new File( "grails-app/init/redwine/seeding.sql" ).eachLine{ sql.executeInsert it }
        sql.commit()


        def animacionDataDesarrollo1 = new File("grails-app/init/redwine/animacionDataDesarrollo1.txt")
        // TO-DO: refactorizar la lectura del archivo de la prueba y moverlo a una RUTINA
        def contenidoArchivo = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo.txt").text
        sql = new Sql(dataSource)
        String updateQueryPruebaAutomatizada1 = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQueryPruebaAutomatizada1, [contenidoArchivo])

        String updateQueryDesarrollo1 = "UPDATE DESARROLLO SET ANIMACION_DATA = ? WHERE ID = 1"
        sql.execute(updateQueryDesarrollo1, [animacionDataDesarrollo1])
        
        sql.commit()
        sql.close()
    }
}