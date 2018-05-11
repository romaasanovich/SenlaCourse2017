package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "`seller`")
public class Seller extends AEntity{
    @Column(name = "phoneNumber",length = 45)
    String phoneNumber;
    @Column(name = "firstName",length = 45)
    String firstName;

    public Seller(Integer id, String firstName, String phoneNumber) {
        super(id);
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }

    public Seller() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
