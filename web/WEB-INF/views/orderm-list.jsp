<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash box entity</title>
</head>
<body>
<h1>Заказы</h1>
<table>
    <tr >
        <td>ID  заказа</td>
        <td>Номер заказа</td>
        <td>Дата регистрации</td>
        <td>Дата исполнения</td>
        <td>Предоплачен</td>
        <td>Выполнен</td>

    </tr>
    <c:forEach items="${listOrderm}" var="listOrderm">
        <tr>
            <td><c:out value="${listOrderm.id}"/></td>
            <td><c:out value="${listOrderm.number}"/></td>
            <td><c:out value="${listOrderm.registrationDate}"/></td>
            <td><c:out value="${listOrderm.executiontionDate}"/></td>

        </tr>
    </c:forEach>
</table>
<h3>${nrows}</h3>
<table>
    <tr>
        <td><a href="orderm/add/">Новый заказ</a></td>
    </tr>
</table>
</body>
</html>
