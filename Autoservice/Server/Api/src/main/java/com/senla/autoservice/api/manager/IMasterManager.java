package com.senla.autoservice.api.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.senla.autoservice.api.bean.AEntity;

public interface IMasterManager <T extends  AEntity>extends IManager {
	public ArrayList<T> getSortedMasters(String string) throws SQLException;
	public AEntity getMasterCarriedOutCurrentOrder(int idOrder) throws SQLException;
	public String add(String name) throws SQLException;
}

