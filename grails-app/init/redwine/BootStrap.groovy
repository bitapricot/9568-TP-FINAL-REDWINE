package redwine
import groovy.sql.Sql

class BootStrap {
    def dataSource 

    def init = { servletContext ->
        Sql sql = new Sql( dataSource )
        new File( "grails-app/init/redwine/seeding.sql" ).eachLine{ sql.executeInsert it }
        sql.commit()
        sql.close()
    }
}