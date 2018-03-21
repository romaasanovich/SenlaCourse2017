package com.senla.autoservice.bean;

import com.senla.autoservice.bean.aentity.AEntity;
import com.senla.autoservice.bean.hashgenerator.HashGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "user")
public class User extends AEntity {

    private static final int EXPIRES_IN_DAYS = 15;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;

    @Column(name = "expires_in")
    private Long expires;

    public User() {
    }

    public User(final String username, final String password) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRES_IN_DAYS);

        this.username = username;
        this.password = password;
        token = HashGenerator.getMD5Hash(username + password);
        expires = calendar.getTime().getTime();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpires() {
        return expires;
    }
}
