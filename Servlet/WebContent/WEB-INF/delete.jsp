<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="delete" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<delete:wrapper title="Suppression d'un usager">
			<div class="header rounded largerfont">Confirmer la suppression de</div>
			<div>
				<c:out value="${userToDelete.prenom}" />
				&nbsp;
				<c:out value="${userToDelete.nom}" />
			</div>
			<div>
				<form method="post">
					<input type="hidden" name="row" value="<c:out value="${requestScope.row}"/>"/>
					<input type="submit" class="submit apply" value="" name="apply"/>
					<input type="submit" class="submit cancel" value="" name="cancel"/>	
				</form>
			</div>
</delete:wrapper>