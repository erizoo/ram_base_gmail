<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Compiled and minified CSS -->
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="${contextPath}/resources/js/jquery-3.1.1.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <title>BOT</title>


</head>
<body>
<div class="container">
    <h1 align="center">Cписок заявок</h1>
    <table class="responsive-table">
        <thead>
        <tr>
            <th>Номер</th>
            <th>Имя</th>
            <th>Телефон</th>
            <th>Тип</th>
            <th></th>
        </tr>
        </thead>

        <c:forEach var="orders" items="${orders}" varStatus="myIndex">
            <tr>
                <td>${myIndex.index + 1}</td>
                <td>${orders.name}</td>
                <td>${orders.number}</td>
                <td>${orders.type}</td>
                <td><button class="btn btn-info" onclick = "finishOrder(${orders.id})">Выполнить заявку</button></td>
            </tr>
        </c:forEach>
    </table>

    <script>
        function finishOrder(id) {
            $.ajax({
                type: "GET",
                dataType: 'text',
                contentType: "application/json",
                url: "/delete/" + id,
                success: function (data) {
                    console.log("success delete item");
                    location.reload();
                }
            });
        }
    </script>
</div>
</body>
</html>
