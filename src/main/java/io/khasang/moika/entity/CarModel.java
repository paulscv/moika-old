package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Модели автомобилей
 *
 */


@Entity(name = "CarModel")
public class CarModel extends ABaseMoikaEntity {
    @Id
    @GeneratedValue
    private  Long id;
    @Column(name = "model")
    private String model;

    public CarModel() {
    }

    public CarModel(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
