package redwine
import groovy.sql.Sql

class BootStrap {
    def dataSource 

    def init = { servletContext ->
        Sql sql = new Sql( dataSource )
        new File( "C:\\Users\\camila.bartocci.GLOBALAD\\Documents\\GitHub\\9568-TP-FINAL-REDWINE\\grails-app\\assets\\test.sql" ).eachLine{ sql.executeInsert it }
        sql.commit()
        sql.close()
    }
}