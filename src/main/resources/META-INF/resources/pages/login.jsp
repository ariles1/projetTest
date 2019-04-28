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
		<div style="align-content: center;">
			<h1>connectez vous</h1>
			<c:if test="${param.error}">
				<div class="alert alert-danger">erreur d'authentification</div>
			</c:if>
			<form method="post" action="">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="form-group">
					<label for="username">username:</label> <input name="username"
						id="username" class="form-control">
				</div>
				<div class="form-group">
					<label for="password">password:</label> <input type="password"
						name="password" id="password" class="form-control">
				</div>
				<div class="form-group">
					<input type="submit" value="envoyer" class="btn btn-info">
				</div>
			</form>
		</div>
	</div>
</body>
</html>