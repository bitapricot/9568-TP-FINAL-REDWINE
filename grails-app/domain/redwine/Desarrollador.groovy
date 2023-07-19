package redwine

class Desarrollador {
    Rango rango;
    int puntosInvestigacion = 0

    static belongsTo = [rango: Rango]

    static constraints = {
        rango insertable: false, updateable: false
        puntosInvestigacion nullable: false
    }
}
