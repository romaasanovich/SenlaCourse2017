package utills;

import order.StatusOrder;

public class Convert {
	public  final static  StatusOrder fromStrToStatus(String status) {
		if (status.equals("Broned")) {
			return StatusOrder.Broned;
		} else if (status.equals("Opened")) {
			return StatusOrder.Opened;
		} else if (status.equals("Canceled")) {
			return StatusOrder.Canceled;
		} else if (status.equals("Closed")) {
			return StatusOrder.Closed;
		} else if (status.equals("Deleted")) {
			return StatusOrder.Deleted;
		} else
			return null;
	}

}
