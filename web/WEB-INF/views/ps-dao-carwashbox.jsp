<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash box entity</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<p>Текущее время скриптлета: <%= new java.util.Date () %> </p>
<table>
    <tr >
        <td>ID  бокса</td>
        <td>Мока</td>
        <td>Имя бокса</td>
        <td>Тип бокса</td>
        <td>Статус бокса</td>
        <td>Доп. информация</td>
    </tr>
    <c:forEach items="${boxlist}" var="boxlist">
        <tr>
            <td><c:out value="${boxlist.id}"/></td>
            <td><c:out value="${boxlist.washFacility.name}"/></td>
            <td><c:out value="${boxlist.boxName}"/></td>
            <td><c:out value="${boxlist.boxTypeEntity.typeName}"/></td>
            <td><c:out value="${boxlist.boxStatusEntity.statusName}"/></td>
            <td><c:out value="${boxlist.description}"/></td>
        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
