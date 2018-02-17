package com.senla.autoservice.api;

public abstract class AEntity {
	
	private int id;
	
	public AEntity(Integer id) {
		this.id = id;
	}


	public void setId(Integer i) {
		this.id=i;
	}

	public int getId() {
		return id;
	}
	
	public abstract void update(final AEntity entity);
}
