<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript">
    </script>
    <script type="text/javascript">
        var myMap;
        var myPlacemark;
        ymaps.ready(function(){
            myMap = new ymaps.Map("my_map", {
                center: [51.66, 39,20],
                zoom: 10
            });

            myPlacemark = new ymaps.Placemark([51.66, 39,20]);
            myMap.geoObjects.add(myPlacemark);

            myPlacemark = new ymaps.Placemark([51.80, 39,20]);
            myMap.geoObjects.add(myPlacemark);
            myPlacemark = new ymaps.Placemark([51.50, 39,20]);
            myMap.geoObjects.add(myPlacemark);
        });
    </script>
</head>
<body>
<h1> Hello yandex. Привет семье.</h1>
Текущее время: <%= new java.util.Date() %>
Имя вашего хоста: <%= request.getRemoteHost() %>
<h3>карта Воронежа</h3>
<div id="my_map" style="width:600px; height:450px"></div>
</body>
</html>

