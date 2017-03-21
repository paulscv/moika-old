package io.khasang.moika.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "wash_facilities")
public class WashFacility  extends ABaseMoikaEntity  {

    @Id
    @Column(name = "id_fclt") //, columnDefinition = "serial"
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_net")
    private int idNet;
    @Column(name = "id_manager")
    private int  idManager;
    @Column(name = "name")
    private String  name ;
    @Column(name = "descr")
    private String  description;

    @Column(name = "id_addr")
    private int  idAddr;
    @OneToOne
    @JoinColumn(name = "id_addr", insertable = false, updatable = false)
    private WashAddr facilityAddr;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_fclt", referencedColumnName = "id_fclt")
    @JsonManagedReference
    private List<WashBox> washBoxes = new ArrayList<>();


    public WashFacility() {
    }

    public int getId() {
        return id;
    }

    public int getIdNet() {
        return idNet;
    }

    public void setIdNet(int idNet) {
        this.idNet = idNet;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdAddr() {
        return idAddr;
    }

    public void setIdAddr(int idAddr) {
        this.idAddr = idAddr;
    }


    public WashAddr getFacilityAddr() {
        return facilityAddr;
    }

    public void setFacilityAddr(WashAddr facilityAddr) {
        this.facilityAddr = facilityAddr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WashBox> getWashBoxes() {
        return washBoxes;
    }

    public void setWashBoxes(List<WashBox> washBowes) {
        this.washBoxes = washBowes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashFacility)) return false;

        WashFacility that = (WashFacility) o;

        if (getId() != that.getId()) return false;
        if (getIdNet() != that.getIdNet()) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getIdNet();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (WashBox box : washBoxes) {
            sb.append(box.toString());
            sb.append("\n");
        }
        return "WashFacility{" +
                "id=" + id +
                ", idNet=" + idNet +
                ", idManager=" + idManager +
                ", name='" + name + '\'' +
                ", facilityAddr=" + facilityAddr.toString() + '\''+
                ", description='" + description + '\'' +
                ", washBoxes=" + sb.toString() +
                '}';
    }
}
