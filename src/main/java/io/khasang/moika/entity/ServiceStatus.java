package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.List;

@Entity (name = "service_status")
public class ServiceStatus extends ABaseMoikaStatusReference{

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status",  insertable = false, updatable = false)
    private List<MoikaService> moiksServices;

    public ServiceStatus() {
    }

    public ServiceStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public List<MoikaService> getMoiksServices() {
        return moiksServices;
    }
}
