<div class="modal fade" tabindex="-1" role="dialog" id="modalEditarUsuario">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Editar Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-nome"><b>Nome:</b></label>
        		<input type="text" class="form-control" id="edit_add-nome" name="edit_add-nome" value="${dados.PARAMETROS.nome}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-8">
        		<label for="edit_add-email"><b>Email:</b></label>
        		<input type="email" class="form-control" id="edit_add-email" name="edit_add-email" value="${dados.PARAMETROS.email}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-telefone"><b>Telefone:</b></label>
        		<input type="text" class="form-control" id="edit_add-telefone" name="edit_add-telefone" placeholder="19 9999999999" value="${dados.PARAMETROS.telefone}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-data"><b>Data Nascimento:</b></label>
        		<input type="date" class="form-control" id="edit_add-data" name="edit_add-data" value="${dados.PARAMETROS.data}" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-matricula"><b>Matrícula:</b></label>
        		<input type="text" class="form-control" id="edit_add-matricula" name="edit_add-matricula" value="${dados.PARAMETROS.matricula}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-cracha"><b>Crachá:</b></label>
        		<input type="text" class="form-control" id="edit_add-cracha" name="edit_add-cracha" value="${dados.PARAMETROS.cracha}"/>
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="edit_add-obs"><b>Observações:</b></label>
        		<textarea class="form-control" id="edit_add-obs" name="edit_add-obs">${dados.PARAMETROS.obs}</textarea>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarEdicaoUsuario(${dados.PARAMETROS.id})">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>