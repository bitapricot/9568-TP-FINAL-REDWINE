package redwine

import grails.gorm.services.Service

@Service(PruebaAutomatizada)
interface PruebaAutomatizadaService {

    PruebaAutomatizada get(Serializable id)

    List<PruebaAutomatizada> list(Map args)

    Long count()

    void delete(Serializable id)

    PruebaAutomatizada save(PruebaAutomatizada pruebaAutomatizada)

}