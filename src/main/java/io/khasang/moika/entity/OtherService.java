package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "other_services")
public class OtherService extends ABaseMoikaServiceAdditionalInfo {

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

    public OtherService() {
        //setAdditionalServiceInfo(addInfo);
    }

    public String getAddInfo() {
        return this.addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
        setAdditionalServiceInfo(addInfo);
    }

    @Override
    public String getAdditionalServiceInfo() {
        if (addInfo != null ) {
            AdditionalServiceInfo = addInfo;
        }
        return AdditionalServiceInfo;
    }
}
