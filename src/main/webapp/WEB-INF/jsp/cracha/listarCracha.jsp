<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<table class="table table-sm">
  <thead class="" style="background-color: #343A40 !important; color:white !important">
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Numeração</th>
      <th scope="col">Data</th>
      <th scope="col">Status</th>
    </tr>
  </thead>
  <tbody>
	  <c:if test="${not empty dados.DATA}">
	    <c:forEach items="${dados.DATA}" var="r">
		    <tr>
		      <td scope="col">${r.id}</td>
		      <td scope="col">${r.numeracao}</td>
		      <td scope="col">${r.data}</td>
		      <td scope="col">
			      <div class="form-check form-switch">
				  	<input class="form-check-input" type="checkbox" <c:if test="${r.status == 'Ativo'}">checked</c:if> onclick="ativarInativarCracha(${r.id})"/>
				  </div>
		      </td>
		    </tr>
	    </c:forEach>
	    </c:if>
	    <c:if test="${!not empty dados.DATA}">
	    	<td scope="col" colspan="5" class="text-center">Não existem crachás cadastradas!</td>
	    </c:if>
  </tbody>
</table>
