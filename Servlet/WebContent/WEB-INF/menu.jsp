<%@page import="fr.imie.model.UsagerDTO"%>
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
		<div class="menu divConnectedUser"><span class="marginRight">${countConnectedUser}</span></div>
</div>
