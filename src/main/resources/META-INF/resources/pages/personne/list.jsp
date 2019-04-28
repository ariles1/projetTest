<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title><spring:message code="personne.list.title"></spring:message></title>
</head>
<body>
	<div class="container">
		<c:import url="../include/langue.jsp"></c:import>
		<c:import url="../include/logout.jsp"></c:import>
		<div>
			<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
				<a href="./addFormateur" class="btn btn-link"><spring:message
						code="bouton.addFormateur"></spring:message></a>
				<a href="./addStagiaire" class="btn btn-link"><spring:message
						code="bouton.addStagiaire"></spring:message></a>
			</sec:authorize>

		</div>

		<table class="table">
			<caption>
				<spring:message code="personne.list.title"></spring:message>
			</caption>
			<tr>
				<th><spring:message code="personne.label.type"></spring:message></th>
				<th><spring:message code="personne.label.id"></spring:message></th>
				<th><spring:message code="personne.label.titre"></spring:message></th>
				<th><spring:message code="personne.label.prenom"></spring:message></th>
				<th><spring:message code="personne.label.nom"></spring:message></th>
				<th><spring:message code="personne.label.rue"></spring:message></th>
				<th><spring:message code="personne.label.codePostal"></spring:message></th>
				<th><spring:message code="personne.label.ville"></spring:message></th>
				<th><spring:message code="personne.label.dateNaissance"></spring:message></th>
				<th><spring:message code="personne.label.experience"></spring:message></th>
				<th><spring:message code="personne.label.formation"></spring:message></th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="p" items="${personnes}">
				<tr>
					<td><c:choose>
							<c:when test="${p.getClass().simpleName == 'Formateur' }">formateur</c:when>
							<c:otherwise>stagiaire</c:otherwise>
						</c:choose></td>
					<td>${p.id}</td>
					<td>${p.titre.texte}</td>
					<td>${p.prenom}</td>
					<td>${p.nom}</td>
					<td>${p.adresse.rue}</td>
					<td>${p.adresse.codePostal}</td>
					<td>${p.adresse.ville}</td>
					<td><fmt:formatDate value="${p.dateNaissance}"
							pattern="dd MMMMM yyyy"></fmt:formatDate></td>
					<td><c:if test="${p.getClass().simpleName == 'Formateur' }">  ${p.experience}</c:if></td>
					<td><c:if test="${p.getClass().simpleName == 'Stagiaire'}">${p.formation}</c:if></td>
					<td><sec:authorize
							access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
							<a href="./edit?id=${p.id}" class="btn btn-info"><spring:message
									code="bouton.edit"></spring:message></a>
						</sec:authorize></td>
					<td><sec:authorize access="hasRole('ROLE_ADMIN')">

							<a href="./delete?id=${p.id}" class="btn btn-danger"><spring:message
									code="bouton.delete"></spring:message></a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>