package com.senla.autoservice.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import com.senla.autoservice.bean.Place;
import com.senla.autoservice.dao.GarageDao;

public interface IGarageManager extends IManager{
	public String getFreePlaces() throws SQLException;
	public void importFromCSV() throws Exception;
	public void exportToCSV() throws Exception;
	public String getSortedPlaces() throws NullPointerException, SQLException;
	public String add(String name) throws SQLException;
}
