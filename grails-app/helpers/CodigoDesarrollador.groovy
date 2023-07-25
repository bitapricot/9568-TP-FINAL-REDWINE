class CodigoDesarrollador {
    String valor

    CodigoDesarrollador(String codigo) {
        if (!codigo || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.")
        }
        this.valor = codigo
    }
}
