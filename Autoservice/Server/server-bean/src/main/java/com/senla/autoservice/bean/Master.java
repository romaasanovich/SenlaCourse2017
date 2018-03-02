package com.senla.autoservice.bean;

import com.senla.autoservice.api.bean.AEntity;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "master")
public class Master extends AEntity {
@Column(name = "isWork",columnDefinition = "TINYINT")
@Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isWork;
@Column(name = "nameMaster",length = 45)
    private String name;

    public Master(Integer id, String name, boolean isWork) {
        super(id);
        setName(name);
        this.isWork = isWork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsWork(boolean isWork) {
        this.isWork = isWork;
    }

    public boolean getIsWork() {
        return isWork;
    }

    public String toString() {
        String message = getId() + "," + getName() + "," + fromBooleanToIntSQL(getIsWork());
        return message;
    }

    private final int fromBooleanToIntSQL(Boolean str) {
        if (str == true) {
            return 1;
        } else
            return 0;
    }
}
