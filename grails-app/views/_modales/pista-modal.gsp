<div class="modal fade" id="pista-modal" tabindex="-1" role="dialog" aria-labelledby="pista-modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="pista-modalLabel">Pista Desarrollo ${desarrolloId}</h5>
          <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div id="pista-modalBody" class="modal-body">
            <p>¿Estás seguro/a de que deseas obtener una Pista para el Desarrollo ${desarrolloId} por <b>250 Puntos de Investigación</b>?</p>
        </div>
        <div class="modal-footer">
          <button id="pista-modalAceptarButton" onclick="aceptar()" type="button" class="btn btn-primary">Aceptar</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        </div>
      </div>
    </div>
</div>

<script>
function aceptar() {
    document.dispatchEvent(new Event('pistaOk'))
}
</script>