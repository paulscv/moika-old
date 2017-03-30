<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%-- HEAD --%>
    <jsp:include page="head.jsp"/>
    <%-- FancyGrid  --%>
    <link href="<c:url value="/css/fancy.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/js/fancy.min.js"/>"></script>
    <title>Cats</title>
</head>
<body>
<h3>Cats list</h3>
<div id="catsContainer"></div>
</body>
<script type="text/javascript">

    var catsData =  [
        <c:forEach items="${cats}" var="cat">
        {
            id: '${cat.id}',
            name: '${cat.name}',
            color: '${cat.catsColor.color}',
            age: '${cat.age}',
            descr: '${cat.description}'
        },
        </c:forEach>
    ];

    window.onload = function(){
        new FancyGrid({
            renderTo: 'catsContainer',
            width: "500", height: "300",
            title: {
                text:"Cats on the yard",
                style: {
                    'text-align': 'center'
                }
            },
            data: catsData,
            columns: [{
                index: 'id',
                title: 'ID',
                type: 'number',
                sortable: true,
                locked: true,
                width: 30
            }, {
                index: 'name',
                title: 'Name',
                type: 'string',
                sortable: true,
                width: 100
            }, {
                index: 'color',
                title: 'Color',
                type: 'string',
                width: 100
            }, {
                type: 'number',
                index: 'age',
                title: 'Age',
                sortable: true,
                width: 50
            }, {
                index: 'descr',
                title: 'Description',
                type: 'string',
                width: 230
            }],
            bbar: [{
                type: 'button',
                text: 'Add',
                action: 'add'
            },{
                type: 'button',
                text: 'Delete',
                action: 'remove'
            }],
        });
    };
</script>
</html>