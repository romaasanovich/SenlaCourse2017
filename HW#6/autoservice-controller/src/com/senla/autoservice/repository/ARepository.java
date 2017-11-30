package com.senla.autoservice.repository;

import java.io.Serializable;
import java.util.List;

import com.senla.autoservice.api.Entity;

public abstract class ARepository implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5005197405528238654L;
	protected List<Entity> repository;
	
}
