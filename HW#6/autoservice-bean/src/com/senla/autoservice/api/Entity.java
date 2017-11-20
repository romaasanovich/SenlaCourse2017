package com.senla.autoservice.api;

import java.io.Serializable;

public class Entity implements Serializable	{
	private int id;
	
	public Entity(Integer id) {
		this.id = id;
	}


	public void setId(int i) {
		this.id=i;
	}

	public int getId() {
		return id;
	}
}
