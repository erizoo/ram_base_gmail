<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Erizo
  Date: 07.12.2017
  Time: 13:39
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
    <input type="search" id="getCategoty" name="getCategoty" class="form-control" placeholder="Введите номер категории">
    <span class="input-group-btn">
    <button class="btn btn-primary" type="submit" onclick="getGoods($('#getCategoty').val())"><span
            class="glyphicon glyphicon-search" aria-hidden="true">
    </span> LOAD!</button>
    </span>
</div>
    <div style="margin-top: 10%">
        <button class="btn btn-primary" type="submit" onclick="getCheckGoods()">Выгрузка привязанных товаров</button>
    </div>
    <div style="margin-top: 10%">
        <button class="btn btn-primary" type="submit" onclick="writeJsonInFile()">LOAD</button>
    </div>

    <div style="margin-top: 10%">
        <button class="btn btn-primary" type="submit" onclick="saveGoods()">SAVE</button>
    </div>
    <div style="margin-top: 10%">
        <button class="btn btn-primary" type="submit" onclick="equalsGoods()">Сравнить названия</button>
    </div>
    <div style="margin-top: 10%">
        <button class="btn btn-primary" type="submit" onclick="saveImages()">Сохранить изображения</button>
    </div>
<script>
    function getGoods(number) {
        $.ajax({
            type: "POST",
            dataType: 'text',
            contentType: "application/json",
            url: "http://ram.by/api/undescription?key=RXxALRCKZKw8j2dUCf6uTsgnSp31FG5VzyDl&category=" + number,
            success: function (data) {
                var obj = JSON.parse(data);
                obj.forEach(function(item) {
                    var encodeUrl = (encodeURIComponent(item.name));
                    var encode = btoa(encodeUrl);
                    console.log(item.name);
                    console.log(encodeUrl);
                    console.log(encode);
                    $.ajax({
                        type: "POST",
                        dataType: 'text',
                        contentType: "application/json",
                        url: "/goods/" + item.sku + "/" + encode,
                        success: function (data) {

                        }

                    });
                });

            }
        });
    }

    function getCheckGoods() {
        $.ajax({
            type: "GET",
            dataType: 'text',
            contentType: "application/json",
            url: "/get_check_good",
        });
    }

    function saveGoods() {
        $.ajax({
            type: "GET",
            dataType: 'text',
            contentType: "application/json",
            url: "/save_goods",
        });
    }

    function equalsGoods() {
        $.ajax({
            type: "GET",
            dataType: 'text',
            contentType: "application/json",
            url: "/equals",
        });
    }

    function saveImages() {
        $.ajax({
            type: "GET",
            dataType: 'text',
            contentType: "application/json",
            url: "/save_images",
        });
    }


    function writeJsonInFile() {
        var json;
        $.ajax({
            type: "GET",
            dataType: 'text',
            contentType: "application/json",
            url: "/all_goods",
            success: function (data) {
                console.log(data);
                function download(text, name, type) {
                    var a = document.createElement("a");
                    var file = new Blob([text], {type: type});
                    a.href = URL.createObjectURL(file);
                    a.download = name;
                    a.click();
                }

                download(data, 'test.json', 'text/plain');
            }
        });
    }

//
</script>
</div>

</body>
</html>
