<div class="modal fade" tabindex="-1" role="dialog" id="modalCriarLeitor">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Criar Leitor</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
        	<div class="col-12 col-sm-12 col-md-6 col-lg-4">
        		<label for="let_descricao"><b>Descrição:</b></label>
        		<input type="text" class="form-control" id="let_descricao" name="let_descricao" />
        	</div>
        	<div class="col-12 col-sm-12 col-md-6 col-lg-8">
        		<label for="let_ip"><b>IP:</b></label>
        		<input type="email" class="form-control" id="let_ip" name="let_ip" />
        	</div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarLeitor()">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>