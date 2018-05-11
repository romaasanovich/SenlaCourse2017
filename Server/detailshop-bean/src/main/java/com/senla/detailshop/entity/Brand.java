package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "`brand`")
public class Brand extends AEntity {
    @Column(name = "nameOfBrand")
    String nameOfBrand;

    public Brand(Integer id, String nameOfBrand) {
        super(id);
        this.nameOfBrand = nameOfBrand;
    }

    public Brand() {
    }

    public String getNameOfBrand() {
        return nameOfBrand;
    }

    public void setNameOfBrand(String nameOfBrand) {
        this.nameOfBrand = nameOfBrand;
    }
}
