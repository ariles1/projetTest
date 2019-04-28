<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>edition salle</h1>
		<form:form action="save" method="post" modelAttribute="salle">

			<form:hidden path="version"></form:hidden>
			<div class="form-group">
				<form:label path="id">id:</form:label>
				<form:input path="id" class="form-control" readonly="true"></form:input>
			</div>
			<div class="form-group">
				<form:label path="nom">nom:</form:label>
				<form:input path="nom" class="form-control"></form:input>
				<form:errors path="nom" cssClass="alert alert-danger" element="div"></form:errors>
			</div>
			<div class="form-group">
				<form:label path="etage">etage:</form:label>
				<form:input path="etage" class="form-control" type="number"></form:input>
				<form:errors path="etage" cssClass="alert alert-danger"
					element="div"></form:errors>

			</div>
			<div class="form-group">
				<form:label path="capacite">capacite:</form:label>
				<form:input type="number" path="capacite" class="form-control"></form:input>
				<form:errors path="capacite" cssClass="alert alert-danger"
					element="div"></form:errors>
			</div>
			<div class="form-group">
				<input type="submit" value="enregistrer" class="btn btn-success">
				<a href="list" class="btn btn-warning">annuler</a>
			</div>
		</form:form>
	</div>
</body>
</html>