package com.senla.autoservice.bean;

import com.senla.autoservice.annotations.CsvEntity;
import com.senla.autoservice.annotations.CsvProperty;
import com.senla.autoservice.annotations.PropertyType;
import com.senla.autoservice.api.AEntity;

@CsvEntity(filename="places.csv",valueSeparator=";",entityId="id")


public class Place extends AEntity {
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber=1)
	private String name;
	
	private boolean isBusy;

	public Place(Integer id, String name) {
		super(id);
		this.name = name;
		isBusy = false;
	}

	public Place(Integer id, String name, boolean isBusy) {
		super(id);
		this.name = name;
		this.isBusy = isBusy;
	}

	
	public Place(String line) {
		super(0);
		String [] temp= line.split(",");
		setId(Integer.valueOf(temp[0]));
		this.name=temp[1];
		isBusy = false;
	}
	
	public void  setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

	public boolean getIsBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public void update(String line) {
		String [] fields = line.split(",");
		this.name=fields[1];
	}
	
	@Override
	public String toString() {
		String s;
		s=getId()+","+getName()+","+getIsBusy();
		return s;
	}

	@Override
	public void update(AEntity entity) {	
		this.name = ((Place) entity).getName();
	}
	
}
