package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "provider")
public class Provider extends AEntity {
    @Column(name = "nameOfProvider",length = 45)
    String nameOfProvider;
    @Column(name = "adress",length = 45)
    String adress;
    @Column(name = "phoneNumber",length = 45)
    String phoneNumber;


    public Provider(Integer id, String nameOfProvider, String adress, String phoneNumber) {
        super(id);
        this.nameOfProvider = nameOfProvider;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public Provider() {
    }

    public String getNameOfProvider() {
        return nameOfProvider;
    }

    public void setNameOfProvider(String nameOfProvider) {
        this.nameOfProvider = nameOfProvider;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
