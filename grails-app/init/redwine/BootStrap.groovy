package redwine
import groovy.sql.Sql

class BootStrap {
    def dataSource 

    def init = { servletContext ->
        Sql sql = new Sql( dataSource )
        new File( "grails-app/init/redwine/seeding.sql" ).eachLine{ sql.executeInsert it }
        sql.commit()

        // TODO: refactorizar la lectura del archivo de la prueba y moverlo a una RUTINA
        def contenidoArchivo = new File("grails-app/init/redwine/pruebaAutomatizadaSaltarObstaculo.txt").text
        sql = new Sql(dataSource)
        String updateQuery = "UPDATE PRUEBA_AUTOMATIZADA SET CODIGO_INICIAL = ? WHERE ID = 1"
        sql.execute(updateQuery, [contenidoArchivo])
        sql.commit()
        sql.close()
    }
}