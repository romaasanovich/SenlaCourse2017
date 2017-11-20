package com.senla.autoservice.manager;

import com.senla.autoservice.bean.Work;
import com.senla.autoservice.repository.WorkRepository;

public class WorkManager {

	private static final String WORK_WAS_SUCCESFUL_ADDED = "Work was succesful added";
	private WorkRepository works;
	
	 public WorkManager() {
		this.works = WorkRepository.getInstance();
	}

	public WorkRepository getWorks() {
		return works;
	}

	public String add(Work work) {
		String message;
		works.add(work);
		message = WORK_WAS_SUCCESFUL_ADDED;
		return message;
	}
}
