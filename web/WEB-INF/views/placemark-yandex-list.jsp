<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Места расположения</title>
</head>
<body>
<h1>Места расположения моек</h1>

<table>
    <tr >
        <td>ID</td>
        <td>Широта</td>
        <td>Долгота</td>
        <td>Подсказка</td>
        <td>Текст</td>
        ,

    </tr>
    <c:forEach items="${placemarkYandexList}" var="placemarkYandexList">
        <tr>
            <td><c:out value="${placemarkYandexList.id}"/></td>
            <td><c:out value="${placemarkYandexList.latitude}"/></td>
            <td><c:out value="${placemarkYandexList.longitude}"/></td>
            <td><c:out value="${placemarkYandexList.hintContent}"/></td>
            <td><c:out value="${placemarkYandexList.balloonContent}"/></td>

        </tr>
    </c:forEach>
</table>
<h3>${nrows}</h3>
<table>
    <tr>
        <td><a href="/orderm/add">Новый заказ</a></td>
    </tr>
</table>

</body>
</html>
