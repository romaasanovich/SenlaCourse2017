package com.senla.autoservice.api.manager;

import java.sql.SQLException;

import com.senla.autoservice.api.bean.AEntity;
import com.senla.autoservice.api.dao.IWorkDao;

public interface IWorkManager <T extends AEntity> extends IManager{
	public String add(String name,double price, int idMaster) throws SQLException;
	public IWorkDao getWorks();
}
