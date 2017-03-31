<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash facility entity</title>
    <style type="text/css">
        html, body { height: 100%; margin: 0; padding: 0; }
        #map { height: 100%; }
    </style>

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
<div id="map"></div>
<script type="text/javascript">

    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: 55.7522200, lng: 37.6155600},
            zoom: 10
        });
    }

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDX_0DCgqImKoRTUPQ3QRjdOwLEVKwm3uE&callback=initMap">
</script>
</body>
</html>
