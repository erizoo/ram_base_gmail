<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Erizo
  Date: 25.11.2017
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/header.jsp"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="input-group" style="margin-top: 20%">
        <input type="search" id="searchName" name="searchText" class="form-control" placeholder="Поиск по имени">
        <span class="input-group-btn">
    <button class="btn btn-primary" type="submit" onclick="getData($('#searchName').val())"><span class="glyphicon glyphicon-search" aria-hidden="true">
    </span> Search!</button>
    </span>
    </div>
    <script>
        function getData(name) {
            console.log(name);
            alert(1);
            $.ajax({
                type: "POST",
                url: "/search" + "/" + name,

                dataType: "text",
                success: function (data) {
                    var obj = JSON.parse(data);
                    console.log(obj);
                }
            });
        }
    </script>
</div>
</body>
</html>
