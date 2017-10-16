
public class Order {
	Dish orderDishes[]= new Dish[] {
			
	};
	
	public void addDish(String name, Menu menu) {
		Dish temp[]= new Dish[orderDishes.length+1];
		for(int i =0;i<orderDishes.length;i++) {
			temp[i]=orderDishes[i];
		}
		Dish newOrderDish= menu.choseDish(name);
		temp[orderDishes.length]=newOrderDish;
		orderDishes=temp;
	}
	
	public void checkOut(Kitcheners kitcheners, Table table) {
		for(int j=0, i=0;i<orderDishes.length;i++,j++) {
			if(kitcheners.kitcheners[i].isCook==false) {
				kitcheners.kitcheners[i].getOrder(orderDishes[j], table);
				System.out.println("Kitchener "+kitcheners.kitcheners[i].name+" is cook "+orderDishes[j].name);
			}
		}
	}
}
