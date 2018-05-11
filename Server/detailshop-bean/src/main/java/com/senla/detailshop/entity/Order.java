package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order extends AEntity {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @JoinColumn(name = "sellerId")
    private Seller seller;
    @JoinColumn(name = "providerId")
    private Provider provider;
    @ManyToMany(cascade = CascadeType.ALL)    @JoinTable(name = "orderHistory", joinColumns = { @JoinColumn(name = "userId"),@JoinColumn(name = "providerId"), @JoinColumn(name = "sellerId") }, inverseJoinColumns = { @JoinColumn(name = "detailId") })
    private List<Detail> details;
    @Column(name = "deliveryTime")
    private Integer deliveryTime;
    @Column(name = "count")
    private Integer count;

    public Order(Integer id, User user, Seller seller, Provider provider, List<Detail> details, Integer deliveryTime, Integer count) {
        super(id);
        this.user = user;
        this.seller = seller;
        this.provider = provider;
        this.details = details;
        this.deliveryTime = deliveryTime;
        this.count = count;
    }

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
