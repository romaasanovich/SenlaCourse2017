import java.util.Random;

public class Program {
	public static void main(String[] s) {
		Restaurant restaurant = new Restaurant(10, 15, 5);
		OutputClass show = new OutputClass(restaurant);
		
		for (int i = 1; i <= 8; i+=2) {
			restaurant.addDish(new Drink("Drink " + (i), Math.abs(new Random().nextInt() % 20)));
			restaurant.addDish(new Drink("Drink " + (i+1), Math.abs(new Random().nextInt() % 20)));
			restaurant.addDish(new Dish("Dish " + (i), Math.abs(new Random().nextInt() % 20)));
			restaurant.addDish(new Dish("Dish " + (i+1), Math.abs(new Random().nextInt() % 20)));
		}
		//show.showMenu();
		///del some dishes
		
		//restaurant.removeDish(1);
		restaurant.removeDish(6);
		restaurant.removeDish(7);
		show.showMenu();
		
		
		//add 5 cooks 
		for(int i = 0; i<5;i++) {
			restaurant.addCook(new Cook("Cook " + (i+1)));
		}
		//show.showCooks();
		
		restaurant.removeCook(4);
		restaurant.removeCook(0);
		//show.showCooks();
		
		restaurant.createNewOrder(1,new int[] {1,5,6});
		show.showCooks();
		
		show.showCookWithOrder(1);
		
		restaurant.removeOrder(1);
		show.showCooks();
		
	}
}
