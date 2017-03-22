<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Форма для ввода/редактирования заказа</title>
</head>
<body>
<h3>Форма для ввода/редактирования заказа</h3>

<form:form method="POST" action="/orderm/add/" modelAttribute="orderm">
    <table>
        <tr>
            <td><form:label path="id">id</form:label></td>
            <td><form:input path="id" /></td>
        </tr>
        <tr>
            <td><form:label path="number">Номер</form:label></td>
            <td><form:input path="number" /></td>
        </tr>
        <tr>
            <td><form:label path="registrationDate">Дата регистрации (месяц/день/год)</form:label></td>
            <td><form:input path="registrationDate" /></td>
        </tr>
        <tr>
            <td><form:label path="executiontionDate">Дата исполнения (месяц/день/год)</form:label></td>
            <td><form:input path="executiontionDate" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Записать" /></td>
        </tr>

    </table>
</form:form>

</body>

</html>
