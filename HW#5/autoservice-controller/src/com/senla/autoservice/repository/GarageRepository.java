package com.senla.autoservice.repository;
import java.util.ArrayList;

import com.senla.autoservice.bean.Place;

public class GarageRepository {
	private  ArrayList<Place> placesList;
	private static GarageRepository instance;
	static private int lastID;
	
	private  GarageRepository(){
		placesList = new ArrayList<Place>() ;
	}
	
	public static GarageRepository getInstance() {
		if(instance == null) {
			instance = new GarageRepository();
		} 
		return instance;
	}
	
	public ArrayList<Place> getPlaces() {
		return placesList;
		
	}

	static public int getLastID() {
		return lastID;
	}
	
	public Place getPlaceById(int id) {
		for (int i = 0; i < placesList.size(); i++) {
			if (placesList.get(i).getId() == id) {
				return placesList.get(i);
			}
		}
		return null;
	}
	
	/*public Place getFreePlace() {
		return placesList;
	}*/
	
	public void add(Place obj) {
		placesList.add(obj);
	}
}

