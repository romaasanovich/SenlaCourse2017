package com.senla.autoservice.manager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.senla.autoservice.api.IGarageManager;
import com.senla.autoservice.api.IManager;
import com.senla.autoservice.api.constants.PropConstants;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.properties.Prop;
import com.senla.autoservice.repository.GarageRepository;
import com.senla.autoservice.utills.IdGenerator;
import com.senla.autoservice.utills.Serializer;

public class GarageManager implements IGarageManager {
	
	private static final String PLACE_WAS_SUCCESFUL_ADDED = "Place was succesful added";
	private static final String DESER_DONE = "Deser. Done \n";
	private static final String FILE_NOT_FOUND = "Error. File not found\n";
	
	
	CsvExportImport<Place> importerExporterPlaces = new CsvExportImport<Place>();
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
		int id = IdGenerator.getFreeID(getPlaces().getPlaces());
		place.setId(id);
		places.add(place);
		message = PLACE_WAS_SUCCESFUL_ADDED;
		return message;
	}
	
	public String deserializePlaces() {
		GarageRepository newPlaces = Serializer.deserialPlaces(Prop.getProp("placePath"));
		if (newPlaces == null) {
			return FILE_NOT_FOUND;
		}
		for (Place place : newPlaces.getPlaces()) {
			add(place);
		}
		return DESER_DONE;
	}
	
	@Override
	public void exportToCSV() throws Exception {
		
		importerExporterPlaces.csvExport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER),places.getPlaces());
	}

	@Override
    public void importFromCSV() throws Exception {
		importerExporterPlaces.csvImport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), Place.class);
    }
}
