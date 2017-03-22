<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Форма для ввода/редактирования геокоординат места</title>
</head>
<body>
<h3>Форма для ввода/редактирования геокоординат места</h3>

<form:form method="POST" action="/yandexmap/add/" modelAttribute="PlacemarkYandex">
    <table>
        <tr>
            <td><form:label path="id">id</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td><form:label path="latitude">Широта</form:label></td>
            <td><form:input path="latitude" /></td>
        </tr>
        <tr>
            <td><form:label path="longitude">Долгота</form:label></td>
            <td><form:input path="longitude" /></td>
        </tr>
        <tr>
            <td><form:label path="hintContent">Подсказка</form:label></td>
            <td><form:input path="hintContent" /></td>
        </tr>
        <tr>
            <td><form:label path="hintContent">Описание</form:label></td>
            <td><form:input path="balloonContent" /></td>
        </tr>

        <tr>
            <td><input type="submit" value="Записать" /></td>
        </tr>

    </table>
</form:form>

</body>

</html>
