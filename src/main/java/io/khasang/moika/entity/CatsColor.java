package io.khasang.moika.entity;

import javax.persistence.*;

@Entity(name = "cats_colors")
public class CatsColor extends ABaseMoikaEntity{

    @Id
    @Column(name = "id_color", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "color")
    private String color;

    public CatsColor() {
    }

    public CatsColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
