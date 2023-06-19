<div class="modal fade" tabindex="-1" role="dialog" id="modalCriarUsuario">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Criar Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-nome"><b>Nome:</b></label>
        		<input type="text" class="form-control" id="add-nome" name="add-nome" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-8">
        		<label for="add-email"><b>Email:</b></label>
        		<input type="email" class="form-control" id="add-email" name="add-email" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-telefone"><b>Telefone:</b></label>
        		<input type="text" class="form-control" id="add-telefone" name="add-telefone" placeholder="19 9999999999" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-data"><b>Data Nascimento:</b></label>
        		<input type="date" class="form-control" id="add-data" name="add-data" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-matricula"><b>Matrícula:</b></label>
        		<input type="text" class="form-control" id="add-matricula" name="add-matricula" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-cracha"><b>Crachá:</b></label>
        		<input type="text" class="form-control" id="add-cracha" name="add-cracha" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="add-obs"><b>Observações:</b></label>
        		<textarea class="form-control" id="add-obs" name="add-obs"></textarea>
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarUsuario()">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>