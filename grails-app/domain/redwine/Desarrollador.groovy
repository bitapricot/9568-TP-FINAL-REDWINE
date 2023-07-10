package redwine

class Desarrollador {
    //public PuntajeExperiencia puntajeExperiencia;
    //public PuntajeInvestigacion puntajeInvestigacion;
    //public PuntajeProgreso puntajeProgreso;
    public List<Proyecto> proyectos; // ver si es necesario tener instancias de estos proyectos
    public Rango rango;

    static constraints = {
    }

    void iniciarProyecto(long proyectoId) {
        // construir relacion desarrollo - proyecto con desarrolloActual y puntaje en 0
    }
    void iniciarDesarrollo(long proyectoId) {
        // repository buscar tabla de relacion desarrollo - proyecto
        // hacer desarrolloActual + 1
    }
}
