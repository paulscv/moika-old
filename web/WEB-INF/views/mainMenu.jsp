<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default">
    <div class="container-fluid main-menu">
        <%-- Brand and toggle get grouped for better mobile display --%>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <%--${pageContext.request.contextPath}--%>
            <a class="navbar-brand" href="<c:url value="/" />">
                                <span><img class="logo-car" alt="CarWash" height="40" width="32"
                                           src="<c:url value="images/logo1.svg" />">
                                 </span> CarWash</a>
        </div>

        <%-- Collect the nav links, forms, and other content for toggling --%>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Новости<span class="sr-only">(current)</span></a></li>
                <li><a href="#">Услуги</a></li>
                <li><a href="#">Магазин</a></li>
                <li><a href="#">О Нас</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="client-reg">
                <c:choose>
                    <c:when test="${isAuth==false}">
                        <li>
                            <div class="btn-group" role="group" aria-label="reg">
                                <button type="button" class="btn btn-default navbar-btn" data-toggle="modal"
                                        data-target="#loginModal">
                                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span> Войти
                                </button>
                                <button type="button" class="btn btn-default navbar-btn" data-toggle="modal"
                                        data-target="#regModal">
                                    Зарегистрироваться <span class="glyphicon glyphicon-pencil"
                                                             aria-hidden="true"></span></button>
                            </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <%--TODO Вставить ссылку на личный кабинет пользователя--%>
                        <li id="userPersonalAreaBtn" style="cursor:pointer;">
                            <a><span class="glyphicon glyphicon-user" aria-hidden="true"/>${userFirstName}</a>
                        </li>
                        <%--TODO Вставить ссылку на logout--%>
                        <li id="userLogoutBtn" style="cursor:pointer;">
                            <a><span class="glyphicon glyphicon-log-out" aria-hidden="true"/>Выйти</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <%--li><button type="button" class="btn btn-default navbar-btn">Войти
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span></button>
                </li>
                <li><button type="button" class="btn btn-default navbar-btn">Регистрация
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></li--%>
            </ul>
        </div>
        <%-- /.navbar-collapse --%>
    </div>
    <%-- /.container-fluid --%>
</nav>

<script language="JavaScript">
    $("#userPersonalAreaBtn").click(function () {
        alert("Раздел \"Личный кабинет\" скоро будет добавлен.")
    });
    $("#userLogoutBtn").click(function () {
        $.get("<c:url value="/users/logout"/>", function (data) {
            if (data.success) window.location.replace(" ");
        })
    });
</script>
