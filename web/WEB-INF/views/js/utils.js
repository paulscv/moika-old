/**
 * Вспомогательный инстурументарий для большей
 * дружелюбности сайта (дополняет framework Bootstrap)
 *
 * @since 2017-03-05
 * @author Kovalev Aleksandr
 */

/**
 * Сделать input-элемент активным для ajax-проверки с сервера
 *
 * @param toURL - адрес куда отсылать проверяемое значение
 * @param selector - Селектор input элемента сожержимое которого надо проверять
 * @param result - callback функция вызывается при приеме запроса в нее передается булевый параметр:
 * true если ответс с сервера положительный
 * false если отрицательный
 */
function setActiveFormInput(toURL, selector, result) {
    setChangeListener(selector, 1000, function () {
        var value = $(selector).val();
        console.log("I am acting for: " + value);
        if (value) {
            var sendObj = {};
            var attrName = $(selector).attr('name');
            sendObj[attrName] = value;
            $.ajax({
                method: "POST",
                url: toURL+"?fieldName="+attrName,
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify(sendObj),
                success: function (data) {
                    if (data.success == true) {
                        if (result) {
                            result(true);
                        }
                        setStatusElement(selector, 'success');
                    } else {
                        if (result) {
                            result(false);
                        }
                        //TODO Возможно сюда стоит вставлять сообение с сервера?!
                        setStatusElement(selector, 'error',
                            data.error?data.error:'Введенные Вами данные уже кем-то используются, введите другое значение');
                    }
                }
            });
        } else {
            setStatusElement(selector, 'none', "Введите данные")
        }
    });
}

/**
 * Отправить элементы формы на сервер в виде json-объекта
 *
 * @param toURL - адрес куда отсылать данные
 * @param selector - Селектор формы
 * @param method - метод откправки данных ( POST|GET|PUT|DELETE... )
 */
function formSubmitAjax(toURL, selector, method) {
    var obj = parseFormToJSON(selector);
    $.ajax({
        method: (method == undefined ? "POST" : method),
        url: toURL,
        data: obj
    });
}

/**
 * Преобразовать элементы формы в json-объекта
 *
 * @param selector - Селектор формы
 */
function parseFormToJSON(selector) {
    var obj = {};
    $(selector).find('[name]').each(function () {
        obj[$(this).attr('name')] = $(this).val();
    });
    return JSON.stringify(obj)
}

/**
 * Обработать ошибки полученные с бэкэнда
 *
 * @param errors - Ошибки
 */
function processErrors(errors) {
    if (errors) {
        $.each(errors, function (name, error) {
            var selector = '[name="' + name + '"]';
            setStatusElement(selector, 'error', error);
        });
    }
}

/**
 * Сбросить статус (оформление) элемента на стандартный
 *
 * @param selector - Селектор input-элемента
 */
function resetStatusElement(selector) {
    var elem = $(selector);
    var div = elem.closest('div.form-group');
    var glyphicon = div.find('span.glyphicon');
    var mapClassNames = {
        'has-error': 'glyphicon-remove',
        'has-success': 'glyphicon-ok',
        'has-warning': 'glyphicon-warning-sign'
    };
    for (var key in mapClassNames) {
        if (div.hasClass(key)) {
            div.removeClass(key);
            glyphicon.removeClass(mapClassNames[key]);
            break;
        }
    }
    if (!glyphicon.hasClass('hide')) {
        glyphicon.addClass('hide');
    }
    if ($(selector).hasClass('popover-dismissible')) {
        $(selector).popover('destroy');
        $(selector).removeClass('popover-dismissible');
     }
}

/**
 * Устанавить статус (оформление) элемента на стандартный
 *
 * @param selector - Селектор input-элемента
 * @param status - статус элемента (его оформление), строковое значение, может принимать
 * одно из перечисленных значений:
 * none | error | warning | success
 * @param msg - текст всплывающий подсказки
 *
 */
function setStatusElement(selector, status, msg) {
    var elem = $(selector);
    if (elem && status && status.search(/^(none|error|warning|success)$/i) > -1) {
        resetStatusElement(selector);
        var div = elem.closest('div.form-group');
        var glyphicon = div.find('span.glyphicon');
        var color = 'black';
        if (glyphicon.hasClass('hide') && status != 'none') {
            glyphicon.removeClass('hide');
        }

        switch (status) {
            case 'error':
                div.addClass('has-error');
                glyphicon.addClass("glyphicon-remove");
                color = '#a94442';
                break;
            case 'warning':
                div.addClass('has-warning');
                glyphicon.addClass("glyphicon-warning-sign");
                color = '#8a6d3b';
                break;
            case 'success':
                div.addClass('has-success');
                glyphicon.addClass("glyphicon-ok");
                color = '#3c763d';
                break;
        }

        if (msg) {
            //Обязательно надо выждать 200ms после вызова метода $(selector).popover('destroy')!!!
            setTimeout(function () {
                $(selector).popover({
                    content: '<span style="color:' + color + '">' + msg + '</span>',
                    html: true,
                    placement: 'auto',
                    trigger: 'hover'
                });
                $(selector).popover('show');
            }, 200);
            console.log("create popover");
            $(selector).addClass('popover-dismissible');
        }
    }
}

/**
 * Устанавить событие на изменение элемента
 *
 * @param selector - Селектор элемента
 * @param ms - задержка в миллисекундах
 * @param action - callback функция которая запускается при срабатывании события
 * */
function setChangeListener(selector, ms, action) {
    $(selector).attr('active','active');
    if ($(selector) && ms && ms > 0) {
        $(selector).on('input', function () {
            if ($(this).attr('timer')) {
                clearTimeout($(this).attr('timer'));
            }
            $(this).attr('timer', setTimeout(function () {
                action($(selector));
            }, ms));
        });
    }
}