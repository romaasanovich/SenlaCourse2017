package garage;

import autoService.Entity;

public class Place extends Entity {
	private String name;
	private boolean isBusy;

	public Place(Integer id, String name) {
		super(id);
		this.name = name;
		isBusy = false;
	}

	public Place(String line) {
		super(0);
		this.name=line;
		isBusy = false;
	}
	
	public void  setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	@Override
	
	public String toString() {
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(getName());
		strBuild.append(": ");
		if (isBusy()) {
			strBuild.append("is busy");
		} 
		else {
			strBuild.append("isn't busy");
		}
		return strBuild.toString();
	}

}
