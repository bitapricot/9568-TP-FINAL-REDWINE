package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PistaServiceSpec extends Specification {

    PistaService pistaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Pista(...).save(flush: true, failOnError: true)
        //new Pista(...).save(flush: true, failOnError: true)
        //Pista pista = new Pista(...).save(flush: true, failOnError: true)
        //new Pista(...).save(flush: true, failOnError: true)
        //new Pista(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pista.id
    }

    void "test get"() {
        setupData()

        expect:
        pistaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Pista> pistaList = pistaService.list(max: 2, offset: 2)

        then:
        pistaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pistaService.count() == 5
    }

    void "test delete"() {
        Long pistaId = setupData()

        expect:
        pistaService.count() == 5

        when:
        pistaService.delete(pistaId)
        sessionFactory.currentSession.flush()

        then:
        pistaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Pista pista = new Pista()
        pistaService.save(pista)

        then:
        pista.id != null
    }
}
