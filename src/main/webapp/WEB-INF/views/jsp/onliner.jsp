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
    <div id="data-container"></div>
    <div id="pagination-container"></div>

    <table class="table" id="my_tab_goods">
        <tr>
            <th>Название</th>
            <th></th>
            <th></th>
        </tr>
        <tbody id="my_body_goods">
        </tbody>
    </table>
    <ul id="pagination-demo" class="pagination-sm"></ul>
    <br><br>
    <script>
        var paramPage = 1;
        var countAll = ${counts};
        var pages = Math.ceil(countAll / 10);
        $('#pagination-demo').twbsPagination({
            totalPages: pages,
            visiblePages: 5,
            loop: true,
            onPageClick: function (event, page) {
                paramPage = page;
                getGoodsForPagination(page);
            }
        });

        function deleteGoods(id) {
            $.ajax({
                type: "POST",
                url: "/delete_goods/" + id,
                dataType: "text",
                success: function (data) {
                    location.reload();
                }
            });
        }

        function moved(id) {
            console.log(urlJson)
            var encodeUrl = btoa(urlJson);
            $.ajax({
                type: "POST",
                url: "/move_goods/" + id + "/" + encodeUrl,
                dataType: "text",
                success: function (data) {
                    location.reload();
                }
            });
        }

        function getGoodsForPagination(page) {
            $.ajax({
                type: "GET",
                dataType: 'text',
                contentType: "application/json",
                url: "/goods/" + page,
                success: function (data) {
                    var obj = JSON.parse(data);
                    $('#my_body_goods').html('');
                    obj.forEach(function (item) {
                        newrow = document.all.my_body_goods.insertRow();
                        newcell = newrow.insertCell(0);
                        newcell.innerText = item.sku;
                        skuGood = item.sku;
                        newcell = newrow.insertCell(1);
                        newcell.innerText = item.name;
                        nameGood = item.name;
                        newcell = newrow.insertCell(2);
                        newcell.innerHTML = newcell.innerHTML + " <button type='button' class='btn btn-info' " +
                            "onclick = getData(nameGood) >Поиск</button><br>";
                        newcell = newrow.insertCell(3);
                        newcell.innerHTML = newcell.innerHTML + " <button type='button' class='btn btn-danger' " +
                            "onclick = deleteGoods('" + item.id + "') >Удалить</button><br>";
                    });

                }

            });
        }


        var urlJson;

        function getData(name) {
            console.log(name);
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
                        console.log(skuGood)
                        $("#setSkuText").val(skuGood);
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
                                "onclick=moved(document.getElementById('setSkuText').value) " +
                                "<span class='glyphicon glyphicon-search' aria-hidden='true'> </span> Привязать</button> </span> </div>";
                        }
                    }
                }
            });
        }

        function setSku(url) {
            var json;
            var sku = document.getElementById('setSkuText').value;
            var encodeUrl = btoa(url);
            $.ajax({
                type: "GET",
                dataType: 'text',
                contentType: "application/json",
                url: "/all_goods/" + encodeUrl + "/" + sku,
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
            // var sku = document.getElementById('setSkuText').value;
            // var encodeUrl = btoa(url);
            // console.log(url);
            // $.ajax({
            //     type: "POST",
            //     url: "/bind/" + sku + "/" + encodeUrl,
            //     dataType: "text",
            //     success: function (data) {
            //         location.reload();
            //     }
            // });
        }
    </script>
    <div class="input-group">
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

    </script>
</div>
</body>
</html>
