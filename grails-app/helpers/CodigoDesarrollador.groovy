import groovy.lang.GroovyShell

class CodigoDesarrollador {
    static final int MAX_LONGITUD_CODIGO = 500

    String valor

    CodigoDesarrollador(String codigo) {
        if (!codigo || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.")
        }

        if (codigo.length() > MAX_LONGITUD_CODIGO) {
            throw new IllegalArgumentException("El código no puede superar los ${MAX_LONGITUD_CODIGO} caracteres.")
        }

        if (!esCodigoValido(codigo)) {
            throw new IllegalArgumentException("El código contiene errores de sintaxis.")
        }

        this.valor = codigo
    }

    // Verifica la sintaxis del codigo
    boolean esCodigoValido(String codigo) {
        try {
            GroovyShell shell = new GroovyShell()
            shell.parse(codigo)
            return true
        } catch (Exception e) {
            return false
        }
    }

    @Override
    boolean equals(Object o) {
        if (this == o) return true
        if (!(o instanceof CodigoDesarrollador)) return false

        CodigoDesarrollador otroCodigo = (CodigoDesarrollador) o

        return valor.equals(otroCodigo.valor)
    }


    @Override
    int hashCode() {
        return valor.hashCode()
    }

    @Override
    String toString() {
        return "CodigoDesarrollador(valor: ${valor})"
    }
}
