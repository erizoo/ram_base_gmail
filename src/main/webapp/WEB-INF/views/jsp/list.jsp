<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<SetEnv downgrade-1.0></SetEnv>
<html lang="ru">
<head>
    <jsp:include page="../fragments/header.jsp"/>
</head>
<body>
<div class="container">
    <h1 align="center">Cписок товаров</h1>
    <div class="table-responsive">
        <table class="table table-condensed table-striped">
            <thead>
            <tr align="center">
                <th>Категория</th>
                <th>Производитель</th>
                <th>Модель</th>
                <th>Популярность</th>
                <th>SKU МАРКЕТ</th>
                <th>SKU RAM</th>
                <th>Маркет</th>
            </tr>
            </thead>



            <c:forEach var="users" items="${users}">
                <tr>
                    <td>${users.nameCategory}</td>
                    <td>${users.nameManufacturer}</td>
                    <td>${users.nameModel}</td>
                    <td>${users.numberPopular}</td>
                    <td>${users.skuMarket}</td>
                    <c:choose>
                        <c:when test="${users.skuRam}.equals(${users.stringNull})">
                            <td>hfjkfhj</td>
                        </c:when>
                        <c:otherwise>
                            <td>Нет</td>
                        </c:otherwise>
                    </c:choose>
                    <td><button class="btn btn-info" onclick="window.open('${users.linkMarket}')">Посмотреть на Маркете</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>