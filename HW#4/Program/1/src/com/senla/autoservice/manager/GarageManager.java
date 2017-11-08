package com.senla.autoservice.manager;

import java.util.Date;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.repository.GaragePlaces;
import com.senla.autoservice.repository.MasterRepository;

import java.util.Arrays;
import java.util.Comparator;

public class GarageManager {
	
	private static final String PLACE_WAS_SUCCESFUL_ADDED = "Place was succesful added";
	private GaragePlaces places;

	public GarageManager() {
		places = new GaragePlaces(Constants.ARRAY_SIZE);
	}

	public GaragePlaces getPlaces() {
		return places;
	}

	public void setGarage(GaragePlaces garage) {
		this.places = garage;
	}

	public GaragePlaces getSortedPlaces(Comparator<Place> comp) {
		Arrays.sort(places.getPlaces(), comp);
		return places;
	}

	public GaragePlaces getFreePlaces() {
		GaragePlaces temp = new GaragePlaces(places.getPlaces().length);
		for (Place place : places.getPlaces()) {
			if (place == null) {
				break;
			}
			if (!place.isBusy()) {
				temp.add(place);
			}
		}
		return temp;
	}

	public GaragePlaces getSortedFreePlaces(Comparator<Place> comp) {
		GaragePlaces temp = getFreePlaces();
		Arrays.sort(temp.getPlaces(), comp);
		return temp;
	}

	
	
	public String add(Place place) {
		String message;
		if (places.add(place)) {
			message = PLACE_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}

}
