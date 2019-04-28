<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1>liste des salles</h1>
		<a href="add" class="btn btn-link">ajouter une salle</a>
		<table class="table">
			<tr>
				<th>id</th>
				<th>nom</th>
				<th>etage</th>
				<th>capacite</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="s" items="${salles}">
				<tr>
					<td>${s.id}</td>
					<td>${s.nom}</td>
					<td>${s.etage}</td>
					<td>${s.capacite}</td>
					<td><a href="edit?id=${s.id}" class="btn btn-info">editer</a></td>
					<td><a href="delete?id=${s.id}" class="btn btn-danger">supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>