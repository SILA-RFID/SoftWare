<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal fade" tabindex="-1" role="dialog" id="modalCriarPermissao">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Adicionar Permissão</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
					<div class="col-sm-12 col-md-6 col-lg-4">
						<label for="add_per_user"><b>Usuário:</b></label>
						<select id="add_per_user" name="add_per_user" class="form-control" onchange="verificarCadastro('U', this.value)">
							<option value="0">Selecione</option>
							<c:forEach items="${dados.USER}" var="r">
								<option value="${r.id}">${r.nome} (${r.matricula})</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-6 col-lg-4">
						<label for="add_per_let"><b>Leitora:</b></label>
						<select id="add_per_let" name="add_per_let" class="form-control">
							<option value="0">Selecione</option>
							<c:forEach items="${dados.LET}" var="r">
								<option value="${r.id}">${r.descricao}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-6 col-lg-4">
						<label for="add_per_cra"><b>Crachá:</b></label>
						<select id="add_per_cra" name="add_per_cra" class="form-control" onchange="verificarCadastro('C', this.value)">
							<option value="0">Selecione</option>
							<c:forEach items="${dados.CRA}" var="r">
								<option value="${r.id}">${r.nome}</option>
							</c:forEach>
						</select>
					</div>
					
					
					<div class="col-sm-12 col-md-6 col-lg-4">
						<label for="per_dia"><b>Dia Semana:</b></label>
						
						<div class="col-12">
						 	<input class="form-check-input" type="checkbox" value="2" id="flexCheckDefault">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Segunda-Feira
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="3" id="flexCheckDefault1">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Terça-Feira
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="4" id="flexCheckDefault2">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Quarta-Feira
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="5" id="flexCheckDefaul3">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Quinta-Feira
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="6" id="flexCheckDefaul4">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Sexta-Feira
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="7" id="flexCheckDefaul5">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Sábado
					 		</label>
				 		</div>
				 		
				 		<div class="col-12">
					 		<input class="form-check-input" type="checkbox" value="1" id="flexCheckDefaul6">
				  			<label class="form-check-label" for="flexCheckDefault">
					    		Domingo
					 		</label>
				 		</div>
					</div>
				</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarPermissao()">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

<script>
function verificarCadastro(tipo, valor){
	$("#add_per_user").attr("disabled", false);
	$("#add_per_cra").attr("disabled", false);
	
	if(tipo == 'U' && valor != 0){
		$("#add_per_cra").attr("disabled", true);
	} 
	
	if (tipo == 'C' && valor != 0){
		$("#add_per_user").attr("disabled", true);
	}
}
</script>