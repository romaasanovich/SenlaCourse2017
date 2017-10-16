public class Kitchener extends Person {
	public boolean isCook;
	public Dish dish;
	public Table table;
	
	Kitchener(String nameOfKitchener){
		name=nameOfKitchener;
		this.isCook=false;
	}
	
	public void getOrder(Dish dish,Table table) {
		this.dish=dish;
		this.table=table;
		isCook=true;
		}
}
