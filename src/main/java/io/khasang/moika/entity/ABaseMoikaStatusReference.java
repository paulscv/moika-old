package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * Базовый абстрактный класс для всех таблиц статусов xxx_status
 * обязательные поля: id_status, code, name
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABaseMoikaStatusReference extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_status", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "code", unique = true, nullable = false)
    protected String code;
    @Column(name = "name")
    protected String name;


    public int getId() {
        return id;
    }

    public String getStatusCode() {
        return code;
    }

    public void setStatusCode(String code) {
        this.code = code;
    }

    public String getStatusName() {
        return this.name;
    }

    public void setStatusName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "id=" + id +
                ", typeCode='" + code + '\'' +
                ", typeName='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABaseMoikaStatusReference)) return false;

        ABaseMoikaStatusReference that = (ABaseMoikaStatusReference) o;

        if (getId() != that.getId()) return false;
        return getStatusCode().equals(that.getStatusCode());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStatusCode().hashCode();
        result = 31 * result + (getStatusName() != null ? getStatusName().hashCode() : 0);
        return result;
    }
}
