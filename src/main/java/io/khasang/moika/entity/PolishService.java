package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "polish_services")
public class PolishService extends ABaseMoikaServiceAdditionalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    public int getIdService() {
        return id;
    }

    public void setIdService(int id) {
        this.id = id;
    }
    @Column(name = "add_info")
    private String addInfo;

    public PolishService() {
        //setAdditionalServiceInfo(addInfo);
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
