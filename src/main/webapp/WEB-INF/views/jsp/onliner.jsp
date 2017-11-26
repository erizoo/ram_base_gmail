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
    <div class="input-group" style="margin-top: 20%">
        <input type="search" id="searchName" name="searchText" class="form-control" placeholder="Поиск по имени">
        <span class="input-group-btn">
    <button class="btn btn-primary" type="submit" onclick="getData($('#searchName').val())"><span
            class="glyphicon glyphicon-search" aria-hidden="true">
    </span> Search!</button>
    </span>
    </div>
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
        var url;
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
                            url = obj.products[i].html_url;
                            newcellurl.innerHTML = newcellurl.innerHTML + " <button type='button' class='btn btn-danger' " +
                                "onclick = window.open('" + obj.products[i].html_url + "') >Ссылка на ОНЛАЙНЕР</button><br>";

                            newcell = newrow.insertCell(2);
                            newcell.innerHTML = newcell.innerHTML + "<div class='input-group'> <input type='search' id='setSkuText' name='setSku' class='form-control' " +
                                "placeholder='Введите ску'> <span class='input-group-btn'> <button class='btn btn-primary' type='submit' " +
                                "onclick='setSku()'><span class='glyphicon glyphicon-search' aria-hidden='true'> </span> Привязать</button> </span> </div>";
                        }
                    }
                }
            });
        }
        function setSku() {
            var sku = document.getElementById('setSkuText').value;
            var encodeUrl = btoa(url);
            console.log(encodeUrl);
            $.ajax({
                type: "POST",
                url: "/bind/" + sku + "/" + encodeUrl,
                dataType: "text",
                success: function (data) {
                    location.reload();
                    console.log("HTTP Status 200");
                }
            });
        }
    </script>
</div>
</body>
</html>
