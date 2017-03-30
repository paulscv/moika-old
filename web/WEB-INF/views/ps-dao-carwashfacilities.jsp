<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test for Car wash facility entity</title>
    <%-- HEAD --%>
    <jsp:include page="head.jsp"/>
    <%-- FancyGrid  --%>
    <link href="<c:url value="/css/fancy.min.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/js/fancy.min.js"/>"></script>

    <style type="text/css">
        #map {
            height: 25%;
            width: 100%;
        }
    </style>
    <style type="text/css">
        .action-column-remove {
            cursor: pointer;
            padding: 5px !important;
            position: relative;
            top: -5px;
            border-radius: 2px;
            background-color: #E4827D;
            color: #FFF;
        }
    </style>
    <style type="text/css">
        .fancy-theme-bootstrap .fancy-checkbox-expander {
            margin-top: 10px;
        }

        .fancy-grid-expand-row .fancy-grid {
            border-top: 1px solid #d3dbe1 !important;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row header">
        <div class="col-xs-12">
            <%--Главное меню сайта--%>
            <jsp:include page="mainMenu.jsp"/>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <p>Текущее время ${currentTime}</p>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <div id="facilityContainer"></div>
            <%--   <button type="button" id="clickBtn" value="Click me" onclick="alert(facilityData.length)">Click me</button>
            <div>[
            <c:forEach items="${fcltlist}" var="fclty">
                {
                id: '${fclty.id}',
                name: '${fclty.name}',
                description: '${fclty.description}',
                addr: '${fclty.facilityAddr.city.name}',
                boxData: [
                <c:forEach items="${fclty.washBoxes}" var="box">
                    {
                    id: '${box.id}',
                    name: '${box.boxName}',
                    description: '${box.description}',
                    status: '${box.boxStatusEntity.statusName}',
                    type: '${box.boxTypeEntity.typeName}'
                    },
                </c:forEach>
                ]
                },
            </c:forEach>
            ];
            </div>
            --%>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <div id="boxContainer"></div>

        </div>
    </div>
    <div class="row">
        <div class="span12">
            <div id="map"></div>
        </div>
    </div>
    <div>
        <%--TODO вынести в отдельную стр--%>
        <div class="row footer vertical-align">
            <div class="col-xs-12">О команде, контакты, ©</div>
        </div>
    </div>
</div>
</body>
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
<script type="text/javascript">
    var facilityData = [
            <c:forEach items="${fcltlist}" var="fclty">
            {
                id: '${fclty.id}',
                name: '${fclty.name}',
                description: '${fclty.description}',
                addr: {
                    id: '${fclty.facilityAddr.id_addr}',
                    city: {id: '${fclty.facilityAddr.id_city}', name: '${fclty.facilityAddr.city.name}'},
                    street: '${fclty.facilityAddr.street}',
                    building: '${fclty.facilityAddr.building}',
                    letter: '${fclty.facilityAddr.letter}',
                    longitude: '${fclty.facilityAddr.longitude}',
                    lattitude: '${fclty.facilityAddr.lattitude}'
                },
                boxData: [
                    <c:forEach items="${fclty.washBoxes}" var="box">
                    {
                        id: '${box.id}',
                        name: '${box.boxName}',
                        description: '${box.description}',
                        status: '${box.boxStatusEntity.statusName}',
                        type: '${box.boxTypeEntity.typeName}'
                    },
                    </c:forEach>
                ]
            },
            </c:forEach>
        ],
        cities = [
            <c:forEach items="${citiesList}" var="citiesList">
            {id: '${citiesList.id}', name: '${citiesList.name}', region: '${citiesList.region}'},
            </c:forEach>
        ],
        boxStatusList = [
            <c:forEach items="${boxStatusList}" var="boxStatusList">
            {id: '${boxStatusList.id}', name: '${boxStatusList.statusCode}', region: '${boxStatusList.statusName}'},
            </c:forEach>
        ],
        boxTypeList = [
            <c:forEach items="${boxTypeList}" var="boxTypeList">
            {id: '${boxTypeList.id}', name: '${boxTypeList.typeCode}', region: '${boxTypeList.typeName}'},
            </c:forEach>
        ]
        ;


    window.onload = function () {
        var facilityGrid = new FancyGrid({
            theme: 'bootstrap',
            title: '<img src = "/images/logo1.svg" alt="CarWash" height="40" width="32">  Мойки',
            titleHeight: 65,
            renderTo: 'facilityContainer',
            data: facilityData,
            height: 500,
            paging: true,
            selModel: 'rows',
            trackOver: true,
            columnLines: true,
            defaults: {
                type: 'string',
                width: 100,
                editable: false,
                sortable: true,
                ellipsis: true,
                resizable: true,
                vtype: 'notempty'
            },
            tbar: [{
                type: 'button',
                text: 'Добавить',
                tip: 'Добавить новый моечный комлекс',
                handler: function () {
                    this.addFacility();
                }
            }, {
                type: 'button',
                text: 'Изменить',
                tip: 'Добавить новый моечный комлекс',
                handler: function () {
                    var me = this, selection = me.getSelection(), item = selection[0];
                    this.editFacility(item);
                }
            }, {
                type: 'button',
                text: 'Delete',
                tip: 'Удалить моечный комплес',
                handler: function () {
                    var me = this, selection = me.getSelection(), item = selection[0];
                    this.deleteFacility(item);
                }
            }],
            events: [{
                select: 'onSelect'
            }, {
                clearselect: 'onClearSelect'
            }, {
                rowdblclick: 'onRowDBLClick'
            }],
            controllers: ['facilityGridConrol'],
            expander: {
                render: function (renderTo, data, columnsWidth) {
                    new FancyGrid({
                        renderTo: renderTo,
                        width: columnsWidth - 20,
                        height: 'fit',
                        trackOver: true,
                        selModel: 'rows',
                        title: 'Боксы',
                        theme: 'bootstrap',
                        data: data.boxData,
                        emptyText: 'пусто',
                        defaults: {
                            type: 'string'
                        },
                        tbar: [{
                            type: 'button',
                            text: 'Add',
                            action: 'add'
                        }, {
                            type: 'button',
                            text: 'Update',
                            action: 'update'
                        }, {
                            type: 'button',
                            text: 'Delete',
                            action: 'remove'
                        }],
                        defaults: {
                            type: 'string',
                            width: 100,
                            editable: false,
                            sortable: true,
                            ellipsis: true,
                            resizable: true
                        },
                        columns: [{
                            index: 'id',
                            locked: true,
                            title: 'ID',
                            type: 'number',
                            editable: false
                        }, {
                            index: 'name',
                            title: 'Наименование',
                            width: 300,
                            editable: false
                        }, {
                            index: 'status',
                            title: 'Статус',
                            editable: false
                        }, {
                            index: 'type',
                            title: 'Тип',
                            editable: false
                        }]
                    });
                }
            },
            columns: [{
                type: 'expand',
                locked: true
            }, {
                type: 'expand',
                rightLocked: true
            }, {
                index: 'id',
                locked: true,
                title: 'ID',
                type: 'number',
                width: 50,
                editable: false
            }, {
                index: 'name',
                title: 'Наименование',
                width: 300,
                editable: false
            }, {
                index: 'addr',
                title: 'Адрес',
                width: 300,
                editable: false
            }, {
                index: 'description',
                title: 'Дополнительная информация',
                width: 400,
                editable: false
            }]
        });


        Fancy.defineController('facilityGridConrol', {
            onSelect: function (grid) {
                var selection = grid.getSelection(),
                    addButton = grid.tbar[0],
                    editButton = grid.tbar[1],
                    deleteButton = grid.tbar[2];

                if (selection.length === 1) {
                    editButton.enable();
                } else {
                    editButton.disable();
                }
            },
            onClearSelect: function (grid) {
                deleteButton.disable();
            },

            onRowDBLClick: function (grid, o) {
                editFacility(grid, o.data);
            }
        });

        function editFacility(grid, item) {
            var me = this;

            if (facilityEditForm) {
                facilityEditForm.set(item);
            }
        }

        var facilityEditForm = new FancyForm({
            title: {
                text: item.name + ' ' + item.surname,
                tools: [{
                    text: 'Close',
                    handler: function () {
                        this.hide();
                    }
                }]
            },
            window: true,
            draggable: true,
            width: 300,
            height: 370,
            defaults: {
                type: 'string'
            },
            items: [{
                name: 'name',
                label: 'Наименование',
                value: item.name
            }, {
                type: 'textarea',
                name: 'description',
                label: 'Описание',
                value: item.description
            }, {
                name: 'address',
                label: 'Адрес',
                type: 'set',
                items: [{
                    name: 'city',
                    label: 'Город',
                    type: 'combo',
                    data: cities
                }, {
                    name: 'street',
                    label: 'Улица',
                    value: item.addr.street
                },
                    , {
                        name: 'building',
                        label: 'Строение/Дом',
                        value: item.addr.building
                    }
                ]
            }],
            buttons: ['side', {
                text: 'Закрыть',
                handler: function () {
                    this.hide();
                }
            }, {
                text: 'Сохранить',
                handler: function () {
                    me.getById(this.get('id')).set(this.get());
                    me.update();
                }
            }]
        });
    };
</script>
</html>
