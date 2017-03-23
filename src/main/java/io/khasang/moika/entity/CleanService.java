package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "clean_Services")
@IdClass(CleanServicePk.class)
public class CleanService extends ABaseMoikaServiceAdditionalInfo {
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
    @Column(name = "id_dirt_type")
    private int idDirtType;
    @ManyToOne
    @JoinColumn(name = "id_dirt_type", foreignKey = @ForeignKey(name = "fk_dirt_type"), insertable = false, updatable = false)
    private DirtType dirtTypeEntity;


    public CleanService() {
        //setAdditionalServiceInfo(dirtTypeEntity.getTypeName());
    }

    public int getidDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    public String getDirtTypeCode() {
        return this.dirtTypeEntity.getTypeCode();
    }


    public DirtType getDirtTypeEntity() {
        return dirtTypeEntity;
    }

    public void setDirtTypeEntity(DirtType dirtTypeEntity) {
        this.dirtTypeEntity = dirtTypeEntity;
    }

    @Override
    public String getAdditionalServiceInfo() {
        if (dirtTypeEntity != null ) {
            AdditionalServiceInfo = dirtTypeEntity.getTypeName();
        }
        return AdditionalServiceInfo;
    }
}
