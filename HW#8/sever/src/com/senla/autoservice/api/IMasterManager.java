package com.senla.autoservice.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import com.senla.autoservice.bean.Master;
import com.senla.autoservice.bean.Order;
import com.senla.autoservice.bean.Work;
import com.senla.autoservice.repository.MasterRepository;

public interface IMasterManager extends IManager {
	public MasterRepository getMasters();
	public ArrayList<Master> getSortedMasters(Comparator<Master> comp);
	public Master getMasterCarriedOutCurrentOrder(Order order); 
	public int getCountOfFreePlacesOnDate(Date date);
	public ArrayList<Order> getAllOrders();
	public Date getFreeDate();
	public String add(Master master);
	public String addOrderToMaster(int id, Order order);
	public String addWorkToMaster(int id, Work work);
	public void importFromCSV() throws Exception;
	public void exportFromCSV() throws Exception;
	public String deserializeMasters();
}

