package com.senla.autoservice.manager;

import com.senla.autoservice.api.Constants;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.repository.WorkList;

public class WorkManager {
	
	private static final String	WORK_WAS_SUCCESFUL_ADDED = "Work was succesful added";
	
	
	private WorkList works;
	
	public WorkManager() {
		works= new WorkList(Constants.ARRAY_SIZE);
	}
	
	WorkManager(WorkList works){
		this.works = new WorkList(works.getListOfServices());
	}
	
	public WorkList getWorks() {
		return works;
	}
	
	public String add(Work work) {
		String message;
		if (works.add(work)) {
			message = WORK_WAS_SUCCESFUL_ADDED;
		} else {
			message = Constants.ERROR_THIS_ID_IS_NOT_FREE;
		}
		return message;
	}
}
