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
  <title>Leitores</title>
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
		    	<b>Dados Leitores</b>
	  		</div>
  			<div class="card-body">
    			<div class="row">
    				<div class="col-sm-12 col-md-3 col-lg-2">
						<label for="letStatus"><b>Status:</b></label>
						<select id="letStatus" name="letStatus" class="form-control">
							<option value="">Todos</option>
							<option value="S">Ativos</option>
							<option value="N">Desativos</option>
						</select>
					</div>
				</div>
  			</div>
  			<div class="card-footer text-muted">
    			<button class="button-login btn btn-sm btn-success" style="cursor:pointer; margin-right: 10px; margin-top: 10px;" onclick="listarLeitor()">
					<i class="bi bi-search"></i>&nbsp;Pesquisar
				</button>
				<button class="button-login btn btn-sm btn-success" style="cursor:pointer; margin-right: 10px; margin-top: 10px;" onclick="criarLeitor()">
					<i class="bi bi-plus"></i>&nbsp;Novo Leitor
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
function listarLeitor(){
	let params = {
			status: $("#letStatus").val(),
			id: 0
	}
	$("#responseContainer").load("http://localhost:8080/portaria/leitorListar", params);
}

function criarLeitor(){
	$("#modalResponse").load("http://localhost:8080/portaria/criarLeitor", function(){
		$("#modalCriarLeitor").modal("show");
	});
}

function salvarLeitor(){
	if($("#let_descricao").val() == ''){
		alert("Preencha o campo descrição!");
		return;
	}
	if($("#let_ip").val() == ''){
		alert("Preencha o campo IP!");
		return;
	}
	
	let param = {
			descricao: $("#let_descricao").val(),
			ip: $("#let_ip").val()
	}

	$.post("http://localhost:8080/portaria/salvarLeitor", param, function(data){
		if(JSON.parse(data).DATA > 0){
			alert("Sucesso ao criar leitor!");
			$("#modalCriarLeitor").modal("hide");
			listarLeitor();
		} else if(JSON.parse(data).DATA == -2){
			alert("Erro ao processar dados!");
		} else{
			alert("Erro ao criar leitor! Já existe um leitor cadastrado com IP informada!");
		}
	});
}

function ativarInativar(id){
	let param = {
			id:id
	}
	
	$.post("http://localhost:8080/portaria/alterarLeitor", param, function(data){
		if(JSON.parse(data).DATA > 0){
			
			alert("Sucesso ao alterar status do leitor!");
		} else{
			alert("Erro ao alterar status do leitor!");
		}
	});
}

function editarLeitor(id, descricao, ip){
	let param = {
			id: id,
			descricao: descricao,
			ip: ip
	}
	
	$("#modalResponse").load("http://localhost:8080/portaria/editarLeitor", param, function(){
		$("#modalEditarLeitor").modal("show");
	});
}

function salvarEdicaoLeitor(id){
	if($("#let_descricao").val() == ''){
		alert("Preencha o campo descrição!");
		return;
	}
	if($("#let_ip").val() == ''){
		alert("Preencha o campo IP!");
		return;
	}
	
	let param = {
			descricao: $("#edit_let_descricao").val(),
			ip: $("#edit_let_ip").val(),
			id: id
	}

	$.post("http://localhost:8080/portaria/salvarEdicaoLeitor", param, function(data){
		if(JSON.parse(data).DATA > 0){
			alert("Sucesso ao editar leitor!");
			$("#modalEditarLeitor").modal("hide");
			listarLeitor();
		} else{
			alert("Erro ao editar leitor!");
		} 
	});
}
</script>
</html>