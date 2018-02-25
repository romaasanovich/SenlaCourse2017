package com.senla.autoservice.api.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.bean.AEntity;

public interface IGarageManager<T extends  AEntity> extends IManager{
	public ArrayList<T> getFreePlaces() throws SQLException;
	public ArrayList<T> getSortedPlaces(String name) throws NullPointerException, SQLException;
	public String add(String name) throws SQLException;
}
