package com.senla.autoservice.bean;

import com.senla.autoservice.api.Entity;

public class Work extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2007601645810201382L;
	private String nameOfService;
	private double price;

	public Work(Integer id, String nameOfService, double price) {
		super(id);
		this.nameOfService = nameOfService;
		this.price = price;
	}
	
	public Work (String line) {
		super(0);
		String[] temp = line.split("-");
		setId(Integer.valueOf(temp[1]));
		this.nameOfService = temp[2];
		this.price = Double.valueOf(temp[3]);
	}

	
	public Work() {
		super(0);
	}
	
	public String getNameOfService() {
		return nameOfService;
	}

	public void setNameOfService(String nameOfService){
		this.nameOfService = nameOfService;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(";");
		strBuild.append(getId());
		strBuild.append(";");
		strBuild.append(getNameOfService());
		strBuild.append(";");
		strBuild.append(getPrice());
		return strBuild.toString();
	}

}
