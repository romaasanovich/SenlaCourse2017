package com.senla.autoservice.bean;

import com.senla.autoservice.api.bean.AEntity;


public class Master extends AEntity {
    private boolean isWork;
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
