
public class OutputClass {
	private Restaurant rest;

	public OutputClass(Restaurant restaurant) {
		this.rest = restaurant;
	}

	public void showMenu() {
		ADish[] menu = rest.getMenu();
		if (menu.length == 0)
			System.out.println("There aren't dish");
		else
			for (int i = 0; i < menu.length; i++)
				if (menu[i] != null) {
					StringBuilder s = new StringBuilder();
					s.append(menu[i].getName());
					s.append(" | ");
					s.append(menu[i].getPrice());
					System.out.println((i + 1) + ") " + s.toString());
				}
	}

	public void showCooks() {
		Cook[] cooks = rest.getListCooks();
		for (int i = 0; i < cooks.length; i++)
			if (cooks[i] != null) {
				StringBuilder strBuild = new StringBuilder();
				strBuild.append("Cook ");
				strBuild.append(cooks[i].getName());
				strBuild.append(" : ");
				if (cooks[i].getIsCooked())
					strBuild.append("is cook");
				else
					strBuild.append("isn't cook");
				System.out.println(strBuild.toString());
			}
	}

	public void showCookWithOrder(int numOfCook) {
		Cook[] cooks = rest.getListCooks();
		if (numOfCook < 0 || numOfCook >= cooks.length)
			System.out.println("Incorrect number!");
		else if (cooks[numOfCook] == null)
			System.out.println("There isn't cook with this number (" + numOfCook + ')');
		else if (!cooks[numOfCook].getIsCooked())
			System.out.println("This cook haven't order");
		else {
			System.out.println("Order:");

			Order order = cooks[numOfCook].getInfoOrder();
			StringBuilder strBuild = new StringBuilder();
			strBuild.append("Number of table : ");
			strBuild.append(order.getNumOfTable());
			strBuild.append('\n');
			strBuild.append("List of dishes:\n");
			for (int i = 0; i < order.getOrderDishes().length; i++) {
				strBuild.append(order.getDish(i).getName());
				strBuild.append(" | ");
				strBuild.append(order.getDish(i).getPrice());
				strBuild.append('\n');
			}
			System.out.println(strBuild.toString());
		}
	}

	public void showPriceOfOrder(int numOfTable) {
		boolean[] tables  = rest.getListOfTables();
		if (numOfTable > -1 && numOfTable < tables.length) {
			if (!tables[numOfTable])
				System.out.println("There is no order for this table!!!");
			else {
				Cook[] cooks = rest.getListCooks();
				for (int i = 0; i < cooks.length; i++)
					if (cooks[i] != null && cooks[i].getIsCooked()
							&& cooks[i].getInfoOrder().getNumOfTable() == numOfTable) {
						ADish[] orderedDishes = cooks[i].getInfoOrder().getOrderDishes();
						for (int j = 0; j < orderedDishes.length; j++)
							System.out.println(orderedDishes[j]);
					}

			}
		}
	}
	
}
