package io.khasang.moika.entity;

import java.math.BigDecimal;

/**
 * Интерфейс для получения/обновления данных по конкретному сервису
 */
public interface IBaseMoikaServiceAddInfo {
    BigDecimal getServiceCost();

    void setServiceCost(BigDecimal cost);

    Integer getServiceDuration();

  //  int getDurationMinutes();

    void setServiceDuration(Integer serviceDuration);

  //  void setServiceDuration(int serviceDurationInMinutes);

    String getAdditionalServiceInfo();

    void setAdditionalServiceInfo(String additionalServiceInfo);
}
