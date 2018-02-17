package com.senla.autoservice.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.senla.autoservice.DBConnector.DBConnector;
import com.senla.autoservice.api.IGarageManager;
import com.senla.autoservice.bean.Place;
import com.senla.autoservice.csvimporexport.CsvExportImport;
import com.senla.autoservice.dao.GarageDao;

public class GarageManager implements IGarageManager {



	CsvExportImport<Place> importerExporterPlaces = new CsvExportImport<Place>();
	private GarageDao places;
	private DBConnector con;

	public GarageManager() {
		con = DBConnector.getInstance();
		places = GarageDao.getInstance();
	}

	public ArrayList<Place> getSortedPlaces(String comp)throws SQLException {
		return places.getSortedPlaces(con.getConnnection(),comp);
	}

	public ArrayList<Place> getFreePlaces() throws SQLException {
		return places.getFreePlaces(con.getConnnection());
	}

	public String add(String name) throws SQLException {
		return places.addPlace(name,con.getConnnection());
	}
	
	public void changeBusying(int id) throws SQLException {
		places.changeBusying(con.getConnnection(), false, id);
	}

	@Override
	public void exportFromCSV() throws Exception {
		places.exportPlacesFromCSV(con.getConnnection());
	}

	@Override
	public void importToCSV() throws Exception {
		places.importPlacesToCSV(con.getConnnection());
	}

	
}
