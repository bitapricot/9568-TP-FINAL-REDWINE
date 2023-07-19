package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RangoServiceSpec extends Specification {

    RangoService rangoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Rango(...).save(flush: true, failOnError: true)
        //new Rango(...).save(flush: true, failOnError: true)
        //Rango rango = new Rango(...).save(flush: true, failOnError: true)
        //new Rango(...).save(flush: true, failOnError: true)
        //new Rango(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //rango.id
    }

    void "test get"() {
        setupData()

        expect:
        rangoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Rango> rangoList = rangoService.list(max: 2, offset: 2)

        then:
        rangoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        rangoService.count() == 5
    }

    void "test delete"() {
        Long rangoId = setupData()

        expect:
        rangoService.count() == 5

        when:
        rangoService.delete(rangoId)
        sessionFactory.currentSession.flush()

        then:
        rangoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Rango rango = new Rango()
        rangoService.save(rango)

        then:
        rango.id != null
    }
}
