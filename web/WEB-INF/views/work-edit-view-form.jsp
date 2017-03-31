<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Ввод/редактирование работы услуги</title>
</head>
<body>
<h3>Ввод/редактирование работы услуги</h3>

<form:form method="POST" action="/work/add/" modelAttribute="work">
    <table>
        <tr>
            <td><form:label path="name">наименование</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td><form:label path="price">Цена</form:label></td>
            <td><form:input path="price" /></td>
        </tr>
        <tr>
            <td><form:label path="timeInBox">Время в боксе</form:label></td>
            <td><form:input path="timeInBox" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Записать" /></td>
        </tr>

    </table>
</form:form>

</body>

</html>
