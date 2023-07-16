package redwine

class Desarrollador {
    Rango rango;
    int puntosInvestigacion = 0

    static belongsTo = [rango: Rango]

    static constraints = {
        rango nullable: false
        puntosInvestigacion nullable: false
    }
}
