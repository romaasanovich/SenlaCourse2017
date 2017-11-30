package com.senla.autoservice.bean;

import com.senla.autoservice.api.Entity;

public class Place extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 905979503861186645L;
	private String name;
	private boolean isBusy;

	public Place(Integer id, String name) {
		super(id);
		this.name = name;
		isBusy = false;
	}

	public Place(String line) {
		super(0);
		String [] temp= line.split(";");
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

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public void update(final String [] fields) {
		this.name=fields[1];
	}
	
	@Override
	
	public String toString() {
		String s;
		s=getId()+";"+getName();
		return s;
	}
	
}
