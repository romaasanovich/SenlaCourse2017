package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends AEntity {
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "secName")
    private String secName;
    @Column(name = "number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;

    public User(Integer id, String login, String password, String firstName, String secName, String phoneNumber, String email) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secName = secName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}




    