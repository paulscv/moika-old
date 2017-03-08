package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, scale =3, unique = true,nullable = false)
    private BigDecimal quantity;
    @Column(length = 15, scale =2, unique = true,nullable = false)
    private BigDecimal sumOfWork;
//    @ManyToOne
//    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "ORDER_ID_FK"))
//   private Order order;
//    @ManyToOne
//    @JoinColumn(name = "work_id", foreignKey = @ForeignKey(name = "WORK_ID_FK"))
//    private Work work;

    public OrdersDetail() {
    }

    public OrdersDetail(BigDecimal quantity, BigDecimal sumOfWork, Order order, Work work) {
        this.quantity = quantity;
        this.sumOfWork = sumOfWork;
//        this.order = order;
//        this.work = work;
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

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public Work getWork() {
//        return work;
//    }
//
//    public void setWork(Work work) {
//        this.work = work;
//    }
}
