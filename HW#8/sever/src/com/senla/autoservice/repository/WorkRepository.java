package com.senla.autoservice.repository;

import java.util.ArrayList;

import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.ARepository;
import com.senla.autoservice.bean.Work;

public class WorkRepository extends ARepository{
	private static WorkRepository instance;
	static private int lastID;

	public WorkRepository() {
		repository = new ArrayList<Work>();
	}

	public static WorkRepository getInstance() {
		if (instance == null) {
			instance = new WorkRepository();
		}
		return instance;
	}

	public ArrayList<Work> getListOfServices() {
		return (ArrayList<Work>) repository;
	}

	static public int getLastID() {
		return lastID;
	}

	public Work getService(int id) {
		for (int i = 0; i < repository.size(); i++) {
			if (((AEntity) repository.get(i)).getId() == id) {
				return (Work) repository.get(i);
			}
		}
		return null;
	}

	

	public void add(AEntity obj) {
		repository.add((Work) obj);
		lastID = repository.size() - 1;
	}
}
