package com.senla.detailshop.entity;

import com.senla.detailshop.entity.aentity.AEntity;
import com.senla.detailshop.entity.enums.TypeOfFuel;
import com.senla.detailshop.entity.enums.WheelDrive;

import javax.persistence.*;

@Entity
@Table(name = "`modification`")
public class Car extends AEntity {
    @ManyToOne
    @JoinColumn(name = "modelId")
    Model model;
    @Column(name = "fuel",length = 45)
    TypeOfFuel fuel;
    @Column(name = "engine",length = 45)
    String engine;
    @Column(name = "wheelDrive",length = 45)
    WheelDrive wheelDrive;

    public Car(Integer id, Model model, TypeOfFuel fuel, String engine, WheelDrive wheelDrive) {
        super(id);
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.wheelDrive = wheelDrive;
    }

    public Car() {
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public TypeOfFuel getFuel() {
        return fuel;
    }

    public void setFuel(TypeOfFuel fuel) {
        this.fuel = fuel;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public WheelDrive getWheelDrive() {
        return wheelDrive;
    }

    public void setWheelDrive(WheelDrive wheelDrive) {
        this.wheelDrive = wheelDrive;
    }
}
