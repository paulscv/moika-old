package io.khasang.moika.entity;

import java.io.Serializable;

public class ChemCleanServicePk implements Serializable{

    protected int id;
    protected int idDirtType;
    protected int idMaterial;


    public ChemCleanServicePk() {
    }

    public ChemCleanServicePk(int id, int idTypeCar, int idMaterial) {
        this.id = id;
        this.idDirtType = idTypeCar;
        this.idMaterial = idMaterial;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChemCleanServicePk)) return false;

        ChemCleanServicePk that = (ChemCleanServicePk) o;

        if (getIdService() != that.getIdService()) return false;
        if (getIdMaterial() != that.getIdMaterial()) return false;
        return getIdDirtType() == that.getIdDirtType();
    }

    public void setIdDirtType(int idDirtType) {
        this.idDirtType = idDirtType;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public int hashCode() {
        int result = getIdService();
        result = 31 * result + getIdDirtType();
        return result;
    }
}
