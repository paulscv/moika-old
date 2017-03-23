<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${isAuth==false}">
    <%-- Модальные окна --%>
    <%-- Модальное окно ауторизации--%>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="loginModalLabel">Вход</h3>
                </div>
                <div class="modal-body">
                    <form id="loginForm">
                        <div class="alert alert-danger hide">
                            <p>Invalid login or password </p>
                        </div>
                        <div class="form-group">
                            <label for="loginInputLogin">Логин</label>
                            <input type="text" class="form-control" name="login" id="loginInputLogin"
                                   placeholder="Логин">
                        </div>
                        <div class="form-group">
                            <label for="loginInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="loginInputPassword"
                                   placeholder="Пароль">
                        </div>
                            <%--TODO добавить checkbox "remeber me" для Spring Secucurity--%>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="loginBtn" class="btn btn-primary">Войти</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div>
    <%-- /.modal --%>

    <%-- Модальное окно регистрации--%>
    <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="regModalLabel">Форма регистрации</h3>
                </div>
                <div class="modal-body">
                    <form id="regForm">
                        <div class="form-group has-feedback">
                            <label for="regInputEmail">Email</label>
                            <input type="email" class="form-control" name="email" id="regInputEmail"
                                   placeholder="Email">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPhone">Телефон</label>
                            <input type="tel" class="form-control" name="phone" id="regInputPhone"
                                   placeholder="Phone">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputLogin">Логин</label>
                            <input type="text" class="form-control" name="login" id="regInputLogin"
                                   placeholder="Login">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputFirstName">Имя</label>
                            <input type="text" class="form-control" name="firstName" id="regInputFirstName"
                                   placeholder="FirstName">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="regInputPassword"
                                   placeholder="Пароль">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword1">Повторите пароль</label>
                            <input type="password" class="form-control" id="regInputPassword1" placeholder="Password">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <input type="text" name="enabled" id="regInputEnabled"  value="true" style="display:none">
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="regBtn" class="btn btn-primary">Регистрация</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div><%-- /.modal --%>
    <%--TODO вынести в отдельный js--%>
    <script type="text/javascript">
        <%-- Здесь описана логика работы модальных окон регистрации и аутентификации --%>
        var hasErr = function () {
            var result = false;
            $("#regForm").find('[active]').each(function () {
                var div = $(this).closest('div.form-group');
                if (div.hasClass('has-error')) {
                    result = true;
                    return;
                }
            });
            return result;
        };

        setChangeListener('#regInputPassword1', 1500, function (elem) {
            if (elem.val() && $('#regInputPassword').val() == elem.val()) {
                setStatusElement('#regInputPassword1', 'success');
            } else {
                setStatusElement('#regInputPassword1', 'error', 'Пароли должны совпадать!');
            }
        });

        setChangeListener('#regInputPhone', 1500, function (elem) {
            if (elem.val()) {
                setStatusElement('#regInputPhone', 'none');
            } else {
                setStatusElement('#regInputPhone', 'error', 'Строка не должна быть пустой!');
            }
        });

        setChangeListener('#regInputFirstName', 1500, function (elem) {
            if (elem.val()) {
                setStatusElement('#regInputFirstName', 'none');
            } else {
                setStatusElement('#regInputFirstName', 'error', 'Строка не должна быть пустой!');
            }
        });
        setActiveFormInput('<c:url value="/users/validation"/>', '#regInputEmail');
        setActiveFormInput('<c:url value="/users/validation"/>', '#regInputLogin');
        setActiveFormInput('<c:url value="/users/validation"/>', '#regInputPhone');
        setActiveFormInput('<c:url value="/users/validation"/>', '#regInputPassword');

        $("#loginBtn").click(function () {
            var jsonData = parseFormToJSON('#loginForm');
            $.ajax({
                method: "POST",
                contentType: 'application/json;charset=UTF-8',
                url: "<c:url value="/users/login"/>",
                data: jsonData,
                success: function (data) {
                    if (data.success)
                        window.location.replace(" ");
                    else if (data.error) {
                        var div = $('#loginForm').find('div.alert');
                        $(div).find("p").text(data.error);
                        div.removeClass('hide');
                    }
                }
            });
        });

        $("#regBtn").click(function () {
            if (!hasErr()) {
                var jsonData = parseFormToJSON("#regForm");
                $.ajax({
                    method: "POST",
                    contentType: 'application/json;charset=UTF-8',
                    url: "<c:url value="/users"/>",//TODO добавить актуальные url
                    data: jsonData,
                    success: function (data) {
                        if (data.redirect) {
                            window.location.replace(data.redirect);
                        }
                        if (data.errors) {
                            processErrors(data.errors);
                        }
                    }
                });
            }
        });
    </script>
</c:if>
