package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DesarrolladorServiceSpec extends Specification {

    DesarrolladorService desarrolladorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Desarrollador(...).save(flush: true, failOnError: true)
        //new Desarrollador(...).save(flush: true, failOnError: true)
        //Desarrollador desarrollador = new Desarrollador(...).save(flush: true, failOnError: true)
        //new Desarrollador(...).save(flush: true, failOnError: true)
        //new Desarrollador(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //desarrollador.id
    }

    void "test get"() {
        setupData()

        expect:
        desarrolladorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Desarrollador> desarrolladorList = desarrolladorService.list(max: 2, offset: 2)

        then:
        desarrolladorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        desarrolladorService.count() == 5
    }

    void "test delete"() {
        Long desarrolladorId = setupData()

        expect:
        desarrolladorService.count() == 5

        when:
        desarrolladorService.delete(desarrolladorId)
        sessionFactory.currentSession.flush()

        then:
        desarrolladorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Desarrollador desarrollador = new Desarrollador()
        desarrolladorService.save(desarrollador)

        then:
        desarrollador.id != null
    }
}
