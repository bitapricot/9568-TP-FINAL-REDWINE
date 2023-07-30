// Place your Spring DSL code here
beans = {
    desarrolloService(DesarrolloService) {
        delegate = ref('desarrolloServiceImpl')
    }
}
