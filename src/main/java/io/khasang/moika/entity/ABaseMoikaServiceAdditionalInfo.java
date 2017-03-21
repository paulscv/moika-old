package io.khasang.moika.entity;

import io.khasang.moika.util.Interval;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * Абстрактный класс расширения для потомков моечных сервисов
 *
 */
@MappedSuperclass
@TypeDef(name="interval", typeClass = Interval.class)
public abstract class ABaseMoikaServiceAdditionalInfo extends ABaseMoikaEntity implements IBaseMoikaServiceAddInfo {


    @Column(name = "cost")
    protected BigDecimal serviceCost = new BigDecimal("0.00");

    @Column(name = "duration")
    @Type(type = "interval")
    protected Integer serviceDuration;

    @Transient
    protected String AdditionalServiceInfo;

    public abstract void setIdService(int Id);

    public abstract int getIdService();

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal cost) {
        this.serviceCost = cost;
    }

    public Integer getServiceDuration() {
        return serviceDuration;
    }

   // public Integer getDurationMinutes(){
   //     return serviceDuration; PGInterval
   // }

    public void setServiceDuration(Integer serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

  //  public void setServiceDuration(int serviceDurationInMinutes) {
  //      this.serviceDuration = serviceDurationInMinutes;
  //  }

    public String getAdditionalServiceInfo() {
        return AdditionalServiceInfo;
    }

    public void setAdditionalServiceInfo(String additionalServiceInfo) {
        AdditionalServiceInfo = additionalServiceInfo;
    }

}
