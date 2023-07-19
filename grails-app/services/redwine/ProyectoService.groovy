package redwine

import grails.gorm.services.Service

@Service(Proyecto)
interface ProyectoService {

    Proyecto get(Serializable id)

    List<Proyecto> list(Map args)

    Long count()

    void delete(Serializable id)

    Proyecto save(Proyecto proyecto)

}