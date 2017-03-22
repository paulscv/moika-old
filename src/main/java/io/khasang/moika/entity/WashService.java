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
    @JoinColumn(name = "id_type_car", foreignKey = @ForeignKey(name = "fk_car_type"), insertable = false, updatable = false)
    private CarType carTypeEntity;

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

}
