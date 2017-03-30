<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
    <%-- HEAD --%>
    <jsp:include page="head.jsp"/>
</head>
<body>
<table>
    <tr >
        <td>ID</td>
        <td>Model</td>
        <td>Type</td>
        <td>Number</td>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td><c:out value="${car.id}"/></td>
            <td><c:out value="${car.carModel}"/></td>
            <td><c:out value="${car.carType}"/></td>
            <td><c:out value="${car.carNumber}"/></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
