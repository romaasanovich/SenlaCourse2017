package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "`order`")
public class HistoryOrder extends AEntity {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "sellerId")
    private Seller seller;
    @ManyToMany(cascade = CascadeType.ALL)    @JoinTable(name = "orderHistory", joinColumns = { @JoinColumn(name = "userId"), @JoinColumn(name = "sellerId") }, inverseJoinColumns = { @JoinColumn(name = "detailId") })
    private List<Detail> details;
    @Column(name = "dateOfOrder")
    private Date dateOrder;
    public HistoryOrder(Integer id, User user, Seller seller, List<Detail> details, Date dateOrder) {
        super(id);
        this.user = user;
        this.seller = seller;
        this.details = details;
        this.dateOrder = dateOrder;
    }

    public HistoryOrder() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
