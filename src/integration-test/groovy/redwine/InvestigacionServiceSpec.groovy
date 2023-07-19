package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class InvestigacionServiceSpec extends Specification {

    InvestigacionService investigacionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Investigacion(...).save(flush: true, failOnError: true)
        //new Investigacion(...).save(flush: true, failOnError: true)
        //Investigacion investigacion = new Investigacion(...).save(flush: true, failOnError: true)
        //new Investigacion(...).save(flush: true, failOnError: true)
        //new Investigacion(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //investigacion.id
    }

    void "test get"() {
        setupData()

        expect:
        investigacionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Investigacion> investigacionList = investigacionService.list(max: 2, offset: 2)

        then:
        investigacionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        investigacionService.count() == 5
    }

    void "test delete"() {
        Long investigacionId = setupData()

        expect:
        investigacionService.count() == 5

        when:
        investigacionService.delete(investigacionId)
        sessionFactory.currentSession.flush()

        then:
        investigacionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Investigacion investigacion = new Investigacion()
        investigacionService.save(investigacion)

        then:
        investigacion.id != null
    }
}
