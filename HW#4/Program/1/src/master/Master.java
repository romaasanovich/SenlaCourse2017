package master;

import java.util.Date;
import java.util.GregorianCalendar;

import autoService.Entity;
import work.WorkList;
import garage.GaragePlaces;
import order.Order;
import order.OrderList;
import utillites.Convert;

public class Master extends Entity {
	private boolean isWork;
	private String name;
	private OrderList orders;

	public Master(Integer id, String name, OrderList orders) {
		super(id);
		this.orders = orders;
		setName(name);
		if (orders == null) {
			setIsWork(false);
		} else {
			setIsWork(true);
		}
	}

	public Master(String line, GaragePlaces placeList,WorkList workList) {
		super(0);
		String[] temp = line.split("-");
		setId(Integer.valueOf(temp[0]));
		this.name = temp[1];
		this.isWork = Boolean.parseBoolean(temp[2]);
		if (temp[3].equals("null")) {
			this.orders = new OrderList(null);
		}
		else {
			Order[] orders = new Order[Integer.parseInt(temp[3])];
			int pos = 4;
			for (int i = 0; i < Integer.parseInt(temp[3]); i++) {
				Order ord= new Order();
				ord.setService(workList.getService(Integer.parseInt(temp[pos++])));
				ord.setPlace(placeList.getPlaceById(Integer.parseInt(temp[pos++])));
				ord.setStatus(Convert.fromStrToStatus(temp[pos++]));

				String[] tempDate = temp[pos++].split(",");
				GregorianCalendar grCal = new GregorianCalendar(Integer.parseInt(tempDate[0]),
						Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
				Date date = (Date) (grCal).getTime();
				ord.setDateOfOrder(date);

				tempDate = temp[pos++].split(",");
				GregorianCalendar grCalDateStart = new GregorianCalendar(Integer.parseInt(tempDate[0]),
						Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
				ord.setDateOfPlannedStart((grCalDateStart).getTime());
				
				tempDate = temp[pos++].split(",");
				GregorianCalendar grCalDateCompl = new GregorianCalendar(Integer.parseInt(tempDate[0]),
						Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
				ord.setDateOfCompletion((grCalDateCompl).getTime());

				orders[i]=ord;
			}
			this.orders = new OrderList(orders);
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

	public OrderList getOrders() {
		return orders;
	}

	public void setOrders(OrderList orders) {
		this.orders = orders;
	}

	@Override

	public String toString() {
		String message;
		message=getId()+"-"+getName()+"-"+getIsWork()+"-";
		if(getOrders().getListOfOrders()!= null) {
			message+=getOrders().getListOfOrders().length;
			for(Order order : this.orders.getListOfOrders()) {
				message+=order;
			}
		}
		else {
			message+="null";
		}
		
		return message;
	}
}
