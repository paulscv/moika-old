<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash facility entity</title>
</head>
<body>
<p>Текущее время ${currentTime}</p>
<table>
    <tr >
        <td>ID  мойки</td>
        <td>Наименование мойки</td>
        <td>Доп. информация</td>
    </tr>
    <c:forEach items="${fcltlist}" var="fcltlist">
        <tr>
            <td><c:out value="${fcltlist.id}"/></td>
            <td><c:out value="${fcltlist.name}"/></td>
            <td><c:out value="${fcltlist.description}"/></td>
        </tr>
    </c:forEach>
</table>
<p>${nrows}</p>
</body>
</html>
