package io.khasang.moika.entity;

import javax.persistence.*;

/**
 * Базовый класс-entity для услуг
 */

@MappedSuperclass
public abstract class ABaseMoikaService extends ABaseMoikaEntity {
    @Id
    @Column(name = "id_service", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO) //не IDENTITY, а тот что в таблицах
    protected int id;

    @Column(name = "id_fclt", insertable = false, updatable = false)
    protected int idFacility;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_fclt", foreignKey = @ForeignKey(name = "fk_service_id_fclt"), insertable = false, updatable = false)
    protected WashFacility washFacility;

    @Column(name = "id_type", insertable = false, updatable = false)
    protected int idType;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_type", foreignKey = @ForeignKey(name = "fk_service_type"), insertable = false, updatable = false)
    protected ServiceType serviceTypeEntity;

    @Column(name = "id_status",insertable = false, updatable = false)
    protected Short idStatus;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "fk_service_status"), insertable = false, updatable = false)
    protected ServiceStatus serviceStatusEntity;

    @Column(name = "name", unique = true)
    protected String name;

    @Column(name = "description")
    protected String description;


    protected ABaseMoikaService() {
    }


    public int getId() {
        return this.id;
    }


    public int getServiceType() {
        return idType;
    }


    public void setServiceType(int type) {
        this.idType = type;
    }


    public void setServiceType(String code) {
        ServiceType sse = new ServiceType();
        sse.getTypeCode();
        this.serviceTypeEntity = sse;
    }

    public int getServiceStatus() {
        return idStatus;
    }

    public void setServiceStatus(short status) {
        this.idStatus = status;
    }

    public void setServiceStatus(String code) {
        ServiceStatus st = new ServiceStatus();
        st.setStatusCode(code);
        this.serviceStatusEntity = st;
    }

    public String getServiceName() {
        return name;
    }

    public void setServiceName(String serviceName) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(int idFacility) {
        this.idFacility = idFacility;
    }

    public WashFacility getWashFacility() {
        return washFacility;
    }

    public void setWashFacility(WashFacility washFacility) {
        this.washFacility = washFacility;
    }

    public ServiceType getServiceTypeEntity() {
        return serviceTypeEntity;
    }

    public void setServiceTypeEntity(ServiceType serviceTypeEntity) {
        this.serviceTypeEntity = serviceTypeEntity;
    }

    public ServiceStatus getServiceStatusEntity() {
        return serviceStatusEntity;
    }

    public void setServiceStatusEntity(ServiceStatus serviceStatusEntity) {
        this.serviceStatusEntity = serviceStatusEntity;
    }

    public int getIdType() {
        return idType;
    }

    public String getTypeCode() {
       return getServiceTypeEntity().getTypeCode();
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public Short getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Short idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ABaseMoikaService)) return false;

        ABaseMoikaService that = (ABaseMoikaService) o;

        if (getId() != that.getId()) return false;
        if (getIdFacility() != that.getIdFacility()) return false;
        if (idType != that.idType) return false;
        if (!getWashFacility().equals(that.getWashFacility())) return false;
        if (!getServiceTypeEntity().equals(that.getServiceTypeEntity())) return false;
        if (idStatus != null ? !idStatus.equals(that.idStatus) : that.idStatus != null) return false;
        if (getServiceStatusEntity() != null ? !getServiceStatusEntity().equals(that.getServiceStatusEntity()) : that.getServiceStatusEntity() != null)
            return false;
        return getServiceName() != null ? getServiceName().equals(that.getServiceName()) : that.getServiceName() == null;
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return this.getClass().getName()+" {" +
                "id=" + id +
                ", idFacility=" + idFacility +
                ", washFacility=" + washFacility +
                ", idType=" + idType +
                ", serviceTypeEntity=" + serviceTypeEntity.getTypeCode() +
                ", idStatus=" + idStatus +
                ", serviceStatusEntity=" + serviceStatusEntity.code +
                ", serviceName='" + name + '\'' +
                ((description != null) ? ", description='" + description : "") + '\'' +
                '}';
    }
}
