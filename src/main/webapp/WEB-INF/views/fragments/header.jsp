<%@ page language="java" contentType="text/html;  charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<script src="${contextPath}/resources/js/jquery-3.1.1.js"></script>
	<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
	<script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.twbsPagination.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap-checkbox.min.js"></script>


</head>
<title>VetClient</title>

<spring:url value="/categories" var="getList"/>


<%--<nav class="navbar navbar-light bg-faded">--%>
	<%--<ul class="nav navbar-nav">--%>
		<%--<li class="nav-item active">--%>
			<%--<a class="nav-link" onclick="location.href='${loginUrl}'" >Меню категорий<span class="sr-only">(current)</span></a>--%>
		<%--</li>--%>
	<%--</ul>--%>
<%--</nav>--%>
<nav class="navbar navbar-light bg-faded">
	<ul class="nav navbar-nav">
		<li class="nav-item active">
			<a class="nav-link" onclick="location.href='${getList}'">Список привязанных товаров<span
					class="sr-only">(current)</span></a>
		</li>
	</ul>
</nav>

</html>