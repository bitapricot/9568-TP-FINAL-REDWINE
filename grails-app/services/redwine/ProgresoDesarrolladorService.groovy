package redwine

import grails.gorm.services.Service

@Service(ProgresoDesarrollador)
interface ProgresoDesarrolladorService {

    ProgresoDesarrollador get(Serializable id)

    List<ProgresoDesarrollador> list(Map args)

    Long count()

    void delete(Serializable id)

    ProgresoDesarrollador save(ProgresoDesarrollador progresoDesarrollador)

}