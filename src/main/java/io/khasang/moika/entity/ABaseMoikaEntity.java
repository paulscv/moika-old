package io.khasang.moika.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Базовый пустой абстрактный класс, для идентификации Entity проекта
 */
@MappedSuperclass
public abstract class ABaseMoikaEntity implements Serializable{
   // private static final long serialVersionUID = 1L;
}
