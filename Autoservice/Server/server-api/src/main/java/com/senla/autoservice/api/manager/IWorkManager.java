package com.senla.autoservice.api.manager;

import com.senla.autoservice.bean.aentity.AEntity;
import com.senla.autoservice.api.dao.IWorkDao;

public interface IWorkManager <T extends AEntity> extends IManager{
	public String add(String name,double price, int idMaster) throws Exception;
	public IWorkDao getWorks();
}
