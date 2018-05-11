package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;

import javax.persistence.*;

@Entity
@Table(name = "`detail`")
public class Detail extends AEntity {
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column(name = "article_code")
    private Integer articleCode;
    @Column(name = "description", length = 45)
    private String description;
    @Column(name = "delivery_period")
    private Integer deliveryPeriod;
    @Column(name = "weight")
    private double weight;
    @Column(name = "price")
    private double price;
    @Column(name = "avaliability")
    private int count;

    public Detail(Integer id, Car car, Integer articleCode, String description, Integer delieryPeriod, double weight, double price, int count) {
        super(id);
        this.car = car;
        this.articleCode = articleCode;
        this.description = description;
        this.deliveryPeriod = delieryPeriod;
        this.weight = weight;
        this.price = price;
        this.count = count;
    }

    public Detail() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(Integer articleCode) {
        this.articleCode = articleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDelieryPeriod() {
        return deliveryPeriod;
    }

    public void setDelieryPeriod(Integer delieryPeriod) {
        this.deliveryPeriod = delieryPeriod;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
