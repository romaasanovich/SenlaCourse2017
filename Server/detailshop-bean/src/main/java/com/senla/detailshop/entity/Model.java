package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.*;

@Entity
@Table(name = "`model`")
public class Model extends AEntity {
    @ManyToOne
    @JoinColumn(name = "brandId")
    Brand brand;
    @Column(name = "yearOfRelease")
    Integer yearOfRelease;
    @Column(name = "model",length = 45)
    String model;

    public Model(Integer id, Brand brand, Integer yearOfRelease, String model) {
        super(id);
        this.brand = brand;
        this.yearOfRelease = yearOfRelease;
        this.model = model;
    }

    public Model() {
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
