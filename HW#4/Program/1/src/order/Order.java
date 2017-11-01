package order;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utills.Convert;
import autoService.Entity;
import garage.GarageManager;
import garage.Place;

public class Order extends Entity {
	private Work service;
	private Place place;
	private StatusOrder status;
	private GregorianCalendar dateOfOrder;
	private GregorianCalendar dateOfCompletion;
	
	public Order(Integer id, Work service,Place place, GarageManager garageManager, StatusOrder status, int days) {
		super(id);
		this.service=service;
		this.place=place;
		this.status=status;
		this.dateOfOrder=new GregorianCalendar();
		this.dateOfCompletion=new GregorianCalendar();
		dateOfCompletion.add(Calendar.DAY_OF_YEAR,days);
		
	}
	
	public Order(String line) {
		super(0);
		String[] temp = line.split(",");
		setId(Integer.valueOf(temp[0]));
		this.service = WorkList.getService(Integer.parseInt(temp[1]));
		setStatus(Convert.fromStrToStatus(temp[2]));
		this.dateOfOrder=new GregorianCalendar();
		this.dateOfCompletion=new GregorianCalendar();
		dateOfCompletion.add(Calendar.DAY_OF_YEAR,Integer.parseInt(temp[2]));	
	}
	
	
	
	public void setPlace(Place place) {
		this.place=place;
	}
	
	public Work getService() {
		return service;
	}

	public void setService(Work service) {
		this.service = service;
	}

	public Place getPlace() {
		return place;
	}
	
	public StatusOrder getStatus() {
		return status;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public GregorianCalendar getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(GregorianCalendar dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public GregorianCalendar getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(GregorianCalendar dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	
	@Override

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(getService().toString());
		strBuild.append("Palce: ");
		strBuild.append(place.getName());
		strBuild.append("\n");
		strBuild.append("Status:");
		strBuild.append(status.toString());
		strBuild.append("\n");
		strBuild.append("Date of Order: ");
		strBuild.append(dateOfOrder.getTime());
		strBuild.append("\n");
		strBuild.append("Date of Completion:");
		strBuild.append(dateOfCompletion.getTime());
		strBuild.append("\n");
		return strBuild.toString();
		
	}



}
