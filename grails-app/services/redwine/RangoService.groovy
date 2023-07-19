package redwine

import grails.gorm.services.Service

@Service(Rango)
interface RangoService {

    Rango get(Serializable id)

    List<Rango> list(Map args)

    Long count()

    void delete(Serializable id)

    Rango save(Rango rango)

}