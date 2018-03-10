package com.senla.autoservice.api.manager;

import com.senla.autoservice.api.bean.AEntity;

import java.util.ArrayList;

public interface IGarageManager<T extends  AEntity> extends IManager{
	public ArrayList<T> getFreePlaces() throws Exception;
	public ArrayList<T> getSortedPlaces(String name) throws Exception;
	public String add(String name) throws Exception;
}
