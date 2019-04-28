<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${personne.getClass().simpleName == 'Formateur' }">
				<c:url value="saveFormateur" var="action"></c:url>
			</c:when>
			<c:otherwise>
				<c:url value="saveStagiaire" var="action"></c:url>
			</c:otherwise>
		</c:choose>
		<form:form modelAttribute="personne" method="get" action="${action}">
			<form:hidden path="version" />
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" readonly="true"
					placeHolder="remplissage automatique" cssClass="form-control" />
			</div>
			<div class="form-group">
				<form:label path="titre">civilite:</form:label>
				<form:select path="titre" cssClass="form-control">
					<form:options items="${titres}" itemLabel="texte" />
				</form:select>
			</div>
			<div class="form-group">
				<form:label path="prenom">prenom:</form:label>
				<form:input path="prenom" cssClass="form-control" />
				<form:errors path="prenom" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" cssClass="form-control" />
				<form:errors path="nom" element="div" cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="dateNaissance">date de naissance:</form:label>
				<form:input type="date" path="dateNaissance" cssClass="form-control" />
				<form:errors path="dateNaissance" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="adresse.rue">rue:</form:label>
				<form:input path="adresse.rue" cssClass="form-control" />
				<form:errors path="adresse.rue" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="adresse.codePostal">code postal:</form:label>
				<form:input path="adresse.codePostal" cssClass="form-control" />
				<form:errors path="adresse.codePostal" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="adresse.ville">ville:</form:label>
				<form:input path="adresse.ville" cssClass="form-control" />
				<form:errors path="adresse.ville" element="div"
					cssClass="alert alert-danger"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="salle">nom:</form:label>
				<form:select path="salle" cssClass="form-control">
					<form:option value="">pas de salle</form:option>
					<form:options items="${salles}" itemValue="id" itemLabel="nom" />
				</form:select>
				<form:errors path="salle" element="div"
					cssClass="alert alert-danger"></form:errors>

			</div>
			<!-- 			<div class="form-group"> -->
			<%-- 				<form:label path="salle">nom:</form:label> --%>
			<%-- 				<form:select path="salle.id" cssClass="form-control"> --%>
			<%-- 					<form:option value="">pas de salle</form:option> --%>
			<%-- 					<form:options items="${salles}" itemValue="id"/> --%>
			<%-- 				</form:select> --%>
			<%-- 				<form:errors path="salle" element="div" --%>
			<%-- 					cssClass="alert alert-danger"></form:errors> --%>

			<!-- 			</div> -->
			<div class="form-group">
				<button type="submit" class="btn btn-success">enregistrer</button>
				<a href="./list" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>