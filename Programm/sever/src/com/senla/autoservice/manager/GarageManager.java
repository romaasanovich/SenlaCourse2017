package com.senla.autoservice.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import com.senla.autoservice.api.IGarageManager;
import com.senla.autoservice.api.constants.PropConstants;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.dao.GarageDao;
import com.senla.autoservice.properties.Prop;

public class GarageManager implements IGarageManager {



	CsvExportImport<Place> importerExporterPlaces = new CsvExportImport<Place>();
	private GarageDao places;

	public GarageManager() {

		places = GarageDao.getInstance();
	}

	public String getSortedPlaces() throws SQLException {

		return places.getSortedPlaces();
	}

	public String getFreePlaces() throws SQLException {
		return places.getFreePlaces();
	}

	public String add(String name) throws SQLException {
		return places.addPlace(name);
	}

	@Override
	public void exportToCSV() throws Exception {

		importerExporterPlaces.csvExport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), places.getPlaces());
	}

	@Override
	public void importFromCSV() throws Exception {
		importerExporterPlaces.csvImport(Prop.getProp(PropConstants.PATH_TO_CSV_FOLDER), Place.class);
	}

}
