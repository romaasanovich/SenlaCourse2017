package comparator;

import java.util.Comparator;
import java.util.GregorianCalendar;

import order.Order;

public class SortedByPlannedStarting implements Comparator<Order>{
	    public int compare(Order obj1, Order obj2)
	    {
	    	GregorianCalendar str1 = obj1.getDatePlannedStarting();
	    	GregorianCalendar str2 = obj2.getDatePlannedStarting();
	        return str1.compareTo(str2);
	    }
	}
