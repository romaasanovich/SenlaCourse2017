package com.senla.autoservice.manager;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.senla.autoservice.api.IManager;
import com.senla.autoservice.api.constants.PropConstants;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.repository.GarageRepository;

public class GarageManager implements IManager {
	
	private static final String PLACE_WAS_SUCCESFUL_ADDED = "Place was succesful added";
	private GarageRepository places;

	public GarageManager() {
		places = GarageRepository.getInstance();
	}

	public GarageRepository getPlaces()  {
		return places;
	}


	public ArrayList<Place> getSortedPlaces(Comparator<Place> comp)throws NullPointerException{
		Collections.sort(places.getPlaces(), comp);
		return places.getPlaces();
	}

	public ArrayList<Place> getFreePlaces() throws NullPointerException{
		ArrayList<Place> temp = new ArrayList<Place>(places.getPlaces().size());
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

	public ArrayList<Place> getSortedFreePlaces(Comparator<Place> comp) throws NullPointerException{
		ArrayList<Place> temp = getFreePlaces();
		Collections.sort(temp, comp);
		return temp;
	}

	
	
	public String add(Place place) {
		String message;
		places.add(place);
		message = PLACE_WAS_SUCCESFUL_ADDED;
		return message;
	}
	
	@Override
	public void exportToCSV() throws IOException,NoSuchFieldException, IllegalAccessException {
		places.csvExport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER));
	}

	@Override
    public void importFromCSV() throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		places.csvImport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), Master.class);
    }
}
