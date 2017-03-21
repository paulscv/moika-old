package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "service_types")
public class ServiceType extends ABaseMoikaTypeReference {

    //@OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @OneToMany(mappedBy="serviceTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_type",  insertable = false, updatable = false)
    private List<MoikaService> moiksServices;

    public ServiceType() {
    }

    public ServiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<MoikaService> getMoiksServices() {
        return moiksServices;
    }
}
