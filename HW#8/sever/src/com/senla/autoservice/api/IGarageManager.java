package com.senla.autoservice.api;

import java.util.ArrayList;
import java.util.Comparator;

import com.senla.autoservice.bean.Place;
import com.senla.autoservice.repository.GarageRepository;

public interface IGarageManager extends IManager{
	public GarageRepository getPlaces();
	public ArrayList<Place> getSortedPlaces(Comparator<Place> comp);
	public ArrayList<Place> getFreePlaces();
	public ArrayList<Place> getSortedFreePlaces(Comparator<Place> comp);
	public String add(Place place) ;
	public void importFromCSV() throws Exception;
	public void exportFromCSV() throws Exception;
	public String deserializePlaces();
}
