package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "ordermdetail")
public class OrdermDetail extends ABaseMoikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, scale = 3, unique = true, nullable = false)
    private BigDecimal quantity;
    @Column(length = 15, scale = 2, unique = true, nullable = false)
    private BigDecimal sumOfWork;

    public Orderm getOrderm() {
        return orderm;
    }

    public void setOrderm(Orderm orderm) {
        this.orderm = orderm;
    }

    @ManyToOne
    @JoinColumn(name = "orderm_id", foreignKey = @ForeignKey(name = "ORDERM_ID_FK"))
    private Orderm orderm;
    @ManyToOne
    @JoinColumn(name = "work_id", foreignKey = @ForeignKey(name = "WORK_ID_FK"))
    private Work work;

    public OrdermDetail() {
    }

    public OrdermDetail(BigDecimal quantity, BigDecimal sumOfWork) {
        this.quantity = quantity;
        this.sumOfWork = sumOfWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSumOfWork() {
        return sumOfWork;
    }

    public void setSumOfWork(BigDecimal sum) {
        this.sumOfWork = sum;
    }

}
