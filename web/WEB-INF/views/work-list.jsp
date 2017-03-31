<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список работ</title>
</head>
<body>
<h1>Список работ</h1>
<table>
    <tr >
        <td>ID</td>
        <td>Наименование</td>
        <td>Цена</td>
        <td>Время в боксе</td>
    </tr>
    <c:forEach items="${listWork}" var="listWork">
        <tr>
            <td><c:out value="${listWork.id}"/></td>
            <td><c:out value="${listWork.name}"/></td>
            <td><c:out value="${listWork.price}"/></td>
            <td><c:out value="${listWork.timeInBox}"/></td>
            <td><c:out value="изменить"/></td>
            <td><c:out value="удалить"/></td>
        </tr>
    </c:forEach>
</table>
<h3>${nrows}</h3>
<table>
    <tr>
        <td><a href="/work/add/">новая работа</a></td>
    </tr>
</table>
</body>
</html>
