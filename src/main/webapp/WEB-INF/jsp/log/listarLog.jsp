<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<table class="table table-sm">
  <thead class="" style="background-color: #343A40 !important; color:white !important">
    <tr>
      <th scope="col">Crachá</th>
      <th scope="col">Data</th>
      <th scope="col">Leitora</th>
      <th scope="col">Condição</th>
      <th scope="col">Motivo</th>
    </tr>
  </thead>
  <tbody>
	  <c:if test="${not empty dados.DATA}">
	    <c:forEach items="${dados.DATA}" var="r">
		    <tr>
		      <td scope="col">${r.cracha}</td>
		      <td scope="col">${r.data}</td>
		      <td scope="col">${r.leitora}</td>
		      <td scope="col">${r.liberado}</td>
		      <td scope="col">${r.motivo}</td>
		    </tr>
	    </c:forEach>
	    </c:if>
	    <c:if test="${!not empty dados.DATA}">
	    	<td scope="col" colspan="5" class="text-center">Não encontramos nenhum dados!</td>
	    </c:if>
  </tbody>
</table>