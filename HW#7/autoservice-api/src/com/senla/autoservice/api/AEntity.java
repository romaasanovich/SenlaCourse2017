package com.senla.autoservice.api;

import java.io.Serializable;

public abstract class AEntity implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8285164002541060238L;
	private int id;
	
	public AEntity(Integer id) {
		this.id = id;
	}


	public void setId(int i) {
		this.id=i;
	}

	public int getId() {
		return id;
	}
	
	public abstract void update(final AEntity entity);
}
