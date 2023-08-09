package redwine
import groovy.json.JsonBuilder
import groovy.json.JsonOutput

class ResultadoObtencionPista {
    String descripcion
    int puntosInvestigacionRestantes
    boolean pistaObtenida // para saber si pudo obtenerse segun puntos de investigacion
    boolean pistaUsada // para saber si ya fue usada

    ResultadoObtencionPista(Pista pista, ProgresoDesarrollador progreso) {
        def resto = progreso.desarrollador.puntosInvestigacion - pista.costo
        def pistaEsObtenible = resto >= 0
        def pistaFueUsada = progreso.pistaUsada
        if (pistaFueUsada) {
            this.pistaUsada = true
            this.pistaObtenida = false
            this.puntosInvestigacionRestantes = progreso.desarrollador.puntosInvestigacion
            this.descripcion = "Ya obtuviste la pista para este Desarrollo: " + pista.descripcion
        }
        else if (pistaEsObtenible) {
            this.pistaUsada = false
            this.pistaObtenida = true
            this.descripcion = pista.descripcion
            this.puntosInvestigacionRestantes = resto
        } 
        else {
            this.descripcion = "No tenés suficientes Puntos de Investigación para obtener la Pista"
            this.pistaUsada = false
            this.pistaObtenida = false
            this.puntosInvestigacionRestantes = progreso.desarrollador.puntosInvestigacion
        }
    }
    
    String serialize() {
        def map = [
                    descripcion: descripcion,
                    puntosInvestigacion: puntosInvestigacionRestantes,
                    pistaObtenida: pistaObtenida
                ]
                
        return JsonOutput.toJson(map)
    }
}