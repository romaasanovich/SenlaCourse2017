package com.senla.autoservice.bean;

import java.util.ArrayList;

import com.senla.autoservice.annotations.CsvEntity;
import com.senla.autoservice.annotations.CsvProperty;
import com.senla.autoservice.annotations.PropertyType;
import com.senla.autoservice.api.AEntity;

 @CsvEntity(filename="masters.csv",valueSeparator=";",entityId="id")
public class Master extends AEntity  {
	private static final long serialVersionUID = -590065500974890221L;
 @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber=2)
	private boolean isWork;
 @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber=1)
	private String name;
 @CsvProperty(propertyType = PropertyType.CompositeProperty, keyField="id")
	private ArrayList<Work> works;
 @CsvProperty(propertyType = PropertyType.CompositeProperty, keyField="id") 
	private ArrayList<Order> orders;

	public Master(Integer id, String name, ArrayList<Work> works, ArrayList<Order> orders) {
		super(id);
		this.orders = orders;
		this.works = works;
		setName(name);
		if (orders == null) {
			setIsWork(false);
		} else {
			setIsWork(true);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsWork(boolean isWork) {
		this.isWork = isWork;
	}

	public boolean getIsWork() {
		return isWork;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Work> getWorks() {
		return works;
	}

	public void setWorks(ArrayList<Work> works) {
		this.works = works;
	}

	public Boolean addOrder(Order obj){
		if(obj !=null) {
		orders.add(obj);
		return true;
		}
		else return false;
	}
	
	
	public Boolean addWork(Work obj)  {
		if(obj !=null) {
		works.add(obj);
		return true;
		}
		else return false;

	}
	
	public void update(AEntity master) {
		this.name=((Master) master).getName();
		this.isWork=((Master) master).getIsWork();
		this.orders=((Master) master).getOrders();
		this.works=((Master) master).getWorks();
	}
	
	
	@Override
	public String toString() {
		String message;
		message = getId() + ";" + getName() + ";" + getIsWork() + ";";
		if (getWorks() != null) {
			message += getWorks().size();
			for (Work work : this.works) {
				message += work;
			}
		} else {
			message += "null";
		}

		message += ";";

		if (getOrders() != null) {
			message += getOrders().size();
			message += ";";	
			for (Order order : this.orders) {
				message += order;
			}
		} else {
			message += "null";
		}

		message += ";";
		return message;
	}
}
