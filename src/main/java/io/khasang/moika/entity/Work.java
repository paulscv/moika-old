package io.khasang.moika.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Work extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 80, unique = true,nullable = false)
    @NaturalId(mutable = true)
    private String name;
    @Column(length = 15, scale =2, unique = true,nullable = false)
    private BigDecimal price;
/*
время в минутах для проведения работ в боксе
*/
    private int timeInBox;

    public Work() {
    }

    public Work(String name, BigDecimal price, int timeInBox) {
        this.name = name;
        this.price = price;
        this.timeInBox = timeInBox;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTimeInBox() {
        return timeInBox;
    }

    public void setTimeInBox(int timeInBox) {
        this.timeInBox = timeInBox;
    }

    @Override
    public String toString(){
        System.out.format("id = %d name= %s price = %f timeInBox =%d",
                id,name,price,timeInBox);
        return null;
    }

}
