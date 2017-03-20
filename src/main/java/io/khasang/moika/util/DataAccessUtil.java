package io.khasang.moika.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;

/**
 * Утилиты для удобного доступа к данным через Hibernate
 *
 * @author Rostislav Dublin
 * @since 2017-03-05
 */
@Service("dataAccessUtil")
public class DataAccessUtil {

    @Autowired
    private SessionFactory sessionFactory;

    public DataAccessUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DataAccessUtil() {
    }

    /**
     * Возвращает готовый к выполнению запрос всех сущностей указанного типа
     *
     * @param entityClass класс запрашиваемых сущностей
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntity(Class<E> entityClass) {

        return getQueryOfEntityWithComplexEqualCondition(entityClass, null);
    }


    /**
     * Возвращает готовый к выполнению запрос сущностей указанного типа по единственному условию "поле=значение"
     *
     * @param entityClass класс запрашиваемых сущностей
     * @param fieldName   имя поля для запроса
     * @param value       значение поля для запроса
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntityWithSoleEqualCondition(Class<E> entityClass, String fieldName, Object value) {

        return getQueryOfEntityWithComplexEqualCondition(entityClass, Collections.singletonMap(fieldName, value));
    }

    /**
     * Возвращает готовый к выполнению запрос сущностей указанного типа по сборному условию "поле=значение И поле=значение..."
     *
     * @param entityClass   класс запрашиваемых сущностей
     * @param fieldValueMap карта имя_поля-значение
     * @return готовый к выполнению запрос
     */
    public <E> TypedQuery<E> getQueryOfEntityWithComplexEqualCondition(Class<E> entityClass, Map<String, Object> fieldValueMap) {

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        TypedQuery<E> query;
        if (fieldValueMap != null && !fieldValueMap.isEmpty()) {
            Predicate where = null;
            List<Object> values = new ArrayList<>();

            for (Map.Entry<String, Object> attr : fieldValueMap.entrySet()) {
                Object value = attr.getValue();

                ParameterExpression params;
                if (value == null) {
                    params = builder.parameter(String.class);
                } else {
                    params = builder.parameter(value.getClass());
                }
                if (where == null) {
                    where = builder.equal(root.get(attr.getKey()), params);
                } else {
                    where = builder.and(where, builder.equal(root.get(attr.getKey()), params));
                }
                values.add(attr.getValue());
            }
            criteriaQuery.where(where);
            query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
            for (int i = 0; i < values.size(); i++) {
                query.setParameter("param" + i, values.get(i));
            }

        } else {
            query = sessionFactory.getCurrentSession().createQuery(criteriaQuery);
        }

        return query;
    }

    /**
     * Возвращает экземпляр сущности указанного типа по её Id
     *
     * @param clazz класс сущности
     * @param id    id
     * @return экземпляр сущности
     */
    public <T> T getEntityById(Class<T> clazz, long id) {
        return sessionFactory.getCurrentSession().get(clazz, id);
    }


    /**
     * Обновляет экземпляр объекта новыми значениями указанных полей. Валидирует объект при обновлении.
     *
     * @param bean          - обновляемый объект
     * @param fieldValueMap - карта поле->новое значение
     * @return обновлённый объект
     */
    public <T> T setNewValuesToBean(@NotNull T bean, @NotNull Map<String, ?> fieldValueMap) {
        BeanWrapperImpl bw = new BeanWrapperImpl();
        bw.setWrappedInstance(bean);

        bw.setPropertyValues(fieldValueMap);
        return bean;
    }

    /**
     * Извлекает значения свойств из экземпляра объекта.
     *
     * @param bean - объект
     * @return карта имя поля->значение
     */
    public Map<String, ?> getValuesFromBean(@NotNull Object bean) {
        BeanWrapperImpl bw = new BeanWrapperImpl();
        bw.setWrappedInstance(bean);

        Map<String, Object> map = new HashMap<>();

        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
            map.put(pd.getName(), bw.getPropertyValue(pd.getName()));
        }

        return map;
    }

    /**
     * Предаставляет объект в виде JSON-строки
     *
     * @param object объект
     * @return JSON-строка
     */
    public String convertObjectToJSON(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Предаставляет JSON-строку в виде объекта указанного класса
     *
     * @param clazz запрашиваемый класс результирующего объекта
     * @param json  JSON-строка
     * @return объект указанного класса
     */
    public <T> T convertJSONToObject(Class<T> clazz, String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Конвиньенс-метод для быстрого получения HTTP-запроса c JSON-представлением передаваемого объекта.
     *
     * @param object объект для передачи в запросе в виде JSON
     * @return HTTP-запрос
     */
    public <T> HttpEntity<T> getHttpEntityForJSON(T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity(object, headers);

    }

}