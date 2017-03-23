<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash service entitiess</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr>
        <td>ID сервиса</td>
        <td>Наименование мойки</td>
        <td>Наименование сервиса</td>
        <td>Тип сервиса</td>
        <td>Статус сервиса</td>
        <td>Стоимость</td>
        <td>Плановая длительность</td>
        <td>Тип кузова (Код)</td>
        <td>Тип кузова (Наименование)</td>
    </tr>
    <c:forEach items="${servicelist}" var="servicelist">
        <tr>
            <td><c:out value="${servicelist.serviceEntity.id}"/></td>
            <td><c:out value="${servicelist.serviceEntity.washFacility.name}"/></td>
            <td><c:out value="${servicelist.serviceEntity.serviceName}"/></td>
            <td><c:out value="${servicelist.serviceEntity.serviceTypeEntity.typeName}"/></td>
            <td><c:out value="${servicelist.serviceEntity.serviceStatusEntity.statusName}"/></td>
            <td><c:out value="${servicelist.serviceCost}"/></td>
            <td><c:out value="${servicelist.serviceDuration/60}"/></td
            <td><c:out value="${servicelist.carTypeEntity.typeCode}"/></td
            <td><c:out value="${servicelist.carTypeEntity.typeName}"/></td
        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
