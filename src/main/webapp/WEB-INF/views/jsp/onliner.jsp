<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <table class="table" id="my_tab_goods">
        <tr>
            <th>Название</th>
            <th></th>
            <th></th>
        </tr>
        <tbody id="my_body_goods">
        </tbody>
    </table>
    <script>
        var idNumber;
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                dataType: 'text',
                contentType: "application/json",
                url: "http://ram.by/api/undescription?key=RXxALRCKZKw8j2dUCf6uTsgnSp31FG5VzyDl&category=" + 245,
                success: function (data) {
                    var obj = JSON.parse(data);
                    console.log(obj);
                    for (var i = 0; i < 100; i++) {
                        newrow = document.all.my_body_goods.insertRow();
                        newcell = newrow.insertCell(0);
                        newcell.innerText = obj[i].sku;
                        newcell = newrow.insertCell(1);
                        newcell.innerText = obj[i].name;
                        newcell = newrow.insertCell(2);
                    }
                }
            });
//            console.log("uотово");
//            $.ajax({
//                type: "GET",
//                contentType: "application/json",
//                url: "/goods",
//                dataType: "text",
//                success: function (data) {
//                    var obj = JSON.parse(data);
//                    console.log(obj);
//                    $('#my_body_goods').html('');
//                    for (var i = 0; i < obj.length; i++) {
//                        newrow = document.all.my_body_goods.insertRow();
//                        newcell = newrow.insertCell(0);
//                        newcell.innerText = obj[i].sku;
//                        newcell = newrow.insertCell(1);
//                        newcell.innerText = obj[i].name;
//                        newcell = newrow.insertCell(2);
//                        idNumber = obj[i].id;
//                        newcell.innerHTML = newcell.innerHTML + " <button type='button' class='btn btn-danger' " +
//                            "onclick = deleteInTrash(idNumber)>Удалить в корзину</button><br>";
//                        newcell = newrow.insertCell(3);
//                        newcell.innerHTML = newcell.innerHTML + " <button type='button' class='btn btn-danger' " +
//                            "onclick = moveInTrash(idNumber)>Нет соответствий</button><br>";
//                    }
//
//                }
//            });
        });

        function moveInTrash(idNumber) {
            console.log(idNumber);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/move_goods" + "/" + idNumber,
                dataType: "text",
                success: function (data) {
                    location.reload();
                    console.log("HTTP Status 200");
                }
            });
        }

        function deleteInTrash(id) {
            console.log(id);
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/delete_goods" + "/" + id,
                dataType: "text",
                success: function (data) {
                    location.reload();
                    console.log("HTTP Status 200");
                }
            });
        }
    </script>
    <div class="input-group" style="margin-top: 20%">
        <input type="search" id="searchName" name="searchText" class="form-control" placeholder="Поиск по имени">
        <span class="input-group-btn">
    <button class="btn btn-primary" type="submit" onclick="getData($('#searchName').val())"><span
            class="glyphicon glyphicon-search" aria-hidden="true">
    </span> Search!</button>
    </span>
    </div>
    <br><br>
    <div class="form-group">
        <div class="col-md-3">
            <input type="text" class="form-control " id="setSkuText" placeholder="Введите ску">
        </div>
    </div>
    <br>
    <table class="table" id="mytab">
        <tr>
            <th>Название</th>
            <th></th>
            <th></th>
        </tr>
        <tbody id="mybody">
        </tbody>
    </table>
    <script>
        var urlJson;

        function getData(name) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: "https://catalog.api.onliner.by/search/products?query=" + name,
                dataType: "text",
                success: function (data) {
                    var obj = JSON.parse(data);
                    console.log(obj);
                    if (obj.products.length === 0) {
                        alert("Совпадений не найдено");
                    } else {
                        $('#mybody').html('');
                        for (var i = 0; i < obj.products.length; i++) {
                            newrow = document.all.mybody.insertRow();
                            newcell = newrow.insertCell(0);
                            newcell.innerText = obj.products[i].name_prefix + " " + obj.products[i].full_name;
                            newcellurl = newrow.insertCell(1);
                            urlJson = obj.products[i].html_url;
                            newcellurl.innerHTML = newcellurl.innerHTML + " <button type='button' class='btn btn-danger' " +
                                "onclick = window.open('" + obj.products[i].html_url + "') >Ссылка на ОНЛАЙНЕР</button><br>";
                            newcell = newrow.insertCell(2);
                            newcell.innerHTML = newcell.innerHTML + "<button class='btn btn-primary' type='submit' " +
                                "onclick=setSku('" + obj.products[i].html_url + "') " +
                                "<span class='glyphicon glyphicon-search' aria-hidden='true'> </span> Привязать</button> </span> </div>";
                        }
                    }
                }
            });
        }

        function setSku(url) {
            var sku = document.getElementById('setSkuText').value;
            var encodeUrl = btoa(url);
            console.log(url);
            $.ajax({
                type: "POST",
                url: "/bind/" + sku + "/" + encodeUrl,
                dataType: "text",
                success: function (data) {
                    location.reload();
                }
            });
        }
    </script>
</div>
</body>
</html>
