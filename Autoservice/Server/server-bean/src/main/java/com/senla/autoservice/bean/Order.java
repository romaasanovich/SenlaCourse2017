package com.senla.autoservice.bean;

import com.senla.autoservice.api.StatusOrder;
import com.senla.autoservice.api.bean.AEntity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "`order`")
public class Order extends AEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMaster")
    private Master master;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idService")
    private Work service;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPlace")
    private Place place;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45)
    private StatusOrder status;
    @Column(name = "orderDate")
    @Temporal(value = TemporalType.DATE)
    private Date orderDate;
    @Column(name = "plannedStartDate")
    @Temporal(value = TemporalType.DATE)
    private Date plannedStartDate;
    @Column(name = "completionDate")
    @Temporal(value = TemporalType.DATE)
    private Date completionDate;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Order(int id, Master master, Work service, Place place, StatusOrder status, Date orderDate, Date plannedDate, Date dateOfCompl) {
        super(id);
        this.setMaster(master);
        this.service = service;
        this.place = place;
        setStatus(status);
        this.orderDate = orderDate;
        this.plannedStartDate = plannedDate;
        completionDate = dateOfCompl;
    }

    public Order() {
        super(0);
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Work getService() {
        return service;
    }

    public void setService(Work service) {
        this.service = service;
    }

    public Place getPlace() {
        return place;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
        if (status == StatusOrder.Broned || status == StatusOrder.Deleted || status == StatusOrder.Canceled
                || status == StatusOrder.Closed) {
            getPlace().setIsBusy(false);
        } else if (status == StatusOrder.Opened) {
            getPlace().setIsBusy(true);
        }
    }


    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    @Override

    public String toString() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(getId());
        strBuild.append(",");
        strBuild.append(getService().getId());
        strBuild.append(",");
        strBuild.append(getMaster().getId());
        strBuild.append(",");
        strBuild.append(place.getId());
        strBuild.append(",");
        strBuild.append(status.toString());
        strBuild.append(",");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        strBuild.append(dateFormat.format(orderDate));
        strBuild.append(",");
        strBuild.append(dateFormat.format(plannedStartDate));
        strBuild.append(",");
        strBuild.append(dateFormat.format(completionDate));
        return strBuild.toString();
    }

}
