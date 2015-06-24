<%@tag description="Template" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ tag import="fr.imie.dto.UsagerDTO" %>
<%@ tag import="java.util.List" %>
<%@ tag import="java.text.SimpleDateFormat"%>

<%@attribute name="title" required="true"%>

<!DOCTYPE html >
<html>
<head>
<title>${title}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel=stylesheet type="text/css" href="css/theme.css">
</head>
<body>

	<fmt:setLocale value="${locale}" />
	<fmt:setBundle basename="fr.imie.controller.general" var="propertie"/>
 	<c:import url="menu.jsp"/>
	<div class="container">
		<div class="content">
			<jsp:doBody/>
		</div>
	</div>
</body>
</html>