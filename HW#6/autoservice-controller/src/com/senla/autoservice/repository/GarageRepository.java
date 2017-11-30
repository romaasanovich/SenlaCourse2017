package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Place;

public class GarageRepository  extends ARepository{
	/**
	 * 
	 */
	private static final long serialVersionUID = -742402579230052503L;
	private ArrayList<Place> placesRepository;
	private static GarageRepository instance;
	static private int lastID;

	private GarageRepository() {
		placesRepository = new ArrayList<Place>();
	}

	public static GarageRepository getInstance() {
		if (instance == null) {
			instance = new GarageRepository();
		}
		return instance;
	}

	public ArrayList<Place> getPlaces() {
		return placesRepository;

	}

	static public int getLastID() {
		return lastID;
	}

	public Place getPlaceById(int id){
		for (int i = 0; i < placesRepository.size(); i++) {
			if (placesRepository.get(i).getId() == id) {
				return placesRepository.get(i);
			}
		}
		return null;
	}

	public boolean isFreeId(final Integer id) {
        for (final Place garage : placesRepository) {
            if (id == garage.getId()) {
                return false;
            }
        }
        return true;
    }
	
	public void add(Place obj) {
		placesRepository.add(obj);
	}
	
	public void add(String[] fields)
	{
		String line=fields[0]+";"+fields[1];
		Place tmp= new Place(line);
		add(tmp);
	}
	
	 public boolean update(final String[] fields) {
	        try{
	            final Place target = (Place) getPlaceById(Integer.valueOf(fields[0]));
	            target.update(fields);
	            return true;
	        } catch (final NumberFormatException e){
	            return false;
	        }
	    }
	
}
