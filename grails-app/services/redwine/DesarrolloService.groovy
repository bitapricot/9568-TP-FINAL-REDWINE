package redwine

import grails.gorm.services.Service

interface DesarrolloService {

    Desarrollo get(Serializable id)

    List<Desarrollo> list(Map args)

    Long count()

    void delete(Serializable id)

    Desarrollo save(Desarrollo desarrollo)
          
    ResultadoDesarrollo ejecutarPruebasAutomatizadasPorDesarrolloId(CodigoDesarrollador codigoDesarrollador, Long desarrolloId)

}