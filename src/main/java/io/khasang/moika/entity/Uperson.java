package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "uperson")
public class Uperson extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    protected Long id;
    @Column(unique = true, nullable = false)
    protected String name;
    @Temporal(TemporalType.DATE)
    protected Date birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Uphone> phones = new ArrayList<>();


    public Uperson() {
    }

    public Uperson(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Uperson(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Uphone> getPhones() {
        return phones;
    }

    public void setPhones(List<Uphone> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uperson)) return false;

        Uperson uperson = (Uperson) o;

        if (!getId().equals(uperson.getId())) return false;
        if (getName() != null ? !getName().equals(uperson.getName()) : uperson.getName() != null) return false;
        if (getBirthDate() != null ? !getBirthDate().equals(uperson.getBirthDate()) : uperson.getBirthDate() != null)
            return false;
        return getPhones() != null ? getPhones().equals(uperson.getPhones()) : uperson.getPhones() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + (getPhones() != null ? getPhones().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Uphone phone : phones) {
            sb.append(phone.getNumber());
            sb.append("\n");
        }
        return "Uperson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", phones=" + sb.toString() +
                '}';
    }
}

