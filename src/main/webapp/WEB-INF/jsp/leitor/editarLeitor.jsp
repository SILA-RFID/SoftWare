<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal fade" tabindex="-1" role="dialog" id="modalEditarLeitor">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Editar Leitor</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_let_descricao"><b>Descrição:</b></label>
        		<input type="text" class="form-control" id="edit_let_descricao" name="edit_let_descricao" value="${dados.PARAMETROS.descricao}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-8">
        		<label for="edit_let_ip"><b>IP:</b></label>
        		<input type="email" class="form-control" id="edit_let_ip" name="edit_let_ip" value="${dados.PARAMETROS.ip}"/>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarEdicaoLeitor(${dados.PARAMETROS.id})">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>