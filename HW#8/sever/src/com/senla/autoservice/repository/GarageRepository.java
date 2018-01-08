package com.senla.autoservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.ARepository;
import com.senla.autoservice.bean.Place;

public class GarageRepository  extends ARepository{
	/**
	 * 
	 */
	private static final long serialVersionUID = -742402579230052503L;
	private static GarageRepository instance;
	static private int lastID;

	private GarageRepository() {
		repository = new ArrayList<Place>();
	}

	public static GarageRepository getInstance() {
		if (instance == null) {
			instance = new GarageRepository();
		}
		return instance;
	}

	public ArrayList<Place> getPlaces() {
		return (ArrayList<Place>) repository;

	}

	static public int getLastID() {
		return lastID;
	}

	
	public void add(AEntity obj) {
		repository.add((Place) obj);
	}
	
	
	public void add(List<String> fields)
	{
		String line=fields.get(0)+";"+fields.get(1);
		Place tmp= new Place(line);
		add(tmp);
	}

	
}
