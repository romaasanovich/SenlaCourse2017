package com.senla.autoservice.bean;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.senla.autoservice.api.Entity;
import com.senla.autoservice.api.StatusOrder;

public class Order extends Entity {
	private Work service;
	private Place place;
	private StatusOrder status;
	private Date dateOfOrder;
	private Date dateOfPlannedStart;
	private Date dateOfCompletion;
	
	public Order(int id, Work service,Place place, StatusOrder status,int fDay, int sDay) {
		super(id);
		this.service=service;
		this.place=place;
		setStatus(status);
		dateOfOrder=(Date) (new GregorianCalendar()).getTime();
		GregorianCalendar grCalDateStart = new GregorianCalendar();
		grCalDateStart.add(Calendar.DAY_OF_YEAR,fDay);
		dateOfCompletion=(Date)(grCalDateStart).getTime();
		GregorianCalendar grCalDateCompl = new GregorianCalendar();
		grCalDateCompl.add(Calendar.DAY_OF_YEAR,sDay);
		dateOfCompletion=(Date)(grCalDateCompl).getTime();	
	}
	
	public Order() {
		super(0);
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
		if(status==StatusOrder.Broned||status==StatusOrder.Deleted||status==StatusOrder.Canceled || status==StatusOrder.Closed) {
			getPlace().setBusy(false);
		}
		else if(status==StatusOrder.Opened) {
			getPlace().setBusy(true);
		}
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	
	@Override

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("-");
		strBuild.append(getService().getId());
		strBuild.append("-");
		strBuild.append(place.getId());
		strBuild.append("-");
		strBuild.append(status.toString());
		strBuild.append("-");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd");
		strBuild.append(dateFormat.format(dateOfOrder));
		strBuild.append("-");
		strBuild.append(dateFormat.format(dateOfPlannedStart));
		strBuild.append("-");
		strBuild.append(dateFormat.format(dateOfCompletion));
		return strBuild.toString();
	}

	public Date getDateOfPlannedStart() {
		return dateOfPlannedStart;
	}

	public void setDateOfPlannedStart(Date dateOfPlannedStart) {
		this.dateOfPlannedStart = dateOfPlannedStart;
	}



}
