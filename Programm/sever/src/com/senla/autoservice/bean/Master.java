package com.senla.autoservice.bean;

import com.senla.autoservice.annotations.CsvEntity;
import com.senla.autoservice.annotations.CsvProperty;
import com.senla.autoservice.annotations.PropertyType;
import com.senla.autoservice.api.AEntity;

@CsvEntity(filename = "masters.csv", valueSeparator = ";", entityId = "id")
public class Master extends AEntity {
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private boolean isWork;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private String name;

	public Master(Integer id, String name, boolean isWork) {
		super(id);
		setName(name);
		this.isWork = false;
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

	public void update(AEntity master) {
		this.name = ((Master) master).getName();
		this.isWork = ((Master) master).getIsWork();
	}

	@Override
	public String toString() {
		String message;
		message = getId() + "," + getName() + "," + getIsWork() + ",";
		return message;
	}
}
