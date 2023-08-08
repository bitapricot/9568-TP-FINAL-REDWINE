<div class="modal fade" id="reiniciarProgreso-modal" tabindex="-1" role="dialog" aria-labelledby="reiniciarProgreso-modalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="reiniciarProgreso-modalLabel">Reiniciar Desarrollo</h5>
          <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div id="reiniciarProgreso-modalBody" class="modal-body">
            <p>¿Estás seguro/a de que deseas reiniciar el Desarrollo ${desarrolloId}?</p>
        </div>
        <div class="modal-footer">
          <button id="reiniciarProgreso-modalAceptarButton" onclick="aceptar()" type="button" data-dismiss="modal" class="btn btn-primary">Aceptar</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        </div>
      </div>
    </div>
</div>

<script>
function aceptar() {
    document.dispatchEvent(new Event('reiniciarOk'))
}
</script>