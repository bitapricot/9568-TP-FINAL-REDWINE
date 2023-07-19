package redwine

import grails.gorm.services.Service

@Service(Investigacion)
interface InvestigacionService {

    Investigacion get(Serializable id)

    List<Investigacion> list(Map args)

    Long count()

    void delete(Serializable id)

    Investigacion save(Investigacion investigacion)

}