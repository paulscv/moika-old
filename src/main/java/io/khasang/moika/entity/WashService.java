package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "wash_services")
@IdClass(WashServicePk.class)
public class WashService extends ABaseMoikaServiceAdditionalInfo {

    @Id
    @Column(name = "id_service")
    private int id;

    public int getIdService() {
        return id;
    }

    public void setIdService(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "id_type_car")
    private int idCarType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_type_car",  referencedColumnName = "id_type", insertable = false, updatable = false) //foreignKey = @ForeignKey(name = "fk_car_type"),
    private CarType carTypeEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service",  insertable = false, updatable = false) //foreignKey = @ForeignKey(name = "fk_car_type"),
    private MoikaService ServiceEntity;

    public WashService() {
       // setAdditionalServiceInfo(carTypeEntity.getTypeName());
    }

    public int getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(int idCarType) {
        this.idCarType = idCarType;
    }

    public CarType getCarTypeEntity() {
        return carTypeEntity;
    }

    public void setCarTypeEntity(CarType carTypeEntity) {
        this.carTypeEntity = carTypeEntity;
        this.setIdCarType(carTypeEntity.getId());
    }

    public MoikaService getServiceEntity() {
        return ServiceEntity;
    }

    @Override
    public String getAdditionalServiceInfo() {
        if (carTypeEntity != null ) {
            AdditionalServiceInfo = carTypeEntity.getTypeName();
        }
        return AdditionalServiceInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashService)) return false;

        WashService that = (WashService) o;

        if (id != that.id) return false;
        return getIdCarType() == that.getIdCarType();
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + carTypeEntity.hashCode();
        return result;
    }
}
