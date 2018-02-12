package com.senla.autoservice.api;

import java.sql.SQLException;

import com.senla.autoservice.dao.WorkDao;

public interface IWorkManager extends IManager{
	public String add(String name,double price, int idMaster) throws SQLException;
	public WorkDao getWorks();
}
