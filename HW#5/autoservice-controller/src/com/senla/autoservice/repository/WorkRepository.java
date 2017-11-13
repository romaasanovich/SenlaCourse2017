package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Work;

public class WorkRepository {
	private ArrayList<Work> serviceList;
	private static WorkRepository instance;
	static private int lastID;

	public WorkRepository() {
		serviceList = new ArrayList<Work>();
	}

	public static WorkRepository getInstance() {
		if (instance == null) {
			instance = new WorkRepository();
		}
		return instance;
	}

	public ArrayList<Work> getListOfServices() {
		return serviceList;
	}

	static public int getLastID() {
		return lastID;
	}

	public Work getService(int id) {
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).getId() == id) {
				return serviceList.get(i);
			}
		}
		return null;
	}

	public Work findById(Integer id) {
		for (Work entity : serviceList) {
			if (entity == null) {
				break;
			}
			if (entity.getId() == id) {
				return entity;
			}
		}
		return null;
	}

	public void add(Work obj) {
		serviceList.add(obj);
		lastID = serviceList.size() - 1;
	}
}
