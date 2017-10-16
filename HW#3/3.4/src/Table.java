
public class Table {
	public int numOfTable;
	public boolean isUsing;
	Order order= new Order();
	
	Table(int i){
		numOfTable=i;
		isUsing=false;
	}
	
	public void outputTotalPriceOfOrder() {
		double sum=0;
		for(int i = 0 ; i< order.orderDishes.length;i++) {
			sum+=order.orderDishes[i].price;
		}
		System.out.println("Total price for "+(numOfTable+1)+" table is: "+sum);
	}
	
}
