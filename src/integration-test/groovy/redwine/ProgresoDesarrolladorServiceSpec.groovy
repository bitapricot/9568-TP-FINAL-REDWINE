package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProgresoDesarrolladorServiceSpec extends Specification {

    ProgresoDesarrolladorService progresoDesarrolladorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProgresoDesarrollador(...).save(flush: true, failOnError: true)
        //new ProgresoDesarrollador(...).save(flush: true, failOnError: true)
        //ProgresoDesarrollador progresoDesarrollador = new ProgresoDesarrollador(...).save(flush: true, failOnError: true)
        //new ProgresoDesarrollador(...).save(flush: true, failOnError: true)
        //new ProgresoDesarrollador(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //progresoDesarrollador.id
    }

    void "test get"() {
        setupData()

        expect:
        progresoDesarrolladorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProgresoDesarrollador> progresoDesarrolladorList = progresoDesarrolladorService.list(max: 2, offset: 2)

        then:
        progresoDesarrolladorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        progresoDesarrolladorService.count() == 5
    }

    void "test delete"() {
        Long progresoDesarrolladorId = setupData()

        expect:
        progresoDesarrolladorService.count() == 5

        when:
        progresoDesarrolladorService.delete(progresoDesarrolladorId)
        sessionFactory.currentSession.flush()

        then:
        progresoDesarrolladorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProgresoDesarrollador progresoDesarrollador = new ProgresoDesarrollador()
        progresoDesarrolladorService.save(progresoDesarrollador)

        then:
        progresoDesarrollador.id != null
    }
}
