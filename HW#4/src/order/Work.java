package order;
import autoService.Entity;

public class Work extends Entity {
	private String nameOfService;
	private double price;

	public Work(Integer id, String nameOfService, double price) {
		super(id);
		this.nameOfService = nameOfService;
		this.price = price;
	}
	
	public Work (String line) {
		super(0);
		line = line.replace("- ", "");
		line = line.replace(")", "");
		String[] temp = line.split(" ");
		setId(Integer.valueOf(temp[0]));
		this.nameOfService = temp[1];
		this.price = Double.valueOf(temp[2]);
	}

	public String getNameOfService() {
		return nameOfService;
	}

	public void setNameOfService(String nameOfService) {
		this.nameOfService = nameOfService;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override

	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(getId());
		strBuild.append(" ");
		strBuild.append(getNameOfService());
		strBuild.append(": ");
		strBuild.append(getPrice());
		strBuild.append("\n");
		return strBuild.toString();
	}


}
