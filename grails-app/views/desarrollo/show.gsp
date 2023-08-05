<!DOCTYPE html>
<html>

<head>
    <title>Ejecutar Código Desarrollador</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/prism/9000.0.1/themes/prism.min.css"
        integrity="sha512-/mZ1FHPkg6EKcxo0fKXF51ak6Cr2ocgDi5ytaTBjsQZIH/RNs6GF6+oId/vPe3eJB836T36nXwVh/WBl/cWT4w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prism/9000.0.1/prism.min.js"
        integrity="sha512-UOoJElONeUNzQbbKQbjldDf9MwOHqxNz49NNJJ1d90yp+X9edsHyJoAs6O4K19CZGaIdjI5ohK+O2y5lBTW6uQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prism/9000.0.1/components/prism-groovy.min.js"
        integrity="sha512-3OjFYZ7G+C5LMT3IHigO6B04Qyw46Q7Symron8imAs1DhS6BMYotqF0nqrHxN5JooRIgoE6uIlN8wg8s89iF8w=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prism/9000.0.1/prism.js"
        integrity="sha512-ahAPBS5R2UFRnj1zW9oY6uEM1cjtmskMh5ZQnfhfR2Rz62wtJeox4kV26PZFEC1isI4d4QjE/8zaayPF88E0Nw=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prettier/3.0.1/standalone.js"
        integrity="sha512-ad6Wxf3CN1YJx8EOG7UwmTl62M6gXrvPjCXZZTi3MjzQhX9cHI4jo2bLH83QvAEYttbIu+HkNW1PDUxEAYEy/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <style>
        /* Estilo para escalar el contenedor de la animación */
        #animacionContainer {
            width: 100%;
            transform: scale(0.5);
            /* Ajustar el valor de escala según sea necesario */
        }

        .console-style {
            border: 1px solid black;
            height: 150px;
            background-color: black;
            /* Fondo negro */
            color: #00FF00;
            /* Texto en blanco */
            font-family: "Courier New", monospace;
            /* Fuente de estilo de consola */
            padding: 10px;
            /* Espaciado interior para que no esté demasiado pegado al borde */
        }

        /* Estilo para el badge con fondo personalizado */
        .badge-custom {
            background-color: #8B0000;
        }

        code {
            white-space: pre;
            /* Muestra el código con el formato original */
            font-family: monospace;
            /* Usa una fuente de ancho fijo para mantener la alineación */
        }

        code[contenteditable]:focus {
            outline: none;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Redwine</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Otra Página</a>
                    </li>
                </ul>
            </div>
            <div class="navbar-text">
                <!-- Información del usuario actual -->
                <span class="text-light mr-3">Desarrollador 1</span>
                <span class="badge badge-pill badge-custom">Trainee</span>
            </div>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="row gx-5 justify-content-between">
            <!-- Contenedor para la animación -->
            <%-- TO-DO: Modificar animacion de caida para que no caiga tan abajo del div, que quede inclinado muerto x.x
                --%>
                <div class="col-4">
                    <h4>Animación</h4>
                    <div id="animacionContainer">
                        <div id="platform">
                        </div>
                    </div>
                </div>
                <div class="col-5">
                    <!-- Contenedor para el input del desarrollador -->
                    <div class="form-group">
                        <h4>Ejecutar Código Desarrollador</h4>
                        <pre>
<code class="language-groovy" id="codigoDesarrollador" contenteditable spellcheck="false">

</code>
</pre>
                    </div>
                    <br>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-primary" onclick="ejecutarCodigo()">Ejecutar</button>
                    </div>
                </div>
        </div>

        <div class="row mt-5">
            <!-- Contenedor para la consola -->
            <div class="col">
                <h4>Consola</h4>
                <div id="consola" class="console-style"></div>
            </div>
        </div>
    </div>


</body>

</html>

<script>
    function reiniciarEscenario() {
        var divContenedor = document.getElementById("animacionContainer");
        var contenidoHTML = unescapeHtml(`${animacionHtml}`);
        divContenedor.innerHTML = contenidoHTML;
    }

    function ejecutarCodigo() {
        reiniciarEscenario()

        // Obtener el código del usuario desde el textarea
        const codigo = document.getElementById('codigoDesarrollador').innerText;

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

                mostrarOutput(resultadoDesarrollo.resultadosPruebas)
            })
            .catch(error => {
                // Manejar cualquier error que ocurra durante la solicitud
                console.error('Error al ejecutar el código:', error);
            });
    }

    document.addEventListener("DOMContentLoaded", function () {
        reiniciarEscenario()
    });


    document.getElementById('codigoDesarrollador').innerHTML = `${codigoInicial}`

    const animaciones = eval(unescapeHtml(`${animacionScript}`))

    function unescapeHtml(unsafeHtml) {
        return new DOMParser().parseFromString(unsafeHtml, "text/html").documentElement.textContent;
    }
</script>

<script>
    function mostrarOutput(output) {
        document.getElementById("consola").innerHTML = "";
        output.forEach(resultado => {
            if (resultado) {
                // TO-DO: darle otro formato al output de consola
                var outputActual = "(" + resultado.estado + ") " + resultado.prueba + ": " + resultado.output;
                document.getElementById("consola").innerHTML += unescapeHtml(outputActual) + "<br>";
            }
        });
    }
</script>