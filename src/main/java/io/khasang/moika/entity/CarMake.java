package io.khasang.moika.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Марка автомобиля
 */
@Entity
public class CarMake extends ABaseMoikaEntity {

    @Id
    @GeneratedValue
    private  Long id;
    private String name;

    public CarMake() {
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> carModels = new ArrayList<>();


    public  List<CarModel> getCarModel() {
        return carModels;
    }
}
