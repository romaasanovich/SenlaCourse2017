
public class Order {
	private ADish[] orderDishes;
	private int numOfTable;
	
	public Order(int numOfTable,ADish[] list) {
		orderDishes=list;
		this.numOfTable=numOfTable;
	}
	
	public int getNumOfTable() {
		return numOfTable;
	}
	
	public ADish getDish(int index) {
		return orderDishes[index];
	}
	
	public ADish[] getOrderDishes() {
		return orderDishes.clone();
	}
	
	public double getTotalPriceOfOrder() {
		double sum=0;
		for(int i = 0 ; i< orderDishes.length;i++) {
			sum+=orderDishes[i].getPrice();
		}
		return sum;
	}
	

}
