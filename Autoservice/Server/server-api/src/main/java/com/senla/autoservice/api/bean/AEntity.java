package com.senla.autoservice.api.bean;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class AEntity  implements IAEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public AEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

}
