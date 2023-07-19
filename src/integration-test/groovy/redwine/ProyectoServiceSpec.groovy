package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProyectoServiceSpec extends Specification {

    ProyectoService proyectoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Proyecto(...).save(flush: true, failOnError: true)
        //new Proyecto(...).save(flush: true, failOnError: true)
        //Proyecto proyecto = new Proyecto(...).save(flush: true, failOnError: true)
        //new Proyecto(...).save(flush: true, failOnError: true)
        //new Proyecto(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //proyecto.id
    }

    void "test get"() {
        setupData()

        expect:
        proyectoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Proyecto> proyectoList = proyectoService.list(max: 2, offset: 2)

        then:
        proyectoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        proyectoService.count() == 5
    }

    void "test delete"() {
        Long proyectoId = setupData()

        expect:
        proyectoService.count() == 5

        when:
        proyectoService.delete(proyectoId)
        sessionFactory.currentSession.flush()

        then:
        proyectoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Proyecto proyecto = new Proyecto()
        proyectoService.save(proyecto)

        then:
        proyecto.id != null
    }
}
