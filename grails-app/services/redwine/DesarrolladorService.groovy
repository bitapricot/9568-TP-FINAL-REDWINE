package redwine

import grails.gorm.services.Service

@Service(Desarrollador)
interface DesarrolladorService {

    Desarrollador get(Serializable id)

    List<Desarrollador> list(Map args)

    Long count()

    void delete(Serializable id)

    Desarrollador save(Desarrollador desarrollador)

}