package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DesarrolloServiceSpec extends Specification {

    DesarrolloService desarrolloService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Desarrollo(...).save(flush: true, failOnError: true)
        //new Desarrollo(...).save(flush: true, failOnError: true)
        //Desarrollo desarrollo = new Desarrollo(...).save(flush: true, failOnError: true)
        //new Desarrollo(...).save(flush: true, failOnError: true)
        //new Desarrollo(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //desarrollo.id
    }

    void "test get"() {
        setupData()

        expect:
        desarrolloService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Desarrollo> desarrolloList = desarrolloService.list(max: 2, offset: 2)

        then:
        desarrolloList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        desarrolloService.count() == 5
    }

    void "test delete"() {
        Long desarrolloId = setupData()

        expect:
        desarrolloService.count() == 5

        when:
        desarrolloService.delete(desarrolloId)
        sessionFactory.currentSession.flush()

        then:
        desarrolloService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Desarrollo desarrollo = new Desarrollo()
        desarrolloService.save(desarrollo)

        then:
        desarrollo.id != null
    }
}
