<%@page import="fr.imie.dto.UsagerDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="fr.imie.controller.general" var="propertie"/>
<div id="menuContainer">
		<fmt:message key="menu.liste" bundle="${propertie}" var="liste"/>
		<fmt:message key="menu.disconnect" bundle="${propertie}" var="disconnect"/>
		<div id="usagers" class="menu ">
			<a href="usagers" class="liste"></a><span class="marginLeft">${liste }</span>
		</div>
		<div id="disconnect" class="menu">
			<a href="déconnexion" class="disconnect"></a><span class="marginLeft">${disconnect}</span>
		</div>
		<div class="menu">
			<span class="capitalize">${connectedUser.prenom }</span>
			<span class="uppercase">${connectedUser.nom }</span> 
		</div>
		<c:if test="${!empty erreur || !empty notification}">
			<c:if test="${!empty erreur}">
				<c:set var="type" value="erreur" scope="page"/>
				<c:set var="message" value="${erreur}" scope="page"/>
				<c:remove var="erreur" scope="session" />
			</c:if>
			<c:if test="${!empty notification}">
				<c:set var="type" value="notification" scope="page"/>
				<c:set var="message" value="${notification}" scope="page"/>
				<c:remove var="notification" scope="session" />
			</c:if>
		<div class="menu">
			<span class="${type}"> <c:out value="${message}"></c:out> </span>
		</div>
		</c:if>
		<div class="menu divConnectedUser"><span class="marginRight">${countConnectedUser}</span></div>
</div>
