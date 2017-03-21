package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name="uphones")
public class Uphone extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long id;
    @Column(name="phone_number")
    private String number;
    @ManyToOne
    @JoinColumn(name = "id_person", foreignKey = @ForeignKey(name = "fk_uperson_uphone"))
    private Uperson person;


    public Uphone() {
    }
    public Uphone(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public Uperson getPerson() {
        return person;
    }
    public void setPerson(Uperson person) {
        this.person = person;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Uphone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", person=" + person.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uphone)) return false;

        Uphone uphone = (Uphone) o;

        if (getId() != null ? !getId().equals(uphone.getId()) : uphone.getId() != null) return false;
        if (getNumber() != null ? !getNumber().equals(uphone.getNumber()) : uphone.getNumber() != null) return false;
        return getPerson() != null ? getPerson().equals(uphone.getPerson()) : uphone.getPerson() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        return result;
    }
}
