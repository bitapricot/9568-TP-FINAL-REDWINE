package redwine

import grails.gorm.services.Service

@Service(Desarrollo)
interface DesarrolloService {

    Desarrollo get(Serializable id)

    List<Desarrollo> list(Map args)

    Long count()

    void delete(Serializable id)

    Desarrollo save(Desarrollo desarrollo)

}