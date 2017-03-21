package io.khasang.moika.entity;

import java.io.Serializable;

public class CleanServicePk implements Serializable{

    protected int id;
    protected int idDirtType;

    public CleanServicePk() {
    }

    public CleanServicePk(int idService, int idTypeCar) {
        this.id = idService;
        this.idDirtType = idTypeCar;
    }

    public int getIdService() {
        return id;
    }

    public void setIdService(int idService) {
        this.id = idService;
    }

    public int getIdDirtType() {
        return idDirtType;
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CleanServicePk)) return false;

        CleanServicePk that = (CleanServicePk) o;

        if (getIdService() != that.getIdService()) return false;
        return getIdDirtType() == that.getIdDirtType();
    }

    @Override
    public int hashCode() {
        int result = getIdService();
        result = 31 * result + getIdDirtType();
        return result;
    }
}
