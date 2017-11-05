package com.senla.autoservice.repository;

import java.util.Arrays;

import com.senla.autoservice.bean.Work;
import com.senla.autoservice.utills.ArrayChecker;

public class WorkList {
	private Work[] serviceList;
	static private int lastID;
	public WorkList(int size) {
		serviceList= new Work[size];
	}
	
	public WorkList(Work[] list) {
		serviceList= list;
	}

	public Work[] getListOfServices() {
		return serviceList;
	}

	static public int getLastID() {
		return lastID;
	}

	
	public  Work getService(int id) {
		for (int i = 0; i < serviceList.length; i++) {
			if (serviceList[i].getId() == id) {
				return serviceList[i];
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

	public Boolean add(Work obj) {
		if (!ArrayChecker.isFreeId(obj.getId(), serviceList)) {
			return false;
		}
		if (!ArrayChecker.isEnoughtSpace(serviceList)) {
			serviceList = Arrays.copyOf(ArrayChecker.resize(serviceList),
					serviceList.length*2, Work[].class);
		}
		Integer position = ArrayChecker.getFreePosition(serviceList);
		lastID=position;
		serviceList[position] = obj;
		return true;
	}	
}
