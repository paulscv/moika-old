package io.khasang.moika.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Coordinate {

    @Column(name = "latitude")
    private BigDecimal latitude = new BigDecimal("0.0").setScale(5);

    @Column(name = "longitude")
    private BigDecimal longitude = new BigDecimal("0.0").setScale(5);

    public BigDecimal getLat() {
        return latitude;
    }

    public void setLat(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLon() {
        return longitude;
    }

    public void setLon(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;

        Coordinate that = (Coordinate) o;

        if (!latitude.equals(that.latitude)) return false;
        return longitude.equals(that.longitude);
    }

    @Override
    public int hashCode() {
        int result = latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }
}
