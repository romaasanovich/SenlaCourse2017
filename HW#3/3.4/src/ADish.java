
public abstract  class ADish {
	private String name;
	private double price;
	
	ADish(String name, double price){
		this.name=name;
		this.price=price;
	}
	
	public double getPrice() {
		return price;
	}
	public String getName() {
		return name;	
	}
	
	
}
