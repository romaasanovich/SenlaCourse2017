package com.senla.autoservice.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.senla.autoservice.bean.Order;
import com.senla.autoservice.dao.MasterDao;

public interface IMasterManager extends IManager {
	public void importFromCSV() throws Exception;
	public void exportToCSV() throws Exception;
	public String getSortedMasters(String string) throws SQLException;
	public String getMasterCarriedOutCurrentOrder(int idOrder) throws SQLException;
	public String add(String name) throws SQLException;
}

