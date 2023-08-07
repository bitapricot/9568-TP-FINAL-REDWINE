<%-- Scripts --%>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.9.1/gsap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <div>
            <i class="fas fa-wine-glass fa-lg" style="color: #930b0b;"></i>
            <a class="navbar-brand" href="#">Redwine</a>
        </div>
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
                <li class="nav-item dropdown">
                    <a class="nav-link text-light dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Desarrollador 1
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                        <a><hr class="dropdown-divider"></a>
                        <a class="dropdown-item" href="#">Cerrar Sesión</a>
                    </div>
                </li>
            </ul>
        </div>
                
        <div class="navbar-text d-flex align-items-center">
            <!-- Información del usuario actual -->
            <span style="display: flex; align-items: center;">
                <span id="puntosInvestigacion" style="margin-right: 6px;">${puntosInvestigacion}</span>
                <span style="border-right: 1px solid #aaa; margin-right: 10px; padding-right: 10px;">
                    <i class="fas fa-book-open" data-toggle="tooltip" data-placement="bottom" title="Puntos de Investigación" style="color: #d9b120;"></i>
                </span>
            </span>
            <span style="margin-right: 8px;" class="badge badge-pill badge-custom">Trainee</span>
            <span style="width: 150px;">
                <div class="progress">
                    <div class="progress-bar bg-success" role="progressbar" style="width: 75%;" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100">75%</div>
                </div>
            </span>
        </div>
    </div>
</nav>

<style>
    /* Estilo para el badge con fondo personalizado */
    .badge-custom {
        background-color: #8B0000;
    }
</style>