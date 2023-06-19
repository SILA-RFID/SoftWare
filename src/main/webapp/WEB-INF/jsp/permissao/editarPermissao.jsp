<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal fade" tabindex="-1" role="dialog" id="modalEditarPermissao">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Editar Permissão</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
			<div class="col-sm-12 col-md-6 col-lg-4">
				<label for="per_dia"><b>Dia Semana:</b></label>
				
				<div class="col-12">
				 	<input class="form-check-input" type="checkbox" value="2" id="flexCheckDefault" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 2 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Segunda-Feira
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="3" id="flexCheckDefault1" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 3 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Terça-Feira
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="4" id="flexCheckDefault2" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 4 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Quarta-Feira
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="5" id="flexCheckDefaul3" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 5 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Quinta-Feira
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="6" id="flexCheckDefaul4" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 6 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Sexta-Feira
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="7" id="flexCheckDefaul5" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 7 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Sábado
			 		</label>
		 		</div>
		 		
		 		<div class="col-12">
			 		<input class="form-check-input" type="checkbox" value="1" id="flexCheckDefaul6" <c:forEach items="${dados.DATA}" var="d">
					<c:if test="${d.diasSemana == 1 }">checked</c:if>
				</c:forEach>>
		  			<label class="form-check-label" for="flexCheckDefault">
			    		Domingo
			 		</label>
		 		</div>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="salvarEdicaoPermissao(${dados.PARAMETROS.id})">Salvar</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>