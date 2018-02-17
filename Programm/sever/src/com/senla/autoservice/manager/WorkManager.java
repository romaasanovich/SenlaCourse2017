package com.senla.autoservice.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.senla.autoservice.DBConnector.DBConnector;
import com.senla.autoservice.api.IWorkManager;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.dao.WorkDao;

public class WorkManager implements IWorkManager {

	private static final String WORK_WAS_SUCCESFUL_ADDED = "Work was succesful added";
	private WorkDao works;
	private DBConnector con;
	
	public WorkManager() {
		works = WorkDao.getInstance();
		con = DBConnector.getInstance();
	}

	public WorkDao getWorks() {
		return works;
	}

	public String add(String name, double price, int idMaster) throws SQLException {
		String message;
		works.addWork(name, price, idMaster,con.getConnnection());
		message = WORK_WAS_SUCCESFUL_ADDED;
		return message;
	}

	
	@Override
	public void exportFromCSV() throws Exception {

	}

	@Override
	public void importToCSV() throws Exception {
		works.importOrdersToCSV(con.getConnnection());
	}
	
	
}
