package redwine

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PruebaAutomatizadaServiceSpec extends Specification {

    PruebaAutomatizadaService pruebaAutomatizadaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PruebaAutomatizada(...).save(flush: true, failOnError: true)
        //new PruebaAutomatizada(...).save(flush: true, failOnError: true)
        //PruebaAutomatizada pruebaAutomatizada = new PruebaAutomatizada(...).save(flush: true, failOnError: true)
        //new PruebaAutomatizada(...).save(flush: true, failOnError: true)
        //new PruebaAutomatizada(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pruebaAutomatizada.id
    }

    void "test get"() {
        setupData()

        expect:
        pruebaAutomatizadaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PruebaAutomatizada> pruebaAutomatizadaList = pruebaAutomatizadaService.list(max: 2, offset: 2)

        then:
        pruebaAutomatizadaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pruebaAutomatizadaService.count() == 5
    }

    void "test delete"() {
        Long pruebaAutomatizadaId = setupData()

        expect:
        pruebaAutomatizadaService.count() == 5

        when:
        pruebaAutomatizadaService.delete(pruebaAutomatizadaId)
        sessionFactory.currentSession.flush()

        then:
        pruebaAutomatizadaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PruebaAutomatizada pruebaAutomatizada = new PruebaAutomatizada()
        pruebaAutomatizadaService.save(pruebaAutomatizada)

        then:
        pruebaAutomatizada.id != null
    }
}
