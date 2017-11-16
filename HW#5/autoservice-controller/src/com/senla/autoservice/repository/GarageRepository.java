package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Place;

public class GarageRepository {
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

	public void add(Place obj) {
		placesRepository.add(obj);
	}
}
