package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.bean.Work;

public class WorkRepository extends ARepository{
	private ArrayList<Work> serviceRepository;
	private static WorkRepository instance;
	static private int lastID;

	public WorkRepository() {
		serviceRepository = new ArrayList<Work>();
	}

	public static WorkRepository getInstance() {
		if (instance == null) {
			instance = new WorkRepository();
		}
		return instance;
	}

	public ArrayList<Work> getListOfServices() {
		return serviceRepository;
	}

	static public int getLastID() {
		return lastID;
	}

	public Work getService(int id) {
		for (int i = 0; i < serviceRepository.size(); i++) {
			if (serviceRepository.get(i).getId() == id) {
				return serviceRepository.get(i);
			}
		}
		return null;
	}

	public Work findById(Integer id) {
		for (Work entity : serviceRepository) {
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
		serviceRepository.add(obj);
		lastID = serviceRepository.size() - 1;
	}
}
