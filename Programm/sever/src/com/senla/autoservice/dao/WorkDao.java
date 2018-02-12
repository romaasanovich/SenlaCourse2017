package com.senla.autoservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.ADao;
import com.senla.autoservice.api.AEntity;
import com.senla.autoservice.api.constants.Constants;
import com.senla.autoservice.api.constants.SqlQuery;
import com.senla.autoservice.bean.Work;

public class WorkDao extends ADao {
	private static ADao instance;
	static private int lastID;
	com.senla.autoservice.DBConnection.DBConnection sqlConnection;

	private WorkDao() {
		sqlConnection = com.senla.autoservice.DBConnection.DBConnection.getInstance();
	}

	public static WorkDao getInstance() {
		if (instance == null) {
			instance = new WorkDao();
		}
		return (WorkDao) instance;
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

	public String addWork(String name, double price, int idMaster) throws SQLException {
		ResultSet rs =sqlConnection.executeQuery(SqlQuery.GET_ID_WORK);
		int id = rs.getInt(1);
		rs = sqlConnection.executeQuery(SqlQuery.ADD_WORK + "(" + id + "," + name + ","+price +","+ idMaster + ")");
		return Constants.SUCCESS;
	}
}
