package io.khasang.moika.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name= "polish_service")
@PrimaryKeyJoinColumn(name="id_service")
public class PolishService extends ABaseMoikaServiceAdditionalInfo{

    @Id
    @Column(name = "id_service")
    private int id;

    @OneToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_service", foreignKey = @ForeignKey(name = "fk_id_service"))
    private MoikaAllService moikaServiceBase;

    @Column(name = "add_info")
    private String AddInfop;

    @Column(name = "cost")
    private BigDecimal cost;

    public PolishService(){}

    @Override
    public PolishService getMoikaServiceAdditinalInfo() {
        return this;
    }

    @Override
    public BigDecimal getServiceCost() {
        return cost;
    }

    @Override
    public void setServiceCost(BigDecimal cost) {
            this.cost = cost;
    }
}
