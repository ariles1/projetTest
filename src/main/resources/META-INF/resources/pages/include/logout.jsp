<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${pageContext.request.userPrincipal !=null}">

	<form action="/springBoot/logout" method="post">
		bonjour&nbsp;${pageContext.request.userPrincipal.name}&nbsp;<input
			type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="logout" class="btn btn-link">
	</form>
</c:if>
