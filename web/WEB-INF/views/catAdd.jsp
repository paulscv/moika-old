<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" info="Add cat" %>
<html>
<head>
    <title>Test POST input</title>
    <%-- HEAD --%>
    <jsp:include page="head.jsp"/>
</head>
<body>
<h1>Внесите информацию о коте</h1>
<table>
    <tr>
        <td>
            Введите
        </td>
        <td>
            Результат
        </td>
    </tr>
    <tr>
        <td>
            <form:form id="addCatFrm">
                <label for="name">имя кота:</label>
                <input type="text" name="name" id="name" placeholder="имя">
                <br></br>
                <label for="age">возраст кота:</label>
                <input type="text" name="age" size="2" maxlength="2" id="age">
                <br></br>
                <label for="color">цвет кота:</label>

                <select name="idColor" id="color">
                    <c:forEach items="${catsColorList}" var="catColor">
                        <option value="${catColor.key}">${catColor.value.color}</option>
                    </c:forEach>
                </select>
                <br></br>
                <label for="descr">описание кота:</label>
                <input type="text" name="description" id="descr" placeholder="описание">
                <br></br>
                <button type="button" id="saveBtn" value="Сохранить">Сохранить</button>
            </form:form>
        </td>
</table>
<script type="text/javascript">
    $('#saveBtn').click(function () {
        var jsonData = parseFormToJSON("#addCatFrm");
        $.ajax({
            method: "POST",
            contentType: 'application/json;charset=UTF-8',
            url: "/cats/catAdd",
            data: jsonData
        });
        alert('Записано!');
        window.location.href ="/cats/list";
    });
</script>
</body>
</html>
