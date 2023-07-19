package redwine

import grails.gorm.services.Service

@Service(Pista)
interface PistaService {

    Pista get(Serializable id)

    List<Pista> list(Map args)

    Long count()

    void delete(Serializable id)

    Pista save(Pista pista)

}