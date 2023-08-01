<!DOCTYPE html>
<html>

<head>
    <title>Ejecutar Código Desarrollador</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <style>
        /* Estilo para escalar el contenedor de la animación */
        #animacionContainer {
            width: 100%;
            transform: scale(0.5);
            /* Ajustar el valor de escala según sea necesario */
        }

        /* Estilo para posicionar el contenedor de la animación */
        #animacionContainer #platform {
            position: relative;
            width: 1000px;
            height: 30px;
            background-color: gray;
            margin-top: 150px;
        }

        /* Estilo para posicionar el personaje */
        #animacionContainer #character {
            position: absolute;
            width: 50px;
            height: 100px;
            background-color: blue;
            bottom: 30px;
        }

        /* Estilo para posicionar el obstáculo */
        #animacionContainer #obstacle {
            position: relative;
            width: 50px;
            height: 30px;
            background-color: white;
            bottom: 0;
            left: 500px;
        }
    </style>
</head>

<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <!-- Contenedor para la animación -->
                <div id="animacionContainer" style="background-color: skyblue !important;">
                    
                </div>
            </div>
            <div class="col-md-6">
                <!-- Contenedor para el input del desarrollador -->
                <h1>Ejecutar Código Desarrollador</h1>
                <textarea id="codigoDesarrollador" rows="10" cols="50">
int saltarObstaculo(int posicionActual) {
    // implementar
}
                </textarea>
                <br>
                <button onclick="ejecutarCodigo()">Ejecutar Código</button>
            </div>
        </div>

        <div class="row mt-5">
            <div class="col">
                <!-- Contenedor para la consola -->
                <h3>Consola</h3>
                <div id="consola" style="border: 1px solid black; height: 200px; overflow-y: scroll;"></div>
            </div>
        </div>
    </div>

    <script>
        function ejecutarAnimacion() {
            gsap.set("#character", { x: 0 });

        // Animación de caminar hacia la derecha
        gsap.to("#character", { duration: 6, x: 450 });

        // Animación del salto
        gsap.to("#character", { duration: 1, x: 500, y: -150, ease: "power2.inOut", delay: 5.5 });

        // Animación para aterrizar después del salto
        gsap.to("#character", { duration: 1, x: 550, y: 0, ease: "power2.inOut", delay: 6 });

        // Detener al personaje al final de la plataforma
        gsap.to("#character", { duration: 6, x: 950, delay: 6.5 });
        }

        function ejecutarCodigo() {
            reiniciarEscenario()
            
            // Obtener el código del usuario desde el textarea
            const codigo = document.getElementById('codigoDesarrollador').value;

            // Construir el objeto con el código a enviar
            const data = {
                codigo: codigo,
                desarrolladorId: 1,
                desarrolloId: 1
            };

            // Realizar la solicitud HTTP POST al endpoint del controlador
            fetch('http://localhost:8080/progresoDesarrollador/ejecutarPruebasAutomatizadas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(resultadoDesarrollo => {
                    if (resultadoDesarrollo.desarrolloOk) {
                        animaciones.ok()
                    }
                    else {
                        animaciones.failed()
                    }
                    // TO-DO: si resultadoDesarrollo.ok => mostramos la animacion feliz
                    // sino la animacion triste
                    // y en todos los casos mostrar los outputs
                    // es importante modificar el json de la respuesta, la respuesta es un resultadoDesarrollo
                })
                .catch(error => {
                    // Manejar cualquier error que ocurra durante la solicitud
                    console.error('Error al ejecutar el código:', error);
                });
        }
    </script>

    <script>
    function reiniciarEscenario() {
        var divContenedor = document.getElementById("animacionContainer");
        var contenidoHTML = unescapeHtml(`${animacionHtml}`);
        divContenedor.innerHTML = contenidoHTML;
    }

    document.addEventListener("DOMContentLoaded", function () {
        reiniciarEscenario()
    });
    
    const animaciones = eval(unescapeHtml(`${animacionScript}`))

    function unescapeHtml(unsafeHtml) {
        return new DOMParser().parseFromString(unsafeHtml, "text/html").documentElement.textContent;
    }
</script>
</body>

</html>