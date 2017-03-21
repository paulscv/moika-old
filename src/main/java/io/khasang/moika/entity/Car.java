package io.khasang.moika.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity()
@Table(name = "cars")
public class Car extends ABaseMoikaEntity {

    @Id
    @Column(name = "id_car")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_car_type")
    private Short idCarType;
    @ManyToOne
    @JoinColumn(name = "id_car_type", foreignKey = @ForeignKey(name = "fk_car_type"), insertable = false, updatable = false)
    private CarType CarTypeEntity;
    private String carNumber;
    @Column(name = "carmodel")
    private String carModel;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Short status;
    @Column(name = "note")
    private String note;
    @Column(name = "date_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReg;
    @Column(name = "date_Last_Wash")
    @Temporal(TemporalType.DATE)
    private Date dateLastWash;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "r_client_car", joinColumns = {
            @JoinColumn(name = "id_car", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_client",
                    nullable = false, updatable = false)})
    private List<Client> clients = new ArrayList<>();
    public Car() {
    }

    public Short getIdCarType() {
        return idCarType;
    }

    public void setIdCarType(Short idCarType) {
        this.idCarType = idCarType;
    }

    public CarType getCarTypeEntity() {
        return CarTypeEntity;
    }

    public void setCarTypeEntity(CarType carTypeEntity) {
        CarTypeEntity = carTypeEntity;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNum) {
        this.carNumber = carNum;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Calendar getDateReg() {
        return dateReg;
    }

    public void setDateReg(Calendar dateReg) {
        this.dateReg = dateReg;
    }

    public Date getDateLastWash() {
        return dateLastWash;
    }

    public void setDateLastWash(Date dateLastWash) {
        this.dateLastWash = dateLastWash;
    }

    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void changeClient(Client oldClient, Client newClient) {
        clients.remove(oldClient);
        clients.add(newClient);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
