package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Master;

public class MasterRepository {
	private ArrayList<Master> masters;
	private static MasterRepository instance;

	static private int lastID;

	private  MasterRepository() {
		masters = new ArrayList<Master>();
	}
	
	public static MasterRepository getInstance() {
		if(instance == null) {
			instance = new MasterRepository();
		} 
		return instance;
	}

	static public int getLastID() {
		return lastID;
	}

	public ArrayList<Master> getListOfMasters() {
		return masters;
	}

	public Master getMasterById(int id) {
		for (int i = 0; i < masters.size(); i++) {
			if (masters.get(i).getId() == id) {
				return masters.get(i);
			}
		}
		return null;
	}

	public void add(Master obj) {
		masters.add(obj);
	}
}
