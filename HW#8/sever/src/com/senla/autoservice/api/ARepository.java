package com.senla.autoservice.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class ARepository<T extends AEntity> implements Serializable {

	private static final long serialVersionUID = 5005197405528238654L;
	protected List<T> repository;
	
	 protected ARepository() {
	        repository = new ArrayList<>();
	    }
	


	public T findById(final Integer id) {
		for (final T entity : repository) {
			if (id.equals(entity.getId())) {
				return entity;
			}
		}
		return null;
	}

	public void add(final T entity) {
		if (isFreeId(entity.getId())) {
			repository.add(entity);
		}

	}

	public boolean update(final T entity) {
		findById(entity.getId()).update(entity);
		return true;
	}

	public String getCSV() {
		final StringBuilder sb = new StringBuilder();
		for (final T entity : repository) {
			sb.append(entity.toString());
		}
		return sb.toString();
	}

	public boolean isFreeId(final Integer id) {
		for (final T entity : repository) {
			if (id.equals(entity.getId())) {
				return false;
			}
		}
		return true;
	}

	
}
