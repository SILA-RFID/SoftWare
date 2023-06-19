<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <title>Permissões</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="http://localhost:8080/portaria/">Acesso Portaria</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/portaria/usuario">Usuário</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/portaria/permissao">Permissões</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/portaria/leitor">Leitor</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/portaria/cracha">Crachá</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://localhost:8080/portaria/log">Logs</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

	<div class="container-fluid mt-3">
  		<div class="card">
  			<div class="card-header" style="background-color: #343A40 !important; color:white !important">
		    	<b>Dados Permissões</b>
		    </div>
  			<div class="card-body">
    			<div class="row">
					<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="per_user"><b>Usuário:</b></label>
						<select id="per_user" name="per_user" class="form-control">
							<option value="0">Todos</option>
							<c:forEach items="${dados.USER}" var="r">
								<option value="${r.id}">${r.nome} (${r.matricula})</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="per_let"><b>Leitora:</b></label>
						<select id="per_let" name="per_let" class="form-control">
							<option value="0">Todos</option>
							<c:forEach items="${dados.LET}" var="r">
								<option value="${r.id}">${r.descricao}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="per_cra"><b>Crachá:</b></label>
						<select id="per_cra" name="per_cra" class="form-control">
							<option value="0">Todos</option>
							<c:forEach items="${dados.CRA}" var="r">
								<option value="${r.id}">${r.nome}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="per_status"><b>Status:</b></label>
						<select id="per_status" name="per_status" class="form-control">
							<option value="">Todos</option>
							<option value="S">Ativos</option>
							<option value="N">Desativos</option>
						</select>
					</div>
					
					<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="per_dia"><b>Dia Semana:</b></label>
						<select id="per_dia" name="per_dia" class="form-control">
							<option value="">Todos</option>
							<option value="2">Segunda-feira</option>
							<option value="3">Terça-feira</option>
							<option value="4">Quarta-feira</option>
							<option value="5">Quinta-feira</option>
							<option value="6">Sexta-feira</option>
							<option value="7">Sábado</option>
							<option value="1">Domingo</option>
						</select>
					</div>
				</div>
  			</div>
  			<div class="card-footer text-muted">
    			<button class="button-login btn btn-sm btn-success" style="cursor:pointer; margin-right: 10px; margin-top: 10px;" onclick="listarPermissao()">
					<i class="bi bi-search"></i>&nbsp;Pesquisar
				</button>
				<button class="button-login btn btn-sm btn-success" style="cursor:pointer; margin-right: 10px; margin-top: 10px;" onclick="criarPermissao()">
					<i class="bi bi-plus"></i>&nbsp;Nova Permissão
				</button>
  			</div>
		</div>
	
		<div id="modalResponse"></div>
		<div id="responseContainer" class="mt-3"></div>	
	</div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>

<script>
function listarPermissao(){
	let param = {
			diaSemana: $("#per_dia").val(),
			status: $("#per_status").val(),
			usuario:$("#per_user").val(),
			leitora: $("#per_let").val(),
			cracha: $("#per_cra").val()
	}
	$("#responseContainer").load("http://localhost:8080/portaria/permissaoListar", param);
}

function criarPermissao(){
	$("#modalResponse").load("http://localhost:8080/portaria/criarPermissao", function(){
		$("#modalCriarPermissao").modal("show");
	});
}

function salvarPermissao(){
	if($("#add_per_user").val() == 0 && $("#add_per_cra").val() == 0){
		alert("Selecione um usuário ou um crachá!");
		return;
	}
	
	if($("#add_per_let").val() == 0){
		alert("Selecione uma leitora!");
		return;
	}
	
	let dias = ''
	$(".form-check-input:checked").each(function(index, element){
		dias += element.value + ',';
	});
	
	if(dias == ''){
		alert("Selecione ao menos 1 dia!");
		return;
	}

	
	let param = {
			usuario: $("#add_per_user").val(),
			cracha: $("#add_per_cra").val(),
			leitora: $("#add_per_let").val(),
			diaSemana: dias
	}
	
	$.post("http://localhost:8080/portaria/salvarPermissao", param, function(data){
		if(JSON.parse(data).DATA > 0){
			alert("Sucesso ao adicionar permissão!");
			$("#modalCriarPermissao").modal("hide");
			listarPermissao();
		} else if(JSON.parse(data).DATA == -2){
			alert("Erro ao processar dados!");
		} else{
			alert("Erro ao adiconar permissão! Já existe a permissão para essa leitora!");
		}
	});
}

function ativarInativar(id){
	let param = {
			id:id
	}
	
	$.post("http://localhost:8080/portaria/alterarPermissao", param, function(data){
		if(JSON.parse(data).DATA > 0){
			
			alert("Sucesso ao alterar status da permissão!");
		} else{
			alert("Erro ao alterar status da permissão!");
		}
	});
}

function editarPermissao(id, diasSemana){
	let param = {
			id: id,
			diasSemana: diasSemana
	}
	
	$("#modalResponse").load("http://localhost:8080/portaria/editarPermissao", param, function(){
		$("#modalEditarPermissao").modal("show");
	});
}

function salvarEdicaoPermissao(id){

	let dias = ''
	$("#modalEditarPermissao .form-check-input:checked").each(function(index, element){
		dias += element.value + ',';
	});
	
	if(dias == ''){
		alert("Selecione ao menos 1 dia!");
		return;
	}
	
	let param = {
			diasSemana: dias,
			id: id
	}

	$.post("http://localhost:8080/portaria/salvarEdicaoPermissao", param, function(data){
		if(JSON.parse(data).DATA > 0){
			alert("Sucesso ao editar permissão!");
			$("#modalEditarPermissao").modal("hide");
			listarPermissao();
		} else{
			alert("Erro ao editar permissão!");
		} 
	});
}
</script>
</html>