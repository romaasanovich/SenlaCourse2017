
public class Menu {
	public Dish[] dishes = new Dish[] {
			new Dish("Chicken",2.55),new Dish("Fired Potato",1.5),new Dish("Stake",5.0),new Dish("Fried Fish",3.0)
	};

	public void addDish(String name, double price) {
		Dish temp[] = new Dish[dishes.length + 1];
		for (int i = 0; i < dishes.length; i++) {
			temp[i] = dishes[i];
		}

		Dish newDish = new Dish(name, price);
		temp[dishes.length] = newDish;
		dishes = temp;
		System.out.println("Dish is added!");
	}

	public void delDish(String chossenDish) {
		Dish temp[] = new Dish[dishes.length - 1];
		for (int j=0, i = 0; i < dishes.length-1; i++,j++) {
			if (!dishes[i].name.equals(chossenDish)) {
				temp[i] = dishes[j];
			}
			else {
				j++;
				temp[i] = dishes[j];
				
				}
		}
		dishes = temp;
		System.out.println("Dish is deleted!!!");
	}

	public Dish choseDish(String name) {
		for (int i = 0; i < dishes.length; i++) {
			if (dishes[i].name.equals(name)) {
				return dishes[i];
			}
		}
		return null;
	}

	public void outputMenu() {
		if (dishes.length == 0) {
			System.out.println("There is no dishes in menu!!!");
		} else {
			System.out.println("Dish|Price");
			for (int i = 0; i < dishes.length; i++) {
				System.out.println(dishes[i].name+"|"+dishes[i].price);
			}
		}
	}
}	
