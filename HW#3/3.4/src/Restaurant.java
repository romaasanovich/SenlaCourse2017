
public class Restaurant {
	private boolean[] tables;
	private ADish[] menu;
	private Cook[] cooks;

	public Restaurant(int countOfTables, int countOfDishes, int countOfCooks) {
		tables = new boolean[countOfTables];
		menu = new ADish[countOfDishes];
		cooks = new Cook[countOfCooks];
	}

	public ADish[] getMenu() {
		return menu.clone();
	}

	public ADish getDishFromMenu(int numberOfDish) {
		if (numberOfDish > -1 && numberOfDish < menu.length)
			return menu[numberOfDish];
		return null;
	}

	public Cook[] getListCooks() {
		return cooks.clone();
	}

	public boolean[] getListOfTables() {
		return tables.clone();
	}

	public Cook getCook(int numberOfCook) {
		if (numberOfCook > -1 && numberOfCook < cooks.length)
			return cooks[numberOfCook];
		return null;
	}

	// dishes

	public boolean addDish(ADish dish) {
		for (int i = 0; i < menu.length; i++)
			if (menu[i] == null) {
				menu[i] = dish;
				return true;
			}
		return false;
	}

	public boolean removeDish(int index) {
		if (index > -1 && index < menu.length) {
			menu[index] = null;
			return true;
		}
		return false;
	}

	// cooks

	public boolean addCook(Cook cook) {
		for (int i = 0; i < cooks.length; i++)
			if (cooks[i] == null) {
				cooks[i] = cook;
				return true;
			}
		return false;
	}

	public boolean removeCook(int index) {
		if (index > -1 && index < cooks.length) {
			cooks[index] = null;
			return true;
		}
		return false;
	}

	public Order createNewOrder(int numOfTable, int[] numberOfDishes) {

		if (numOfTable < (-1) && numOfTable >= tables.length) {
			System.out.println("Inccorrect num of table!!!");
			return null;
		}
		if (tables[numOfTable] == true) {
			System.out.println("This table is busy!!!");
			return null;
		}

		// CheckingOrderedDishes

		int count = 0;
		for (int i = 0; i < numberOfDishes.length; i++)
			if (numberOfDishes[i] >= 0 && numberOfDishes[i] < menu.length && menu[numberOfDishes[i]] != null)
				count++;
		if (count == 0) {
			System.out.println("Incorrect numbers of ordered dishes");
			return null;
		}

		ADish[] dishs = new ADish[count];
		count = 0;
		for (int i = 0; i < numberOfDishes.length; i++) {
			if (numberOfDishes[i] > -1 && menu[numberOfDishes[i]] != null && numberOfDishes[i] < menu.length) {
				dishs[count] = menu[numberOfDishes[i]];
				count++;
			}
		}

		// Checking cooks

		Cook c = null;
		for (int i = 0; i < cooks.length; i++)
			if (cooks[i] != null && !cooks[i].getIsCooked()) {
				c = cooks[i];
				break;
			}
		if (c == null) {
			System.out.println("There aren't free cooks");
			return null;
		}

		Order order = new Order(numOfTable, dishs);
		tables[numOfTable] = true;
		c.setOrder(order);
		return order;
	}

	public boolean removeOrder(Order order) {
		if (order != null)
			for (int i = 0; i < cooks.length; i++) {
				if (cooks[i] != null)
					if (cooks[i].getInfoOrder() == order) {
						cooks[i].getOrder();
						tables[order.getNumOfTable()] = false;
						return true;
					}
			}

		return false;
	}

	public boolean removeOrder(int indexOfTable) {
		if (indexOfTable > -1 && indexOfTable < tables.length && tables[indexOfTable])
			for (int i = 0; i < cooks.length; i++)
				if (cooks[i] != null && cooks[i].getIsCooked()
						&& cooks[i].getInfoOrder().getNumOfTable() == indexOfTable) {
					cooks[i].getOrder();
					tables[indexOfTable] = false;
					return true;
				}
		return false;
	}

}
