package com.senla.detailshop.entity.aentity;

import javax.persistence.*;

@MappedSuperclass
public abstract class AEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    protected AEntity() {
    }

    public AEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
