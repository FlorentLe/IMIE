<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="edit" tagdir="/WEB-INF/tags"%>



<edit:wrapper title="Edition d'un usager">
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<fmt:setLocale value="${locale}" />
	<fmt:setBundle basename="fr.imie.controller.general" var="propertie"/>

	
		<div class="header rounded largerfont">Edition d'un usager</div>
			<form method="POST">
				<div>
				
					<fmt:message key="usager.lastName" bundle="${propertie}" var="lastName"/>
					<fmt:message key="usager.firstName" bundle="${propertie}" var="firstName"/>
					<fmt:message key="usager.login" bundle="${propertie}" var="login"/>
					<fmt:message key="usager.password" bundle="${propertie}" var="password"/>
					<fmt:message key="usager.email" bundle="${propertie}" var="email"/>
					<fmt:message key="usager.dateOfBirth" bundle="${propertie}" var="dob"/>
					
					<label for="nom" class="label">${lastName} : </label>
					<input type="text" id="nom" name="nom" 
					value="<c:out value="${userToUpdate.nom}" />"
					placeholder="Nom" class="inputText uppercase" required/>				
				</div>
				<div>
					<label for="prenom" class="label">${firstName} : </label>
					<input type="text" id="prenom" name="prenom" 
					value="<c:out value="${userToUpdate.prenom}" />"
					placeholder="Prénom" class="inputText capitalize" required/>
				</div>
				<div>
					<label for="mail" class="label">${email}  : </label>
					<input type="text" id="mail" name="mail" 
					value="<c:out value="${userToUpdate.email}" />"
					placeholder="E-mail" class="inputText" />
				</div>
				<div>
					<fmt:formatDate value="${userToUpdate.dateNaiss}" var="dateNaiss" pattern="dd/MM/yyyy"/>
					<label for="dOB" class="label"> ${dob}  : </label>
					<input type="text" id="dOB" name="dOB" 
	 			 	value="<c:out value="${dateNaiss}"/>" 
					placeholder="Date de naissance" class="inputText" /> 
				</div>
				<div>
					<label for="loginEdit" class="label">${login} : </label>
					<input type="text" id="loginEdit" name="loginEdit" 
					value="<c:out value="${userToUpdate.login}" />"
					placeholder="Login" class="inputText " required/>			
				</div>
				<div>
					<label for="passwordEdit" class="label">${password}  : </label>
					<input type="password" id="passwordEdit" name="passwordEdit" 
					value="<c:out value="${userToUpdate.password}" />"
					placeholder="Mot de passe" class="inputText " required/>				
				</div>
				<c:if test="${!empty userToUpdate}" >
					<input type="hidden" name="row" value="<c:out value="${row}"/>"/>
				</c:if>
				<div >
					<input type="submit" class="submit apply" value="" name="apply"/>
					<input type="submit" class="submit cancel" value="" name="cancel" formnovalidate/>	
				</div>
			</form>
</edit:wrapper>