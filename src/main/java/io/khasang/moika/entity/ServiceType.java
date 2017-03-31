package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "service_types")
public class ServiceType extends ABaseMoikaTypeReference {

    //@OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @OneToMany(mappedBy="serviceTypeEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_type",  insertable = false, updatable = false)
    @JsonIgnore
    private List<MoikaService> moikaServices;

    public ServiceType() {
    }

    public ServiceType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<MoikaService> getMoikaServices() {
        return moikaServices;
    }
}
