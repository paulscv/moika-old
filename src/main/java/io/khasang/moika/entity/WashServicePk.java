package io.khasang.moika.entity;

import java.io.Serializable;
import java.util.Objects;

public class WashServicePk implements Serializable{

    protected int id;
    protected int idCarType;

    public WashServicePk() {
    }

    public WashServicePk(int idService, int idTypeCar) {
        this.id = idService;
        this.idCarType = idTypeCar;
    }

    public int getIdService() {
        return id;
    }

    public void setIdService(int idService) {
        this.id = idService;
    }

    public int getIdCarType() {
        return idCarType;
    }

    public void setIdTypeCar(int idTypeCar) {
        this.idCarType = idTypeCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WashServicePk)) return false;

        WashServicePk that = (WashServicePk) o;

        if (getIdService() != that.getIdService()) return false;
        return getIdCarType() == that.getIdCarType();
    }

    @Override
    public int hashCode() {
        int result = getIdService();
        result = 31 * result + getIdCarType();
        return Objects.hashCode(result);
    }
}
