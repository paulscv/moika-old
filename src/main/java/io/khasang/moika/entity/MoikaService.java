package io.khasang.moika.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity(name = "services")
public class MoikaService extends ABaseMoikaService{

    @Transient
    private List<IBaseMoikaServiceAddInfo>  serviceAddInfo;

    public List<IBaseMoikaServiceAddInfo> getServiceAddInfo() {
        return serviceAddInfo;
    }

    public void setServiceAddInfo(List<IBaseMoikaServiceAddInfo> serviceAddInfo) {
        this.serviceAddInfo = serviceAddInfo;
    }

    public void addServiceAddInfo(IBaseMoikaServiceAddInfo serviceAddInfo) {
        this.serviceAddInfo.add(serviceAddInfo);
    }

}
