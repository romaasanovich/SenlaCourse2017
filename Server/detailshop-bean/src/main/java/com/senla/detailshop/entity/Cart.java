package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`cart`")
public class Cart extends AEntity {
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)    @JoinTable(name = "cart", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "detailId") })
    private List<Detail> details;


    public Cart(Integer id, User user, List<Detail> details) {
        super(id);
        this.user = user;
        this.details = details;
    }

    public Cart() {

    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
