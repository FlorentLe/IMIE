<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="liste" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<liste:wrapper title="Liste des Usagers">
			<div class="header largerfont rounded ">Liste des usagers</div>
			<table>
				<tr>
					<th>#</th>
					<th>Nom</th>
					<th>Prénom</th>
					<th>E-mail</th>
					<th>Date de naissance</th>
					<th>Login</th>
					<th>Actions</th>
				</tr>
				<c:forEach var="usager" items="${usagersDTO}" varStatus="loopStatus">
						<tr>
							<td>#<c:out value="${loopStatus.index}" /></td>
							<td><c:out value="${ usager.nom}"/></td>
							<td><c:out value="${ usager.prenom}"/></td>
							<td><c:out value="${usager.email}"/></td>
							<fmt:formatDate value="${usager.dateNaiss}" var="dateNaiss" pattern="dd/MM/yyyy"/>
							<td><c:out value="${dateNaiss}" /></td>
							<td><c:out value="${ usager.login}"/></td>
						<td>
							<div>
							<form method="post">
								<input type="hidden" name="row" value="${loopStatus.index}" />
								<input type="submit" name="update"  value="" class="edit submit " />
								<input type="submit" name="delete"  value="" class="delete submit "/>
							</form>
							</div>
						</td>
							
						</tr>
				</c:forEach> 	
			</table>
			
			<div>
				<form method="post">
					<input type="submit" id="create" name="create" value="Nouveau" class=" fullWidth largerfont lightgreen rounded submit" />
				</form>
			</div>
</liste:wrapper>
