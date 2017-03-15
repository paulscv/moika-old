package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orderm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, unique = true,nullable = false)
    private String number;
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Temporal(TemporalType.DATE)
    private Date executiontionDate;
    private boolean is_prepaid;
    private boolean is_made;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdermDetail> ordersDetails = new ArrayList<>();
    public Orderm() {
    }

    public Orderm(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExecutiontionDate() {
        return executiontionDate;
    }

    public void setExecutiontionDate(Date executiontionDate) {
        this.executiontionDate = executiontionDate;
    }

    public boolean is_prepaid() {
        return is_prepaid;
    }

    public void setIs_prepaid(boolean is_prepaid) {
        this.is_prepaid = is_prepaid;
    }

    public boolean is_made() {
        return is_made;
    }

    public void setIs_made(boolean is_made) {
        this.is_made = is_made;
    }

}
