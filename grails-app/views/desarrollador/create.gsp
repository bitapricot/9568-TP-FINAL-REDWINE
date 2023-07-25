<!DOCTYPE html>
<html>
<head>
    <title>Ejecutar Código Desarrollador</title>
</head>
<body>
    <h1>Ejecutar Código Desarrollador</h1>
    <textarea id="codigoDesarrollador" rows="10" cols="50">
// Aquí ingresa tu código desarrollador
def sum(a, b) {
    return a + b;
}
    </textarea>
    <br>
    <button onclick="ejecutarCodigo()">Ejecutar Código</button>

    <script>
        function ejecutarCodigo() {
            // Obtener el código del usuario desde el textarea
            const codigo = document.getElementById('codigoDesarrollador').value;

            // Construir el objeto con el código a enviar
            const data = {
                codigo: codigo
            };

            // Realizar la solicitud HTTP POST al endpoint del controlador
            fetch('http://localhost:8080/progresoDesarrollador/ejecutarCodigoDesarrollador', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(resultado => {
                // Manejar el resultado obtenido
                console.log(resultado);
                // Aquí puedes hacer lo que necesites con el resultado,
                // como mostrarlo en el frontend o procesarlo de alguna otra manera.
            })
            .catch(error => {
                // Manejar cualquier error que ocurra durante la solicitud
                console.error('Error al ejecutar el código:', error);
            });
        }
    </script>
</body>
</html>
