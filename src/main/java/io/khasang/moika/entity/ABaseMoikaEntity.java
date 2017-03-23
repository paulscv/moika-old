package io.khasang.moika.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Базовый пустой абстрактный класс, для идентификации Entity проекта
 */
@MappedSuperclass
public abstract class ABaseMoikaEntity implements Serializable{
   // private static final long serialVersionUID = 1L;

    /**
     * Удаляет лидирующие и завершающие пробелы в тнепустых строках.
     * Рекомендуется к встраиванию в сеттеры классов сущнеостей для тех полей (логин, телефон, email и т.п., где это необходимо).
     * Решает проблему некорректной работы валидаторов типа @Length и @Size, настренных на ограничение длин значений.
     * Например, если "логин должен быть от 3 до 16 символов", то ведь неправильно будет валидировать логин из 4 пробелов :)
     *
     * @author Rostislav Dublin
     * @since 21.03.2017
     */
    public static String trim(String string) {
        return string == null ? null : string.trim();
    }

}
