public class Cook extends Person {
	private boolean isCook;
	private Order order = null;

	Cook(String nameOfCook) {
		setName(nameOfCook);
		this.isCook = false;
	}

	public boolean getIsCooked() {
		if (isCook == false) {
			return false;
		} else
			return true;
	}

	public boolean setOrder(Order order) {
		if (order != null) {
			this.order = order;
			isCook = true;
			return true;
		}
		return false;
	}

	public Order getOrder() {
		isCook = false;
		Order tmp = order;
		order = null;
		return tmp;
	}

	public Order getInfoOrder() {
		return order;
	}

}
