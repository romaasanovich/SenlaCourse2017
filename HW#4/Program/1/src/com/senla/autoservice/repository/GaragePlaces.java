package com.senla.autoservice.repository;
import java.util.Arrays;

import com.senla.autoservice.bean.Place;
import com.senla.autoservice.utills.ArrayChecker;

public class GaragePlaces {
	private Place[] placesList;
	static private int lastID;
	
	public GaragePlaces(int size){
		placesList = new Place[size];
	}
	
	public Place[] getPlaces() {
		
		return placesList;
	}

	static public int getLastID() {
		return lastID;
	}
	
	public Place getPlaceById(int id) {
		for (int i = 0; i < placesList.length; i++) {
			if (placesList[i].getId() == id) {
				return placesList[i];
			}
		}
		return null;
	}
	
	public Place getFreePlace() {
		return placesList[0];
	}
	
	public Boolean add(Place obj) {
		if (!ArrayChecker.isFreeId(obj.getId(), placesList)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(placesList)) {
			placesList = Arrays.copyOf(ArrayChecker.resize(placesList),
					placesList.length*2, Place[].class);
		}
		Integer position = ArrayChecker.getFreePosition(placesList);
		lastID=position;
		placesList[position] = obj;
		return true;
	}
	

}

