<div class="modal fade" id="detalleDesarrollador-modal" tabindex="-1" role="dialog" aria-labelledby="detalleDesarrollador-modalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="detalleDesarrollador-modalLabel">Información del Desarrollo ${detalle.nroOrden} del Proyecto ${detalle.proyecto.descripcion}</h5>
          <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
          </button>
        </div>
        <div class="modal-body">
        <p>${detalle.descripcion}</p>
          <ul class="list-group">
            <g:each in="${pruebas}" var="prueba" status="i">
            <li class="list-group-item list-group-item-info"><b>Prueba ${prueba.nroOrden}</b>: ${prueba.descripcion}</li>
            </g:each>
        </ul>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
        </div>
      </div>
    </div>
  </div>