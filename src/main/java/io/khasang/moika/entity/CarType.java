package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "car_types")
public class CarType extends ABaseMoikaTypeReference {

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car_type", referencedColumnName = "id_type", insertable = false, updatable = false) //name - имя в удаленной таблице, а refname в локальной
    //@JsonManagedReference
    @JsonIgnore
    private List<Car> cars;

    public CarType() {
    }

    public CarType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }
}


