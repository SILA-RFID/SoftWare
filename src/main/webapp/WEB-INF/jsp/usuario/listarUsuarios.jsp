<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<table class="table table-sm">
  <thead class="" style="background-color: #343A40 !important; color:white !important">
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Email</th>
      <th scope="col">Telefone</th>
      <th scope="col">Matrícula</th>
      <th scope="col">Data Nascimento</th>
      <th scope="col">Crachá</th>
      <th scope="col">Status</th>
      <th scope="col">Observações</th>
      <th scope="col">Ações</th>
    </tr>
  </thead>
  <tbody>
 	<c:if test="${not empty dados.DATA}">
	    <c:forEach items="${dados.DATA}" var="r">
		    <tr>
		      <td scope="col">${r.nome}</td>
		      <td scope="col">${r.email}</td>
		      <td scope="col">${r.telefone}</td>
		      <td scope="col">${r.matricula}</td>
		      <td scope="col">${r.dataNascimentoF}</td>
		      <td scope="col">${r.idCracha == 0 ? ' - ' : r.idCracha}</td>
		      <td scope="col">
		      	<div class="form-check form-switch">
					<input class="form-check-input" type="checkbox" <c:if test="${r.status == 'Ativo'}">checked</c:if> onclick="ativarInativar(${r.id})"/>
				</div>
			  </td>
		      <td scope="col" style="text-align:center;">
		      	<span style="text-align:center; cursor:pointer"><i class="bi bi-info-circle-fill" style="color:black" title="${r.observacao}"></i></span>
		      </td>
		      <td scope="col">
		      	<a href="#" onclick="editarUsuario(${r.id}, '${r.nome}', '${r.email}', '${r.telefone}', '${r.matricula}', '${r.dataNascimento}', ${r.idCracha}, '${r.observacao}')"><i class="bi bi-pencil-square" style="color:black"></i></a>
		      </td>
		    </tr>
	    </c:forEach>
	</c:if>
    <c:if test="${!not empty dados.DATA}">
    	<td scope="col" colspan="5" class="text-center">Não encontramos nenhum dados!</td>
    </c:if>
  </tbody>
</table>