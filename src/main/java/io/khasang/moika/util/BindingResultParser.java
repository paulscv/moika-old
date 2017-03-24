package io.khasang.moika.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Данный класс - это вспомогательный инструмент для преобразования List<FieldError>
 *
 * @author Kovalev Aleksandr
 * @since 2017-03-05
 */
public class BindingResultParser {
    /**
     * Возвращает полную карту "поле->список сообщений об ошибках валидации".
     * Список необходим, потому что по каждому данному полю может быть более одной ошибки.
     *
     * @param bindingResult объект в который передаются Ошибки в виде объектов FieldError {@link FieldError}
     * @return Map<String, String> - key = field name, value = error code list
     */
    public static Map<String, List<String>> getFieldErrorListsMap(BindingResult bindingResult) {

        return bindingResult.getFieldErrors().stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
    }

    /**
     * Возвращает "плоскую" карту "поле->сводное сообщение об ошибках валидации".
     * Сообщение называется сводным, потому что по каждому данному полю может быть более одной ошибки.
     * В этом случае сообщения сочленяются последовательно, через точку с запятой.
     * Данные в таком формате легко преобразовываются в JSON-объект для фронтэнда
     *
     * @param bindingResult объект в который передаются Ошибки в виде объектов FieldError {@link FieldError}
     * @return Map<String, String> - key = field name, value = aggregated error code
     */
    public static Map<String, String> getFieldErrorFlatMap(BindingResult bindingResult) {
        return getFieldErrorListsMap(bindingResult).entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().collect(Collectors.joining("; "))));
    }

    /**
     * Возвращает карту с единственным ключом "success", в котором содержится сообщение об успешной валидации данных
     *
     * @param msg сообщение
     * @return Map<String,String> - объект
     */
    public static Map<String, String> getSuccessMap(String msg) {
        Map<String, String> result = new HashMap<>();
        result.put("success", msg);
        return result;
    }


}
