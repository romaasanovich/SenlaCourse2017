package com.senla.autoservice.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.DBConnector.DBConnector;
import com.senla.autoservice.api.IMasterManager;
import com.senla.autoservice.bean.Master;
import com.senla.autoservice.dao.MasterDao;

public class MasterManager implements IMasterManager {

	private MasterDao masters;
	private DBConnector con;

	public MasterManager() {
		masters = MasterDao.getInstance();
		con = DBConnector.getInstance();
	}

	public ArrayList<Master> getSortedMasters(String comp) throws SQLException {
		return masters.getListOfMasters(comp, con.getConnnection());
	}

	public Master getMasterCarriedOutCurrentOrder(int idOrder) throws SQLException {
		return masters.getMasterCarriedOutOrder(idOrder, con.getConnnection());
	}

	public String add(String name) throws SQLException {
		return masters.add(name, con.getConnnection());
	}
	
	public void changeBusying(int id) throws SQLException {
		masters.changeBusying(con.getConnnection(), false, id);
	}

	@Override
	public void exportFromCSV() throws Exception {

	}

	@Override
	public void importToCSV() throws Exception {
		masters.importMastersToCSV(con.getConnnection());
	}

}
