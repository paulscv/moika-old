package io.khasang.moika.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Cats extends ABaseMoikaEntity{
    @Id
    @Column(name = "id_cat", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "id_color", insertable=false, updatable=false)
    private int idColor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_color", referencedColumnName = "id_color")
    private CatsColor catsColor;
    private Date additionalInfo;
    private String description;

    public Cats() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Date additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public CatsColor getCatsColor() {
        return catsColor;
    }

    public void setCatsColor(CatsColor catsColor) {
        this.catsColor = catsColor;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", catsColor=" + catsColor +
                ", additionalInfo=" + additionalInfo +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cats)) return false;

        Cats cats = (Cats) o;

        if (getId() != cats.getId()) return false;
        if (getAge() != cats.getAge()) return false;
        if (!getName().equals(cats.getName())) return false;
        if (getCatsColor() != null ? !getCatsColor().equals(cats.getCatsColor()) : cats.getCatsColor() != null)
            return false;
        return getDescription() != null ? getDescription().equals(cats.getDescription()) : cats.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + (getCatsColor() != null ? getCatsColor().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}