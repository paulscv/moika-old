package io.khasang.moika.service;

/**
 * Реализация Service пользователей
 *
 * @since 2017-03-02
 * @author Kovalev Aleksandr
 */
import io.khasang.moika.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Создать учетную запись пользователя
     *@param user - Данные в объекте user
     */
    User createUser(User user);

    /**
     * Удалить учетную запись пользователя
     *@param id пользователя в БД
     */
    void removeUser(long id);

    /**
     * Обновить поля учетной записи пользователя
     *@param user - Данные в объекте user
     */
    User updateUser(User user);

    /**
     * Проверить является ли данный логин пользователя свободным
     *@param login - Логин пользолвателя
     *@return возвращает булевое значение, true - если данный логин свободен, false - если нет
     */
    boolean hasFreeUserLogin(String login);

    /**
     * Проверить является ли данный email пользователя свободным
     *@param email - Логин пользолвателя
     *@return возвращает булевое значение, true - если данный email свободен, false - если нет
     */
    boolean hasFreeUserEmail(String email);

    /**
     * Вернуть закодированную версию исходного пароля.
     * @param rawPassword
     * @return закодированный пароль
     */
    String getEncodedPassword(String rawPassword);
}
