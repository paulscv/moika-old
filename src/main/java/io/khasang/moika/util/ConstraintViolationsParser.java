package io.khasang.moika.util;

import javax.validation.ConstraintViolation;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Данный класс - это вспомогательный инструмент для преобразования Set<ConstraintViolation<User>>
 *
 * @author Rostislav Dublin
 * @since 2017-03-22
 */
public class ConstraintViolationsParser {

    /**
     * Возвращает полную карту "поле->список сообщений об ошибках валидации".
     * Список необходим, потому что по каждому данному полю может быть более одной ошибки.
     *
     * @param constraintViolations список ошибок, возвращённый валидатором {@link ConstraintViolation}
     * @return Map<String, String> - key = field name, value = error code list
     */
    public static Map<String, List<String>> getFieldErrorListsMap(Collection<ConstraintViolation<?>> constraintViolations) {

        return constraintViolations.stream()
                .collect(Collectors.groupingBy(cv -> cv.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
    }

    /**
     * Возвращает "плоскую" карту "поле->сводное сообщение об ошибках валидации".
     * Сообщение называется сводным, потому что по каждому данному полю может быть более одной ошибки.
     * В этом случае сообщения сочленяются последовательно, через точку с запятой.
     * Данные в таком формате легко преобразовываются в JSON-объект для фронтэнда
     *
     * @param constraintViolations список ошибок, возвращённый валидатором {@link ConstraintViolation}
     * @return Map<String, String> - key = field name, value = aggregated error code
     */
    public static Map<String, String> getFieldErrorFlatMap(Collection<ConstraintViolation<?>> constraintViolations) {
        return getFieldErrorListsMap(constraintViolations).entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(), e -> e.getValue().stream().collect(Collectors.joining("; "))));
    }

}
