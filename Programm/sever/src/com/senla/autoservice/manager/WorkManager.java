package com.senla.autoservice.manager;

import java.sql.SQLException;

import com.senla.autoservice.api.IWorkManager;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.dao.WorkDao;

public class WorkManager implements IWorkManager {

	private static final String WORK_WAS_SUCCESFUL_ADDED = "Work was succesful added";
	private WorkDao works;

	public WorkManager() {
		this.works = WorkDao.getInstance();
	}

	public WorkDao getWorks() {
		return works;
	}

	public String add(String name, double price, int idMaster) throws SQLException {
		String message;
		works.addWork(name, price, idMaster);
		message = WORK_WAS_SUCCESFUL_ADDED;
		return message;
	}

	@Override
	public void exportToCSV() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importFromCSV() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
