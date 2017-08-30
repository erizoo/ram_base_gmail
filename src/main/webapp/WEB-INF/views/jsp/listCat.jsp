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
    <h1 align="center">Cписок категорий</h1>
    <table class="table" id="mytab">

        <tbody id="mybody">

        </tbody>
    </table>
    <ul id="pagination-demo" class="pagination-sm"></ul>
    <br><br>
    <script>
        var paramPage = 1;
        var countAll = ${counts};
        var pages = Math.ceil(countAll / 10 - 2);
        $('#pagination-demo').twbsPagination({
            totalPages: pages,
            visiblePages: 5,
            loop: true,
            onPageClick: function (event, page) {
                paramPage = page;
                getData(page);
            }
        });
        function getData(page) {
            paramPage = page;
            console.log(page)
            $.ajax({
                type: "POST",
                url: "/categories_json/" + page,

                dataType: "text",
                success: function (data) {
                    var obj = JSON.parse(data);
                    console.log(data);
                    $('#mybody').html('');
                    for (var i = 0; i < 13; i++) {
                        newrow = document.all.mybody.insertRow()
                        newcell = newrow.insertCell(0)
                        console.log(obj[i].id);
                        newcell.innerText = obj[i].name;
                        newcell = newrow.insertCell(1)
                        newcell.innerHTML = newcell.innerHTML + " <button type='button' class='btn btn-info' onclick = location.href='/user/" +  obj[i].id + "'>Открыть</button><br>";
                    }
                }
            });
        }

    </script>

    </div>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>