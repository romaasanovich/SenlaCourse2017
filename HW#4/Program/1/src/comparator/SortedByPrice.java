package comparator;

import java.util.Comparator;

import order.Order;

public class SortedByPrice implements Comparator<Order>{
	public int compare(Order obj1, Order obj2)
    {
        double price1 = obj1.getService().getPrice();
        double price2 = obj2.getService().getPrice();

        if (price1 > price2) {
            return 1;
        } else if (price1 < price2) {
            return -1;
        } else {
            return 0;
        }
    }
}
